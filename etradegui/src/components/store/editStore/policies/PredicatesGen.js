import Predicate from "./Predicate";
import Grid from "@mui/material/Grid";
import FormControl from "@mui/material/FormControl";
import InputLabel from "@mui/material/InputLabel";
import Select from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";

export default function PredicatesGen({preNum, handleFormChange, connectionType, setConnectionType}) {
    const ans = [
        <Grid item xl={12}>
            <FormControl fullWidth>
                <InputLabel id="demo">Connection Type</InputLabel>
                <Select
                    labelId="demo"
                    id="demo-simple-select"
                    label="Connection Type"
                    value={connectionType}
                    onChange={(event) => {
                        setConnectionType(event.target.value)
                    }}
                >
                    <MenuItem value={"and"}>And</MenuItem>
                    <MenuItem value={"or"}>Or</MenuItem>
                </Select>
            </FormControl>
        </Grid>
    ]
    for(let i = 0; i < preNum; i ++) {
        ans.push(<Predicate handleFormChange={handleFormChange} index={i}/>)
    }
    return ans;
}
