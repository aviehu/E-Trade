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

const CanvasJS = CanvasJSReact.CanvasJS;
const CanvasJSChart = CanvasJSReact.CanvasJSChart;

const mdTheme = createTheme();

export default function Stats() {
    const [stats, setStats] = useState(null);
    const [open, setOpen] = useState(true)
    const [year, setYear] = useState(2022)
    const [month, setMonth] = useState(6)
    const [day, setDay] = useState(1);

    const getStats = async () => {
        const body = {
            year,
            month,
            day
        }
        const res = await post(body, 'users/viewtraffic');
        const ans = await res.json()
        setStats(ans.val)
    }

    useEffect(() => {
        getStats()
    }, [])

    const getDataPoints = () => {
        return [
            {
                label: "Guests",
                y: stats.guests.length
            },
            {
                label: 'Simple Members',
                y: stats.simpleMembers.length
            },
            {
                label: 'Manager Members',
                y: stats.managersMembers.length
            },
            {
                label: 'Owner Members',
                y: stats.ownersMembers.length
            },
            {
                label: 'System Manager',
                y: stats.sysManagers.length
            }
        ]
    }

    const renderStats = () => {
        const options = {
            title: {
                text: `Traffic for ${day}.${month}.${year}`
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
                            <Grid item xs={3}>
                                <main>
                                    <TextField value={day} onChange={(event) => {setDay(event.target.value)}} label={"day"}></TextField>
                                </main>
                            </Grid>
                            <Grid item xs={3}>
                                <main>
                                    <TextField value={month} onChange={(event) => {setMonth(event.target.value)}} label={"Month"}></TextField>
                                </main>
                            </Grid>
                            <Grid item xs={3}>
                                <main>
                                    <TextField value={year} onChange={(event) => {setYear(event.target.value)}} label={"Year"}></TextField>
                                </main>
                            </Grid>
                            <Grid item xs={3}>
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
