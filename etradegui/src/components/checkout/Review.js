import * as React from 'react';
import Typography from '@mui/material/Typography';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import post from "../util/post";
import {Fab} from "@mui/material";
import RemoveIcon from '@mui/icons-material/Remove';
import Button from "@mui/material/Button";
import {useState} from "react";
import ChangeAmountDialog from "./ChangeAmountDialog";
import MyError from "../util/MyError";

export default function Review({products, totalPrice, getMyBasket}) {
    const [error, setError] = useState('');
    const [hasError, setHasError] = useState(false);
    const [openDialog, setOpenDialog] = useState(false)
    const [productName, setProductName] = useState('')
    const [storeName, setStoreName] = useState('')
    const [amount, setAmount] = useState(0)

    const handleRemove = async (productName, amount, storeName) => {
        const body = {
            productName: productName,
            storeName: storeName,
            quantity: amount
        }
        const ans = await post(body, 'stores/removeproductfromcart')
        const res = await ans.json();
        getMyBasket()
    }

    const handleChange = async () => {
        const body = {
            productName: productName,
            storeName: storeName,
            quantity: amount
        }
        const res =  await post(body,'stores/editproductincart');
        const ans = await res.json()
        if(!ans.val) {
            setError(ans.err)
            setHasError(true)
        }
        getMyBasket()
        setOpenDialog(false)
    }

    return (
        <React.Fragment>
            <MyError open={hasError} setOpen={setHasError} error={error}/>
            <Typography variant="h6" gutterBottom>
                Order summary
            </Typography>
            <List disablePadding>
                {products.map((product) => (
                    <ListItem key={product.name} sx={{ py: 1, px: 0 }}>
                        <ListItemText primary={product.productName} secondary={product.desc} />
                        <Typography variant="body2">x {product.amount}</Typography>
                        <Button onClick={() => {
                            setStoreName(product.storeName)
                            setAmount(product.amount)
                            setProductName(product.productName);
                            setOpenDialog(true);
                        }}>Edit</Button>
                        <Fab onClick={() => handleRemove(product.productName, product.amount, product.storeName)} sx={{ml: 5}} size="small" color="primary" aria-label="add">
                            <RemoveIcon />
                        </Fab>
                    </ListItem>
                ))}
                <ListItem sx={{ py: 1, px: 0 }}>
                    <ListItemText primary="Total" />
                    <Typography variant="subtitle1" sx={{ fontWeight: 700 }}>
                        {totalPrice}
                    </Typography>
                </ListItem>
            </List>
            <ChangeAmountDialog open={openDialog} amount={amount} setAmount={setAmount} handleChange={handleChange}/>
        </React.Fragment>
    );
}
