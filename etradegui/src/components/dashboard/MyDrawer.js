import Toolbar from "@mui/material/Toolbar";
import IconButton from "@mui/material/IconButton";
import ChevronLeftIcon from "@mui/icons-material/ChevronLeft";
import Divider from "@mui/material/Divider";
import List from "@mui/material/List";
import {mainListItems} from "../util/listItems";
import {guestListItems} from '../util/guestListItems'
import Link from "@mui/material/Link";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import PersonRemoveIcon from "@mui/icons-material/PersonRemove";
import ListItemText from "@mui/material/ListItemText";
import RemoveShoppingCartIcon from "@mui/icons-material/RemoveShoppingCart";
import {useEffect, useState} from "react";
import get from "../util/get";
import {styled} from "@mui/material/styles";
import MuiDrawer from "@mui/material/Drawer";
import SearchIcon from '@mui/icons-material/Search';
import EqualizerIcon from '@mui/icons-material/Equalizer';


const drawerWidth = 240;

const Drawer = styled(MuiDrawer, {shouldForwardProp: (prop) => prop !== 'open'})(
    ({theme, open}) => ({
        '& .MuiDrawer-paper': {
            position: 'relative',
            whiteSpace: 'nowrap',
            width: drawerWidth,
            transition: theme.transitions.create('width', {
                easing: theme.transitions.easing.sharp,
                duration: theme.transitions.duration.enteringScreen,
            }),
            boxSizing: 'border-box',
            ...(!open && {
                overflowX: 'hidden',
                transition: theme.transitions.create('width', {
                    easing: theme.transitions.easing.sharp,
                    duration: theme.transitions.duration.leavingScreen,
                }),
                width: theme.spacing(7),
                [theme.breakpoints.up('sm')]: {
                    width: theme.spacing(9),
                },
            }),
        },
    }),
);

export default function MyDrawer({open, setOpen}) {
    const [isAdmin, setIsAdmin] = useState(false)
    const [isGuest, setIsGuest] = useState(true)
    useEffect(() => {
        const name = localStorage.getItem("userName");
        setIsGuest(name.startsWith("guest"))
        async function checkAdmin() {
            const res = await get('users/isadmin')
            const ans = await res.json()
            if(ans.val) {
                setIsAdmin(ans.val);
            }
        }
        checkAdmin()
    }, [])


    const toggleDrawer = () => {
        setOpen(!open);
    };

    return (
        <Drawer variant="permanent" open={open}>
            <Toolbar
                sx={{
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'flex-end',
                    px: [1],
                }}
            >
                <IconButton onClick={toggleDrawer}>
                    <ChevronLeftIcon/>
                </IconButton>
            </Toolbar>
            <Divider/>
            <List component="nav">
                {isGuest ? guestListItems : mainListItems}
                <Divider sx={{my: 1}}/>
                {isAdmin ? <Link href="/admin/removemember">
                    <ListItemButton>
                        <ListItemIcon>
                            <PersonRemoveIcon/>
                        </ListItemIcon>
                        <ListItemText primary="Remove Member"/>
                    </ListItemButton>
                </Link> : null}
                {isAdmin ? <Link href="/admin/closestore">
                    <ListItemButton>
                        <ListItemIcon>
                            <RemoveShoppingCartIcon/>
                        </ListItemIcon>
                        <ListItemText primary="Close Shop"/>
                    </ListItemButton>
                </Link> : null}
                {isAdmin ? <Link href="/admin/viewtraffic">
                    <ListItemButton>
                        <ListItemIcon>
                            <EqualizerIcon/>
                        </ListItemIcon>
                        <ListItemText primary="View Traffic"/>
                    </ListItemButton>
                </Link> : null}
            </List>
        </Drawer>
    )
}
