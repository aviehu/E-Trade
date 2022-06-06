import * as React from 'react';
import {styled, createTheme, ThemeProvider} from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';
import '../../css/Dashboard.css';
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import {useNavigate, useParams} from 'react-router-dom';
import post from "../post";
import MyAppBar from "../dashboard/MyAppBar";
import MyDrawer from "../dashboard/MyDrawer";
import MyError from "../MyError";
import FormControl from "@mui/material/FormControl";
import InputLabel from "@mui/material/InputLabel";
import Select from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";
import {useState} from "react";
import PredicatesGen from "./PredicatesGen";

const mdTheme = createTheme();

const DashboardContent = () => {
    const { name } = useParams()
    const [open, setOpen] = React.useState(true);
    const [error, setError] = React.useState("")
    const [hasError, setHasError] = React.useState(false)
    const [predicates, setPredicates] = React.useState([{
        index: 0,
        predicateType: 'amount',
        preProduct: '',
        minAmount: 0,
        maxAmount: 0,
        startTime: new Date(),
        endTime: new Date()
    }])
    const [preNum, setPreNum] = React.useState(1)
    const [connectionType, setConnectionType] = useState("and")


    const [policyType, setPolicyType] = React.useState("basket");

    const navigate = useNavigate();

    const cleanPreds = () => {
        const tmp = predicates.filter((pre) => {
            return pre.index <= preNum
        })
        const res = tmp.map(pred => {
            return {
                predicateType: pred.predicateType,
                preProduct: pred.preProduct,
                minAmount: pred.minAmount,
                maxAmount: pred.maxAmount,
                startTime: pred.startTime,
                endTime: pred.endTime
            }
        })
        return res
    }


    const handleSubmit = async (event) => {
        event.preventDefault();
        const data = new FormData(event.currentTarget);
        const body = {
            policyOn: data.get("policyOn"),
            description: data.get("description"),
            type: policyType,
            connectionType: connectionType,
            predicates: cleanPreds()
        }
        try {
            const res = await post(body, `stores/addpolicy/${name}`)
            const boolRes = await res.json()
            if(boolRes.val) {
                navigate(`/store/edit/${name}`);
            } else {
                setError(boolRes.err)
                setHasError(true)
            }
        } catch (e) {

        }
    };

    const handleFormChange = (index, att, val) => {
        let data = [...predicates];
        if(!data[index]) {
            data.push({index: index})
        }
        data[index][att] = val
        setPredicates(data)
    }

    return (
        <ThemeProvider theme={mdTheme}>
            <Box sx={{display: 'flex'}}>
                <CssBaseline/>
                <MyError open={hasError} setOpen={setHasError} error={error}/>
                <MyAppBar title={`${name} - Add Policy`} open={open} toggleDrawer={() => {setOpen(!open)}}/>
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
                    <Toolbar/>
                    <Container maxWidth="lg" sx={{mt: 4, mb: 4}}>
                        {/*Here the form of the create store*/}
                        <Grid container spacing={3}>
                            <ThemeProvider theme={mdTheme}>
                                <Container component="main" maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
                                    <CssBaseline/>
                                    <Box
                                        sx={{
                                            marginTop: 8,
                                            display: 'flex',
                                            flexDirection: 'column',
                                            alignItems: 'center',
                                        }}
                                    >
                                        <Box component="form" noValidate onSubmit={handleSubmit} sx={{mt: 3}}>
                                            <Grid container spacing={2}>
                                                <Grid item xl={12}>
                                                    <FormControl fullWidth>
                                                        <InputLabel id="demo">Policy Type</InputLabel>
                                                        <Select
                                                            labelId="demo"
                                                            id="demo-simple-select"
                                                            label="Policy Type"
                                                            value={policyType}
                                                            onChange={(event) => setPolicyType(event.target.value)}
                                                        >
                                                            <MenuItem value={"basket"}>Basket</MenuItem>
                                                            <MenuItem value={"category"}>Category</MenuItem>
                                                            <MenuItem value={"product"}>Product</MenuItem>
                                                        </Select>
                                                    </FormControl>
                                                </Grid>
                                                {
                                                    policyType === "category" || policyType === "product" ?
                                                        <Grid item xl={12}>
                                                            <TextField
                                                                fullWidth
                                                                id="policyOn"
                                                                label={ policyType === "category" ? "Category For Policy" : "Product For Policy"}
                                                                name="policyOn"
                                                                autoComplete="policyOn"
                                                            />
                                                        </Grid> : null
                                                }
                                                <Grid item xl={12}>
                                                    <TextField
                                                        fullWidth
                                                        id="description"
                                                        label="Description"
                                                        name="description"
                                                        autoComplete="description"
                                                    />
                                                </Grid>
                                                <PredicatesGen preNum={preNum} setConnectionType={setConnectionType} connectionType={connectionType} handleFormChange={handleFormChange}></PredicatesGen>

                                            </Grid>
                                            <Button
                                                fullWidth
                                                variant="contained"
                                                sx={{mt: 3, mb: 2}}
                                                onClick={() => {
                                                    setPreNum(preNum + 1)
                                                    let data = [...predicates]
                                                    data.push({
                                                        index: preNum,
                                                        predicateType: 'amount',
                                                        preProduct: '',
                                                        minAmount: 0,
                                                        maxAmount: 0,
                                                        startTime: new Date(),
                                                        endTime: new Date(),
                                                    })
                                                    setPredicates(data)
                                                }}
                                            >
                                                Add Predicate
                                            </Button>
                                            <Button
                                                fullWidth
                                                variant="contained"
                                                sx={{mt: 3, mb: 2}}
                                                onClick={() => {
                                                    if(preNum > 1) {
                                                        setPreNum(preNum - 1)
                                                    }
                                                    let data = [...predicates]
                                                    data.pop();
                                                    setPredicates(data)
                                                }}
                                            >
                                                Remove Predicate
                                            </Button>
                                            <Button
                                                type="submit"
                                                fullWidth
                                                variant="contained"
                                                sx={{mt: 3, mb: 2}}
                                            >
                                                Add Policy
                                            </Button>
                                        </Box>
                                    </Box>
                                </Container>
                            </ThemeProvider>
                        </Grid>
                    </Container>
                </Box>
            </Box>
        </ThemeProvider>
    );
}

export default function Dashboard() {
    return <DashboardContent/>;
}

