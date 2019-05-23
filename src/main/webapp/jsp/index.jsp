<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<title>简易实现点对点聊天</title>
<body>
<div>
    <input id="connectId" type="text">
    <button type="button" id="connect">连接到聊天室</button>
</div>

<div id="msgInfo">
</div>

<div style="margin-top: 50px">
    <div>
        <div><span>接收者id:</span><input type="text" id="toId">
            <span>要发送的消息:</span><input type="text" id="msg"></input>
            <button id="send" type="button">发送消息</button>
        </div>
    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script>
    var websocket = null;
    var conect = null;

    //点击连接
    $("#connect").click(function () {
        //判断当前连接是否为空
        if (websocket != null) {
            alert("您已经连接了，请不要重复连接！");
            return;
        }
        //自定义的唯一标识，如果两个人输入的连接标识相同则会覆盖原来这个标识对应的连接，这里只是为了简单演示
        var connectId = $("#connectId").val();
        if (connectId == null || connectId == '') {
            alert("请先输入您的连接标识!");
            return
        }
        //开始连接
        // 首先判断是否 支持 WebSocket
        if ('WebSocket' in window) {
            //路径ws + ip + port + 自定义路径
            conect = connectId;
            websocket = new WebSocket("ws://localhost:8080/chat/" + connectId);
        } else {
            alert("浏览器不支持连接！")
            return;
        }

        // 打开时
        websocket.onopen = function (evnt) {
            console.log("  websocket.onopen  ");
            alert("连接成功!");
        };

        // 处理消息时
        websocket.onmessage = function (evnt) {
            //将消息转成json格式
            var msg = JSON.parse(evnt.data);
            $("#msgInfo").append("<p>收到--" + msg.senderId + "--给您发消息:<font color='red'>" + msg.text + "</font></p>");
            alert("收到消息!"+ msg.senderId + "--给您发消息:<font color='red'>" + msg.text );
            console.log("websocket.onmessage ");
        };

        websocket.onerror = function (evnt) {
            websocket == null;
            console.log("  websocket.onerror  ");
        };

        websocket.onclose = function (evnt) {
            console.log("  websocket.onclose  ");
            websocket.close();
            alert("连接关闭!");
        };
    });

    //发送消息
    $("#send").click(function () {
        //先判断是否连接
        if (websocket == null) {
            alert("您还没有连接!!!");
            return;
        }

        //接收者id
        var toId = $("#toId").val();
        //发送的消息内容
        var msg = $("#msg").val();
        if (toId == null || toId == '') {
            alert("请先输入接收者");
            return;
        }
        if (msg == null || msg == '') {
            alert("消息不能为空!!!");
            return;
        }

        //发送消息
        //构造消息的json格式
        var msgJson = {
            senderId: conect,
            text: msg,
            to: toId
        };
        websocket.send(JSON.stringify(msgJson));
    });
</script>
</html>
