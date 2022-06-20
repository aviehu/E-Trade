import {useEffect, useState} from "react";
import get from "../util/get";
import post from "../util/post";
import {createTheme, ThemeProvider} from "@mui/material/styles";
import Box from "@mui/material/Box";
import CssBaseline from "@mui/material/CssBaseline";
import MyAppBar from "../dashboard/MyAppBar";
import MyDrawer from "../dashboard/MyDrawer";
import Toolbar from "@mui/material/Toolbar";
import Container from "@mui/material/Container";
import Grid from "@mui/material/Grid";
import CanvasJSReact from './canvasjs.react';
import {TextField} from "@mui/material";
import Button from "@mui/material/Button";
import MyError from "../util/MyError";
import SockJS from "sockjs-client";
import Stomp from "stompjs";

const CanvasJS = CanvasJSReact.CanvasJS;
const CanvasJSChart = CanvasJSReact.CanvasJSChart;

const mdTheme = createTheme();

export default function Stats() {
    const [stats, setStats] = useState(null);
    const [open, setOpen] = useState(true)
    const [startYear, setStartYear] = useState(2022)
    const [startMonth, setStartMonth] = useState(6)
    const [startDay, setStartDay] = useState(1);
    const [endYear, setEndYear] = useState(2022)
    const [endMonth, setEndMonth] = useState(6)
    const [endDay, setEndDay] = useState(2);
    const [error, setError] = useState("")
    const [hasError, setHasError] = useState(false)




    const getStats = async () => {
        const body = {
            startDay,
            startMonth,
            startYear,
            endDay,
            endMonth,
            endYear
        }
        const res = await post(body, 'users/viewtraffic');
        const ans = await res.json()
        if(ans.val) {
            if(checkStats(ans.val)) {
                setStats(ans.val)
            }
        } else {
            setError(ans.err)
            setHasError(true)
        }

    }
    function checkStats(ans){
        if(!stats){
            return true;
        }
        if(ans.guests !== 0 || ans.simpleMembers !== 0 || ans.storeManagers !== 0 || ans.storeOwners !== 0 || ans.sysManagers !== 0 ){
            return true;
        }
        return false;
    }

    useEffect(() => {

        getStats()
        const sock = new SockJS('http://localhost:8080/real-stats');

        let stompClient = Stomp.over(sock);

        sock.onopen = function() {
            console.log('open');
        }
        stompClient.connect({}, function (frame) {
            stompClient.subscribe(`/topic/stats`, async function (greeting) {

                await getStats()
                //you can execute any function here
            });
        });
    }, [])

    const getDataPoints = () => {
        return [
            {
                label: "Guests",
                y: stats.guests
            },
            {
                label: 'Simple Members',
                y: stats.simpleMembers
            },
            {
                label: 'Manager Members',
                y: stats.storeManagers
            },
            {
                label: 'Owner Members',
                y: stats.storeOwners
            },
            {
                label: 'System Manager',
                y: stats.sysManagers
            }
        ]
    }



    const renderStats = () => {
        const options = {
            title: {
                text: `Traffic for ${startDay}.${startMonth}.${startYear} - ${endDay}.${endMonth}.${endYear}`
            },
            data: [
                {
                    type: "column",
                    dataPoints: getDataPoints()
                }
            ]
        }
        return (
            stats ? <div>
                        <CanvasJSChart options = {options}/>
                    </div> : null
        )
    }

    return (
        <ThemeProvider theme={mdTheme}>
            <Box sx={{ display: 'flex' }}>
                <CssBaseline />
                <MyError open={hasError} setOpen={setHasError} error={error}/>
                <MyAppBar title={"My Stores"} open={open} toggleDrawer={() => {setOpen(!open)}}/>
                <MyDrawer open={open} setOpen={setOpen}/>
                <Box
                    component="main"
                    sx={{
                        backgroundColor: (theme) =>
                            theme.palette.mode === 'light'
                                ? theme.palette.grey[100]
                                : theme.palette.grey[900],
                        flexGrow: 1,
                        height: '100vh',
                        overflow: 'auto',
                    }}
                >
                    <Toolbar />
                    <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
                        <Grid container spacing={3}>
                            <Grid item xs={12}>
                                <main>
                                    {stats ? renderStats() : <h2>Loading...</h2>}
                                </main>
                            </Grid>
                            <Grid item xs={4}>
                                <main>
                                    <TextField value={startDay} onChange={(event) => {setStartDay(event.target.value)}} label={"Starting Day"}></TextField>
                                </main>
                            </Grid>
                            <Grid item xs={4}>
                                <main>
                                    <TextField value={startMonth} onChange={(event) => {setStartMonth(event.target.value)}} label={"Starting Month"}></TextField>
                                </main>
                            </Grid>
                            <Grid item xs={4}>
                                <main>
                                    <TextField value={startYear} onChange={(event) => {setStartYear(event.target.value)}} label={"Starting Year"}></TextField>
                                </main>
                            </Grid>
                            <Grid item xs={4}>
                                <main>
                                    <TextField value={endDay} onChange={(event) => {setEndDay(event.target.value)}} label={"End Day"}></TextField>
                                </main>
                            </Grid>
                            <Grid item xs={4}>
                                <main>
                                    <TextField value={endMonth} onChange={(event) => {setEndMonth(event.target.value)}} label={"End Month"}></TextField>
                                </main>
                            </Grid>
                            <Grid item xs={4}>
                                <main>
                                    <TextField value={endYear} onChange={(event) => {setEndYear(event.target.value)}} label={"End Year"}></TextField>
                                </main>
                            </Grid>
                            <Grid item xs={4}>
                                <main>
                                    <Button onClick={getStats}>Get Traffic</Button>
                                </main>
                            </Grid>

                        </Grid>
                    </Container>
                </Box>
            </Box>
        </ThemeProvider>
        )





}
