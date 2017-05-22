var stompClient = null;
function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
    document.getElementById('response').innerHTML = '';
}
// 开启socket连接
function connect() {
    var socket = new SockJS('/socket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        // noticeSocket();
        //method 2
        stompClient.subscribe('/topic/notice', function(greeting){
           showGreeting(JSON.parse(greeting.body).content);
           showGreeting(greeting.body);
        });
        stompClient.subscribe('/user/1/message', function(message){
            // var messageEntity = JSON.parse(message.body);
            showGreeting(message.body);
        });
    });
}
// 断开socket连接
function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}
// 向‘/app/change-notice’服务端发送消息
function sendName() {
    var value = document.getElementById('name').value;
    // stompClient.send("/app/change-notice", {}, value);
    stompClient.send("/user/0/message", {}, value);
}
//    connect();

function showGreeting(message) {
    var response = document.getElementById('response');
    var p = document.createElement('p');
    p.style.wordWrap = 'break-word';
    p.appendChild(document.createTextNode(message));
    response.appendChild(p);
}

var noticeSocket = function () {
    var s = new SockJS('/socket');
    var stompClient = Stomp.over(s);
    stompClient.connect({}, function () {
        console.log('notice socket connected!');
        stompClient.subscribe('/topic/notice', function (data) {
            console.log("receive:" + data.body);
            document.getElementById('content').innerHTML = data.body;
        });
    });
};