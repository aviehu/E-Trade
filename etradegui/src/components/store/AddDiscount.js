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
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import PredicatesGen from "./PredicatesGen";
import {useEffect, useState} from "react";


const mdTheme = createTheme();

const DashboardContent = () => {
    const { name } = useParams()
    const [open, setOpen] = React.useState(true);
    const [error, setError] = React.useState("")
    const [hasError, setHasError] = React.useState(false)
    const [isPredicate, setIsPredicate] = React.useState(false);
    const [discountType, setDiscountType] = React.useState("product");
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

    const handleFormChange = (index, att, val) => {
        let data = [...predicates];
        if(!data[index]) {
            data.push({index: index})
        }
        data[index][att] = val
        setPredicates(data)
    }

    const navigate = useNavigate();

    const simpleDiscount = async (event) => {
        event.preventDefault();
        const data = new FormData(event.currentTarget);
        const body = {
            discountOn: data.get("discountOn"),
            discountPercentage: data.get("discountPer"),
            description: data.get("description"),
            type: discountType
        }
        try {
            const res = await post(body, `stores/addsimplediscount/${name}`)
            const boolRes = await res.json()
            if(boolRes.val) {
                navigate(`/store/edit/${name}`);
            } else {
                setError(boolRes.err)
                setHasError(true)
            }
        } catch (e) {

        }
    }

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

    const predicateDiscount = async (event) => {
        event.preventDefault();
        const data = new FormData(event.currentTarget);
        const body = {
            discountOn: data.get("discountOn"),
            discountPercentage: data.get("discountPer"),
            description: data.get("description"),
            type: discountType,
            connectionType: connectionType,
            predicates: cleanPreds()
        }
        try {
            const res = await post(body, `stores/addprediscount/${name}`)
            const boolRes = await res.json()
            if(boolRes.val) {
                navigate(`/store/edit/${name}`);
            } else {
                setError(boolRes.err)
                setHasError(true)
            }
        } catch (e) {

        }
    }

    const handleSubmit = async (event) => {
        if(isPredicate) {
            await predicateDiscount(event)
        } else {
            await simpleDiscount(event)
        }
    };

    return (
        <ThemeProvider theme={mdTheme}>
            <Box sx={{display: 'flex'}}>
                <CssBaseline/>
                <MyError open={hasError} setOpen={setHasError} error={error}/>
                <MyAppBar title={`${name} - Add Discount`} open={open} toggleDrawer={() => {setOpen(!open)}}/>
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
                                                        <InputLabel id="demo">Predicate Discount</InputLabel>
                                                        <Select
                                                            labelId="demo"
                                                            id="demo-simple-select"
                                                            label="Predicate Discount"
                                                            value={isPredicate}
                                                            onChange={(event) => {
                                                                setIsPredicate(event.target.value)
                                                            }}
                                                        >
                                                            <MenuItem value={false}>Simple Discount</MenuItem>
                                                            <MenuItem value={true}>Predicate Discount</MenuItem>
                                                        </Select>
                                                    </FormControl>
                                                </Grid>
                                                <Grid item xl={12}>
                                                    <FormControl fullWidth>
                                                        <InputLabel id="demo-simple-select-label">Type</InputLabel>
                                                        <Select
                                                            labelId="demo-simple-select-label"
                                                            id="demo-simple-select"
                                                            label="Type"
                                                            value={discountType}
                                                            onChange={(event) => setDiscountType(event.target.value)}
                                                        >
                                                            <MenuItem value={"product"}>Product</MenuItem>
                                                            <MenuItem value={"category"}>Category</MenuItem>
                                                            <MenuItem value={"store"}>Store</MenuItem>
                                                        </Select>
                                                    </FormControl>
                                                </Grid>
                                                {
                                                discountType !== "store" ? <Grid item xl={12}>
                                                    <TextField
                                                        fullWidth
                                                        id="discountOn"
                                                        label={discountType === "category" ? "Category Name" : "Product Name"}
                                                        name="discountOn"
                                                        autoComplete="discountOn"
                                                    />
                                                </Grid> : null
                                                }
                                                <Grid item xl={12}>
                                                    <TextField
                                                        fullWidth
                                                        id="discountPer"
                                                        label="Discount Percentage"
                                                        name="discountPer"
                                                        autoComplete="discountPer"
                                                    />
                                                </Grid>
                                                <Grid item xl={12}>
                                                    <TextField
                                                        fullWidth
                                                        id="description"
                                                        label="Description"
                                                        name="description"
                                                        autoComplete="description"
                                                    />
                                                </Grid>
                                                {isPredicate ? <PredicatesGen preNum={preNum} connectionType={connectionType} setConnectionType={setConnectionType} handleFormChange={handleFormChange}/> : null}
                                            </Grid>
                                            {isPredicate ?
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
                                            </Button> : null
                                            }
                                            {isPredicate ?
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
                                            </Button> : null
                                            }

                                            <Button
                                                type="submit"
                                                fullWidth
                                                variant="contained"
                                                sx={{mt: 3, mb: 2}}
                                            >
                                                Add Discount
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

