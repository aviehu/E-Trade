import * as React from 'react';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import ListSubheader from '@mui/material/ListSubheader';
import DashboardIcon from '@mui/icons-material/Dashboard';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import HomeIcon from '@mui/icons-material/Home';
import AddIcon from '@mui/icons-material/Add';

import PeopleIcon from '@mui/icons-material/People';
import BarChartIcon from '@mui/icons-material/BarChart';
import LayersIcon from '@mui/icons-material/Layers';
import AssignmentIcon from '@mui/icons-material/Assignment';
import Link from "@mui/material/Link";

export const mainListItems = (
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

        <Link href="/mystores">
            <ListItemButton>
                <ListItemIcon>
                    <DashboardIcon/>
                </ListItemIcon>
                <ListItemText primary="My stores"/>
            </ListItemButton>
        </Link>

        <Link href="/createstore">
            <ListItemButton>
                <ListItemIcon>
                    <AddIcon/>
                </ListItemIcon>
                <ListItemText primary="Create Store"/>
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
