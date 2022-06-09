import Toolbar from "@mui/material/Toolbar";
import IconButton from "@mui/material/IconButton";
import MenuIcon from "@mui/icons-material/Menu";
import Typography from "@mui/material/Typography";
import Badge from "@mui/material/Badge";
import NotificationsIcon from "@mui/icons-material/Notifications";
import {styled} from "@mui/material/styles";
import MuiAppBar from "@mui/material/AppBar";
import {useNavigate} from "react-router-dom";
import {useState, useEffect} from "react";
import get from "../util/get";
import LogoutIcon from '@mui/icons-material/Logout';
import SocketProvider from "../util/SocketProvider";
import MessageDialog from "../util/MessageDialog";
import * as React from "react";

const drawerWidth = 240;

const AppBar = styled(MuiAppBar, {
    shouldForwardProp: (prop) => prop !== 'open',
})(({theme, open}) => ({
    zIndex: theme.zIndex.drawer + 1,
    transition: theme.transitions.create(['width', 'margin'], {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.leavingScreen,
    }),
    ...(open && {
        marginLeft: drawerWidth,
        width: `calc(100% - ${drawerWidth}px)`,
        transition: theme.transitions.create(['width', 'margin'], {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.enteringScreen,
        }),
    }),
}));


export default function MyAppBar({open, toggleDrawer, title}) {
    const navigate = useNavigate();
    const [notifications, setNotifications] = useState([])
    const [message, setMessage] = useState(null)


    async function handleLogout() {
        const res = await get("users/logout")
        const ans = await res.json()
        navigate("/")
    }

    useEffect(() => {
        const { createSocket } = SocketProvider(setMessage);
        createSocket(localStorage.getItem("userName"))
        async function getMessages() {
            const note = await get('users/messages')
            const notes = await note.json()
            if (notes.val) {
                console.log(notes.val)
                setNotifications(notes.val)
            }
        }
        getMessages()

    }, [])

    return (
        <AppBar position="absolute" open={open}>
            <MessageDialog message={message} open={message !== null} handleClose={() => setMessage(null)}/>
            <Toolbar
                sx={{
                    pr: '24px', // keep right padding when drawer closed
                }}
            >
                <IconButton
                    edge="start"
                    color="inherit"
                    aria-label="open drawer"
                    onClick={toggleDrawer}
                    sx={{
                        marginRight: '36px',
                        ...(open && {display: 'none'}),
                    }}
                >
                    <MenuIcon/>
                </IconButton>
                <Typography
                    component="h1"
                    variant="h6"
                    color="inherit"
                    noWrap
                    sx={{flexGrow: 1}}
                >
                    E-trade - {title}
                </Typography>
                <IconButton color="inherit">
                    <Badge color="secondary">
                        <LogoutIcon onClick={handleLogout}/>
                    </Badge>
                </IconButton>
                <IconButton color="inherit">
                    <Badge badgeContent={notifications.length} color="secondary">
                        <NotificationsIcon onClick={() => navigate('/mymessages')}/>
                    </Badge>
                </IconButton>
            </Toolbar>
        </AppBar>
    )
}
