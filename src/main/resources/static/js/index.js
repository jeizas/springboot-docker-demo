var stompClient = null;
function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
    document.getElementById('response').innerHTML = '';
}
/**
 * 开启socket连接
 */
function connect() {
    //获取登录人ID
    var usrId = document.getElementById('nick').value;
    if (usrId == "") {
        return;
    }
    $.ajax({url: '/login?id=' + usrId, success:function(result){
        $('#nick').val(result.obj.name);

        updateLoginUsers(result);
    }});

    var socket = new SockJS('/socket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        // noticeSocket();
        //method 2
        stompClient.subscribe('/topic/notice', function(greeting){
           showGreeting(greeting.body);
        });
        stompClient.subscribe('/user/' + usrId + '/message', function(message){
            // var messageEntity = JSON.parse(message.body);
            showGreeting(message.body);
        });
    });
}
/**
 * 断开socket连接
 */
function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

/**
 * 向‘/app/change-notice’服务端发送消息
 */
function sendName() {
    var msg = $('#name').val();
    var usrId = $("#usrIds").val();

    if (usrId == 0) {
        stompClient.send("/app/change-notice", {}, msg);
    } else {
        stompClient.send('/user/' + usrId + '/message', {}, usrId + "@" + msg);
    }
}

/**
 * 收到消息
 * 方法二
 * @param message
 */
function showGreeting(message) {
    if (isJSON(message)) {
        updateLoginUsers(message)
    } else {
        var response = document.getElementById('response');
        var p = document.createElement('p');
        p.style.wordWrap = 'break-word';
        p.appendChild(document.createTextNode(message));
        response.appendChild(p);
    }

}

/**
 * 收到消息
 * 方法一
 * @param message
 */
var noticeSocket = function () {
    var s = new SockJS('/socket');
    var stompClient = Stomp.over(s);
    stompClient.connect({}, function () {
        console.log('notice socket connected!');
        stompClient.subscribe('/topic/notice', function (data) {
            document.getElementById('content').innerHTML = data.body;
        });
    });
};

var updateLoginUsers = function (msg) {
    var data = msg.list;
    if (msg.list == undefined) {
        // data = eval(" ' "+msg+" ' ").list;
        data = JSON.parse(msg).list;
    }
    $('#usrIds').empty();
    $('#usrIds').append($("<option>").val("0").text("select.."));
    for (var i=0; i<data.length; i++) {
        $('#usrIds').append($("<option>").val(data[i].id).text(data[i].name));
    }
}

function isJSON(str) {
    if (typeof str == 'string') {
        try {
            JSON.parse(str);
            return true;
        } catch(e) {
            console.log(e);
            return false;
        }
    }
    console.log('It is not a string!')
}