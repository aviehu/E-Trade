import * as React from 'react';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import {TextField} from "@mui/material";
import FormControl from "@mui/material/FormControl";
import InputLabel from "@mui/material/InputLabel";
import Select from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";

export default function EditProduct({open, handleChange, amount, setAmount, price, setPrice}) {
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
                        Choose what to edit
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
                        <TextField
                            required
                            fullWidth
                            label="Price"
                            value={price}
                            onChange={(event) => {
                                setPrice(event.target.value);
                            }}
                        />
                    </FormControl>
                    <Button onClick={handleChange} autoFocus>
                        Agree
                    </Button>
                </DialogActions>
            </Dialog>
        </div>
    );
}
