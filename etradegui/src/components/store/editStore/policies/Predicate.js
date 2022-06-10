import Grid from "@mui/material/Grid";
import FormControl from "@mui/material/FormControl";
import InputLabel from "@mui/material/InputLabel";
import Select from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";
import TextField from "@mui/material/TextField";
import {LocalizationProvider} from "@mui/x-date-pickers/LocalizationProvider";
import {AdapterDateFns} from "@mui/x-date-pickers/AdapterDateFns";
import {DesktopDatePicker} from "@mui/x-date-pickers/DesktopDatePicker";
import {useState, useEffect} from "react";
import Box from "@mui/material/Box";


export default function Predicate({handleFormChange, index}) {
    const [predicateType, setPredicateType] = useState("amount")
    const [preProduct, setPreProduct] = useState(null)
    const [minAmount, setMinAmount] = useState(0)
    const [maxAmount, setMaxAmount] = useState(0)
    const [startTime, setStartTime] = useState(new Date())
    const [endTime, setEndTime] = useState(new Date())

    const updateState = (att, val) => {
        handleFormChange(index, att, val)
    }
    return (
        <Box sx={{mt: 3}}>
            <Grid container spacing={2}>
                <Grid xl={1}>
                    <h4>{`Predicate ${index + 1}`}</h4>
                </Grid>
                <Grid item xl={12}>
                    <FormControl fullWidth>
                        <InputLabel id="demo">Predicate Type</InputLabel>
                        <Select
                            labelId="demo"
                            id="demo-simple-select"
                            label="Predicate Discount"
                            value={predicateType}
                            onChange={(event) => {
                                setPredicateType(event.target.value)
                                updateState("predicateType", event.target.value)
                            }}
                        >
                            <MenuItem value={"amount"}>Product Amount</MenuItem>
                            <MenuItem value={"basket"}>Basket Value</MenuItem>
                            <MenuItem value={"time"}>Time</MenuItem>
                        </Select>
                    </FormControl>
                </Grid>
                {predicateType === "amount" ?
                <Grid item xl={12}>
                    <TextField
                        fullWidth
                        id="preProduct"
                        label="Product For Predicate"
                        name="preProduct"
                        autoComplete="preProduct"
                        value={preProduct}
                        onChange={event => {
                            setPreProduct(event.target.value)
                            updateState("preProduct", event.target.value)
                        }}
                    />
                </Grid> : null}
                {(predicateType === "amount" || predicateType === "basket") ?
                <Grid item xl={12}>
                    <TextField
                        fullWidth
                        id="minAmount"
                        id="minAmount"
                        label={predicateType === "amount" ? `Min Product Amount` : 'Min Basket Value'}
                        name="minAmount"
                        value={minAmount}
                        autoComplete="minAmount"
                        onChange={event => {
                            setMinAmount(event.target.value)
                            updateState('minAmount', event.target.value)
                        }}
                    />
                </Grid> : null}
                {(predicateType === "amount" || predicateType === "basket") ?
                    <Grid item xl={12}>
                        <TextField
                            fullWidth
                            id="maxAmount"
                            label={predicateType === "amount" ? `Max Product Amount` : 'Max Basket Value'}
                            name="maxAmount"
                            value={maxAmount}
                            onChange={event => {
                                setMaxAmount(event.target.value)
                                updateState('maxAmount', event.target.value)
                            }}
                            autoComplete="maxAmount"
                        />
                    </Grid> : null}
                {predicateType === "time" ?
                    <Grid item xs={12} md={6}>
                        <LocalizationProvider dateAdapter={AdapterDateFns}>
                            <DesktopDatePicker
                                label="Start Time"
                                value={startTime}
                                onChange={(newValue) => {
                                    setStartTime(newValue);
                                    updateState('startTime', newValue)
                                }}
                                renderInput={(params) => <TextField {...params} />}
                            />
                        </LocalizationProvider>
                    </Grid> : null}
                {                predicateType === "time" ?
                    <Grid item xs={12} md={6}>
                        <LocalizationProvider dateAdapter={AdapterDateFns}>
                            <DesktopDatePicker
                                label="End Time"
                                value={endTime}
                                onChange={(newValue) => {
                                    setEndTime(newValue);
                                    updateState('endTime', newValue)
                                }}
                                renderInput={(params) => <TextField {...params} />}
                            />
                        </LocalizationProvider>
                    </Grid> : null}
            </Grid>
        </Box>
    )
}
