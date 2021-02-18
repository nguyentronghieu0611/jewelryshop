// var connectingElement = document.querySelector('#connecting');
var stompClient = null;
var username = null;


function connect() {
    username = $('#username').text().trim();
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
}

// Connect to WebSocket Server.
connect();

function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/publicNotify', onMessageReceived);
    // connectingElement.classList.add('hidden');
    // alert('connect socket success');
}


function onError(error) {
    // connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    // connectingElement.style.color = 'red';
    console.log('connect socket fail:'+error);
}


function onMessageReceived(payload) {
    debugger;
    var message = JSON.parse(payload.body);
    new Toast("info",message.content);
}
