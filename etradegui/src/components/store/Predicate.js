import Grid from "@mui/material/Grid";
import FormControl from "@mui/material/FormControl";
import InputLabel from "@mui/material/InputLabel";
import Select from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";
import TextField from "@mui/material/TextField";
import {LocalizationProvider} from "@mui/x-date-pickers/LocalizationProvider";
import {AdapterDateFns} from "@mui/x-date-pickers/AdapterDateFns";
import {DesktopDatePicker} from "@mui/x-date-pickers/DesktopDatePicker";

export default function Predicate({isPredicate, predicateType, setPredicateType, connectionType, setConnectionType, startTime, setStartTime, endTime, setEndTime }) {
    if (isPredicate) {
        return [
                    <Grid item xl={12}>
                        <FormControl fullWidth>
                            <InputLabel id="demo">Predicate Type</InputLabel>
                            <Select
                                labelId="demo"
                                id="demo-simple-select"
                                label="Predicate Discount"
                                value={predicateType}
                                onChange={(event) => setPredicateType(event.target.value)}
                            >
                                <MenuItem value={"amount"}>Product Amount</MenuItem>
                                <MenuItem value={"basket"}>Basket Value</MenuItem>
                                <MenuItem value={"time"}>Time</MenuItem>
                            </Select>
                        </FormControl>
                    </Grid>,
                    <Grid item xl={12}>
                        <FormControl fullWidth>
                            <InputLabel id="demo">Connection Type</InputLabel>
                            <Select
                                labelId="demo"
                                id="demo-simple-select"
                                label="Connection Type"
                                value={connectionType}
                                onChange={(event) => setConnectionType(event.target.value)}
                            >
                                <MenuItem value={"and"}>And</MenuItem>
                                <MenuItem value={"or"}>Or</MenuItem>
                            </Select>
                        </FormControl>
                    </Grid>,
                predicateType === "amount" ?
                <Grid item xl={12}>
                    <TextField
                        fullWidth
                        id="preProduct"
                        label="Product For Predicate"
                        name="preProduct"
                        autoComplete="preProduct"
                    />
                </Grid> : null,
                (predicateType === "amount" || predicateType === "basket") ?
                <Grid item xl={12}>
                    <TextField
                        fullWidth
                        id="minAmount"
                        label={predicateType === "amount" ? `Min Product Amount` : 'Min Basket Value'}
                        name="minAmount"
                        autoComplete="minAmount"
                    />
                </Grid> : null,
                (predicateType === "amount" || predicateType === "basket") ?
                <Grid item xl={12}>
                    <TextField
                        fullWidth
                        id="maxAmount"
                        label={predicateType === "amount" ? `Max Product Amount` : 'Max Basket Value'}
                        name="maxAmount"
                        autoComplete="maxAmount"
                    />
                </Grid> : null,
                predicateType === "time" ?
                <Grid item xs={12} md={6}>
                    <LocalizationProvider dateAdapter={AdapterDateFns}>
                        <DesktopDatePicker
                            label="Start Time"
                            value={startTime}
                            onChange={(newValue) => {
                                setStartTime(newValue);
                            }}
                            renderInput={(params) => <TextField {...params} />}
                        />
                    </LocalizationProvider>
                </Grid> : null,
                predicateType === "time" ?
                <Grid item xs={12} md={6}>
                    <LocalizationProvider dateAdapter={AdapterDateFns}>
                        <DesktopDatePicker
                            label="End Time"
                            value={endTime}
                            onChange={(newValue) => {
                                setEndTime(newValue);
                            }}
                            renderInput={(params) => <TextField {...params} />}
                        />
                    </LocalizationProvider>
                </Grid> : null
        ]
    }
    return null

}
