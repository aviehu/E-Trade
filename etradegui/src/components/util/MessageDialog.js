import * as React from 'react';
import Snackbar from '@mui/material/Snackbar';

export default function messageDialog({ open , handleClose, message}) {

    function parseMsg(msg) {
        const ans = JSON.parse(msg)
        if(!ans) {
            return msg
        }
        return ans.message
    }

    return (
        <Snackbar
            open={open}
            onClose={handleClose}
            message={parseMsg(message)}
        />
    );
}
