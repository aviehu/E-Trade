import {useState} from "react";
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';

function SocketProvider(setMessage) {

    function createSocket(userName) {
        const sock = new SockJS('http://localhost:8080/ws-message');

        let stompClient = Stomp.over(sock);

        sock.onopen = function() {
            console.log('open');
        }

        stompClient.connect({}, function (frame) {
            stompClient.subscribe(`/topic/${userName}`, function (greeting) {
                //you can execute any function here
                console.log(greeting);
                setMessage(greeting.body);
                setTimeout(() => {
                    setMessage(null)
                    console.log("cleared")
                }, 3000)
            });
        });
    }

    return { createSocket }
}

export default SocketProvider
