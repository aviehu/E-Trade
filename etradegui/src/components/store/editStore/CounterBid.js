import * as React from 'react';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import {TextField} from "@mui/material";
import Grid from "@mui/material/Grid";
import {createTheme, ThemeProvider} from "@mui/material/styles";
import Container from "@mui/material/Container";
import CssBaseline from "@mui/material/CssBaseline";
import Box from "@mui/material/Box";
import {useState} from "react";
import post from "../../util/post";

export default function CounterBid({setSuccessMsg ,open, setOpen, bidId, storeName}) {
    const [newOffer, setNewOffer] = useState(0)
    const mdTheme = createTheme();

    const handleClick = async () => {
        const body = {
            bidId: bidId,
            newOffer: newOffer
        }
        const res = await post(body, `stores/counterbid/${storeName}`)
        const ans = await res.json()
        setSuccessMsg(`a counter bid for - ${newOffer} has been submited`)
        setOpen(false)
    }

    return (
        <div>
            <Dialog
                open={open}
                aria-labelledby="alert-dialog-title"
                aria-describedby="alert-dialog-description"
            >
                <DialogTitle id="alert-dialog-title">
                    {"Counter Bid For Product"}
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
                                            <Grid item xl={12} md={12} xs={12}>
                                                <TextField
                                                    required
                                                    fullWidth
                                                    id="newOffer"
                                                    label="New Offer"
                                                    name="newOffer"
                                                    autoComplete="newOffer"
                                                    onChange={(event) => {setNewOffer(event.target.value)}}
                                                />
                                            </Grid>
                                        </Grid>
                                        <Button
                                            fullWidth
                                            variant="contained"
                                            sx={{mt: 3, mb: 2}}
                                            onClick={handleClick}
                                        >
                                            Counter Offer
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
