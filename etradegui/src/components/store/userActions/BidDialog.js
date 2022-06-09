import * as React from 'react';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import {TextField} from "@mui/material";
import Grid from "@mui/material/Grid";
import {createTheme, ThemeProvider} from "@mui/material/styles";
import Container from "@mui/material/Container";
import CssBaseline from "@mui/material/CssBaseline";
import Box from "@mui/material/Box";
import {useState} from "react";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import InputLabel from "@mui/material/InputLabel";
import Select from "@mui/material/Select";

export default function BidDialog({open, setOpen, productName, handleBid}) {
    const getMonths = () => {
        const months = [1,2,3,4,5,6,7,8,9,10,11,12]
        return months.map((m) => {
            return <MenuItem value={m}>{m}</MenuItem>
        })
    }

    const getYears = () => {
        const years = [2022, 2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030, 2031, 2032]
        return years.map((y) => {
            return <MenuItem value={y}>{y}</MenuItem>
        })
    }
    const [bidAmount, setBidAmount] = useState(0);
    const [holderName, setHolderName] = useState("");
    const [cardNumber, setCardNumber] = useState("")
    const [month, setMonth] = useState(1);
    const [year, setYear] = useState(2022);
    const [cvv, setCvv] = useState(0);
    const [id, setId] = useState(0);
    const [city, setCity] = useState("")
    const [street, setStreet] = useState("")
    const [streetNum, setStreetNum] = useState(0);
    const [apartmentNum, setApartmentNum] = useState(0)
    const [zip, setZip] = useState(0)
    const [country, setCountry] = useState("")
    const mdTheme = createTheme();

    const handleClick = () => {
        const body = {
            productName,
            bidAmount,
            creditCardForm: {
                holderName,
                cardNumber,
                month,
                year,
                cvv,
                id
            },
            supplyAddressForm: {
                city,
                street,
                streetNum,
                apartmentNum,
                zip,
                country
            }
        }
        handleBid(body)
    }

    return (
        <div>
            <Dialog
                open={open}
                aria-labelledby="alert-dialog-title"
                aria-describedby="alert-dialog-description"
            >
                <DialogTitle id="alert-dialog-title">
                    {"Bid For Product"}
                </DialogTitle>
                <DialogContent>
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
                                    <Box component="form" noValidate  sx={{mt: 3}}>
                                        <Grid container spacing={2}>
                                            <Grid item xl={6} md={6} xs={6}>
                                                <TextField
                                                    required
                                                    fullWidth
                                                    id="bidAmount"
                                                    label="Bid Amount"
                                                    name="bidAmount"
                                                    autoComplete="bidAmount"
                                                    onChange={(event) => {setBidAmount(event.target.value)}}
                                                />
                                            </Grid>
                                            <Grid item xl={6} md={6} xs={6}>
                                                <TextField
                                                    required
                                                    fullWidth
                                                    name="holderName"
                                                    label="Card Holder Name"
                                                    id="holderName"
                                                    autoComplete="holderName"
                                                    onChange={(event) => {setHolderName(event.target.value)}}
                                                />
                                            </Grid>
                                            <Grid item xl={12} md={12} xs={12}>
                                                <TextField
                                                    required
                                                    fullWidth
                                                    name="cardNumber"
                                                    label="Card Number"
                                                    id="cardNumber"
                                                    autoComplete="cardNumber"
                                                    onChange={(event) => {setCardNumber(event.target.value)}}
                                                />
                                            </Grid>
                                            <Grid item xl={6} xs={6}>
                                                <FormControl fullWidth>
                                                    <InputLabel id="demo">Month</InputLabel>
                                                    <Select
                                                        labelId="demo"
                                                        id="demo-simple-select"
                                                        label="Predicate Discount"
                                                        value={month}
                                                        onChange={(event) => {
                                                            setMonth(event.target.value)
                                                        }}
                                                    >
                                                        {getMonths()}
                                                    </Select>
                                                </FormControl>
                                            </Grid>
                                            <Grid item xl={6} xs={6}>
                                                <FormControl fullWidth>
                                                    <InputLabel id="demo">Year</InputLabel>
                                                    <Select
                                                        labelId="demo"
                                                        id="demo-simple-select"
                                                        label="Predicate Discount"
                                                        value={year}
                                                        onChange={(event) => {
                                                            setYear(event.target.value)
                                                        }}
                                                    >
                                                        {getYears()}
                                                    </Select>
                                                </FormControl>
                                            </Grid>
                                            <Grid item xl={6} md={6} xs={6}>
                                                <TextField
                                                    required
                                                    fullWidth
                                                    name="cvv"
                                                    label="Cvv"
                                                    id="cvv"
                                                    autoComplete="cvv"
                                                    onChange={(event) => {setCvv(event.target.value)}}
                                                />
                                            </Grid>
                                            <Grid item xl={6} md={6} xs={6}>
                                                <TextField
                                                    required
                                                    fullWidth
                                                    name="id"
                                                    label="Card Owner Id"
                                                    id="id"
                                                    autoComplete="id"
                                                    onChange={(event) => {setId(event.target.value)}}
                                                />
                                            </Grid>
                                            <Grid item xl={6} md={6} xs={6}>
                                                <TextField
                                                    required
                                                    fullWidth
                                                    name="city"
                                                    label="City"
                                                    id="city"
                                                    autoComplete="city"
                                                    onChange={(event) => {setCity(event.target.value)}}
                                                />
                                            </Grid>
                                            <Grid item xl={6} md={6} xs={6}>
                                                <TextField
                                                    required
                                                    fullWidth
                                                    name="street"
                                                    label="Street"
                                                    id="street"
                                                    autoComplete="street"
                                                    onChange={(event) => {setStreet(event.target.value)}}
                                                />
                                            </Grid>
                                            <Grid item xl={6} md={6} xs={6}>
                                                <TextField
                                                    required
                                                    fullWidth
                                                    name="streetNum"
                                                    label="Street Number"
                                                    id="streetNum"
                                                    autoComplete="streetNum"
                                                    onChange={(event) => {setStreetNum(event.target.value)}}
                                                />
                                            </Grid>
                                            <Grid item xl={6} md={6} xs={6}>
                                                <TextField
                                                    required
                                                    fullWidth
                                                    name="apartmentNum"
                                                    label="Apartment Number"
                                                    id="apartmentNum"
                                                    autoComplete="apartmentNum"
                                                    onChange={(event) => {setApartmentNum(event.target.value)}}
                                                />
                                            </Grid>
                                            <Grid item xl={6} md={6} xs={6}>
                                                <TextField
                                                    required
                                                    fullWidth
                                                    name="zip"
                                                    label="Zip"
                                                    id="zip"
                                                    autoComplete="zip"
                                                    onChange={(event) => {setZip(event.target.value)}}
                                                />
                                            </Grid>
                                            <Grid item xl={6} md={6} xs={6}>
                                                <TextField
                                                    required
                                                    fullWidth
                                                    name="country"
                                                    label="Country"
                                                    id="country"
                                                    autoComplete="country"
                                                    onChange={(event) => {setCountry(event.target.value)}}
                                                />
                                            </Grid>
                                        </Grid>
                                        <Button
                                            fullWidth
                                            variant="contained"
                                            sx={{mt: 3, mb: 2}}
                                            onClick={handleClick}
                                        >
                                            Add Bid
                                        </Button>
                                        <Button
                                            fullWidth
                                            variant="contained"
                                            sx={{mt: 3, mb: 2}}
                                            onClick={() => setOpen(false)}
                                        >
                                            Cancel
                                        </Button>
                                    </Box>
                                </Box>
                            </Container>
                        </ThemeProvider>
                    </Grid>
                </DialogContent>
            </Dialog>
        </div>
    );
}
