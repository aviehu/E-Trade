import * as React from 'react';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import {TextField} from "@mui/material";

export default function AlertDialog({open, handleBid, setAmount}) {

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
                    <DialogContentText id="alert-dialog-description">
                        Enter the price you wish to bid
                    </DialogContentText>
                </DialogContent>
                <DialogActions>
                    <TextField label="Amount" onChange={(event) => {
                        setAmount(event.target.value)
                    }}/>
                    <Button onClick={handleBid} autoFocus>
                        Agree
                    </Button>
                </DialogActions>
            </Dialog>
        </div>
    );
}
