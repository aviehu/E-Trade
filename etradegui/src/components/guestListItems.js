import * as React from 'react';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import DashboardIcon from '@mui/icons-material/Dashboard';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import HomeIcon from '@mui/icons-material/Home';
import AddIcon from '@mui/icons-material/Add';
import SearchIcon from '@mui/icons-material/Search';
import Link from "@mui/material/Link";

export const guestListItems = (
    <React.Fragment>

        <Link href="/etrade">
            <ListItemButton>
                <ListItemIcon>
                    <HomeIcon/>
                </ListItemIcon>
                <ListItemText primary="E-Trade Market"/>
            </ListItemButton>
        </Link>

        <Link href="/cart">
            <ListItemButton>
                <ListItemIcon>
                    <ShoppingCartIcon/>
                </ListItemIcon>
                <ListItemText primary="My Cart"/>
            </ListItemButton>
        </Link>
        <Link href="/search">
            <ListItemButton>
                <ListItemIcon>
                    <SearchIcon/>
                </ListItemIcon>
                <ListItemText primary="Search Market"/>
            </ListItemButton>
        </Link>

        {/*<ListItemButton>*/}
        {/*    <ListItemIcon>*/}
        {/*        <LayersIcon />*/}
        {/*    </ListItemIcon>*/}
        {/*    <ListItemText primary="Integrations" />*/}
        {/*</ListItemButton>*/}
    </React.Fragment>
);
