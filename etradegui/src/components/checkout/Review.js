import * as React from 'react';
import Typography from '@mui/material/Typography';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import post from "../util/post";
import {Fab} from "@mui/material";
import RemoveIcon from '@mui/icons-material/Remove';

export default function Review({products, totalPrice, getMyBasket}) {
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

    return (
        <React.Fragment>
            <Typography variant="h6" gutterBottom>
                Order summary
            </Typography>
            <List disablePadding>
                {products.map((product) => (
                    <ListItem key={product.name} sx={{ py: 1, px: 0 }}>
                        <ListItemText primary={product.productName} secondary={product.desc} />
                        <Typography variant="body2">x {product.amount}</Typography>
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

        </React.Fragment>
    );
}
