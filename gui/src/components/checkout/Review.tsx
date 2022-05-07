import * as React from 'react';
import Typography from '@mui/material/Typography';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import Grid from '@mui/material/Grid';

const totalSum = "$34.06"
const products = [
    {
        name: 'Product 1',
        desc: 'description',
        price: '$9.99',
    },
    {
        name: 'Product 2',
        desc: 'description',
        price: '$3.45',
    },
    {
        name: 'Product 3',
        desc: 'description',
        price: '$6.51',
    },
    {
        name: 'Product 4',
        desc: 'description',
        price: '$14.11',
    },
    { name: 'Shipping', desc: '', price: 'Free' },
];

export default function Review() {
    return (
        <React.Fragment>
            <Typography variant="h6" gutterBottom>
                Order summary
            </Typography>
            <List disablePadding>
                {products.map((product) => (
                    <ListItem key={product.name} sx={{ py: 1, px: 0 }}>
                        <ListItemText primary={product.name} secondary={product.desc} />
                        <Typography variant="body2">{product.price}</Typography>
                    </ListItem>
                ))}
                <ListItem sx={{ py: 1, px: 0 }}>
                    <ListItemText primary="Total" />
                    <Typography variant="subtitle1" sx={{ fontWeight: 700 }}>
                        {totalSum}
                    </Typography>
                </ListItem>
            </List>

        </React.Fragment>
    );
}