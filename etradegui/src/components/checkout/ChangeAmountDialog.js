import Dialog from "@mui/material/Dialog";
import DialogTitle from "@mui/material/DialogTitle";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogActions from "@mui/material/DialogActions";
import FormControl from "@mui/material/FormControl";
import {TextField} from "@mui/material";
import Button from "@mui/material/Button";

export default function ChangeAmountDialog({open, amount ,setAmount, handleChange}) {
    return (
        <div>
            <Dialog
                open={open}
                aria-labelledby="alert-dialog-title"
                aria-describedby="alert-dialog-description"
            >
                <DialogTitle id="alert-dialog-title">
                    {"Edit Product"}
                </DialogTitle>
                <DialogContent>
                    <DialogContentText id="alert-dialog-description">
                    </DialogContentText>
                </DialogContent>
                <DialogActions>
                    <FormControl fullWidth>
                        <TextField
                            required
                            fullWidth
                            label="Amount"
                            value={amount}
                            onChange={(event) => {
                                setAmount(event.target.value);
                            }}
                        />
                        <br/>
                    </FormControl>
                    <Button onClick={handleChange} autoFocus>
                        Agree
                    </Button>
                </DialogActions>
            </Dialog>
        </div>
    )
}
