<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>

    <%-- websocket 测试 --%>
    <title>websocket测试</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>

<div class="col-lg">
    <div class="page-header" id="info">webSocket及时聊天Demo程序</div>
    <div class="input-group">
        <input type="text" class="form-control" placeholder="" id="user_info_id">
        <span class="input-group-btn">
				<button class="btn btn-default" type="button" id="connect_server">连接服务器</button>
			</span>
    </div>
</div>

<div class="col-lg">
    <div class="input-group">
        <input type="text" class="form-control" placeholder="接收方id" id="receiverId">
        <input type="text" class="form-control" placeholder="发送信息..." id="message">
        <span class="input-group-btn">
				<button class="btn btn-default" type="button" id="send">发送</button>
			</span>
    </div>
</div>

<script>
    var user_info_id;

    $(function () {
        console.log(uuid(32, 10));

        testUploadArray();

        var websocket;

        function connectServer() {
            user_info_id = $('#user_info_id').val();
            if ("WebSocket" in window) {
                websocket = new WebSocket("ws://192.168.1.102:8080/chat?userType=0&userId=" + user_info_id);
            } else if ("MozWebSocket" in window) {
                alert("MozWebSocket");
                websocket = new MozWebSocket("ws://chat");
            } else {
                alert("SockJS");
                websocket = new SockJS("http://localhost:8080/sockjs/chat");
            }
        }

        $("#connect_server").bind("click", function () {
            connectServer();

            websocket.onopen = function (event) {
                $("#info").html("连接成功");
            };

            websocket.onmessage = function (event) {
                console.log(event.data);

                var data = eval("(" + event.data + ")");

                $("#info").append(data.msg + "<br>");
            };

            websocket.onerror = function (event) {

            };

            websocket.onclose = function (event) {
                $("#info").append("连接结束");
            };
        });

        $("#send").bind("click", function () {
            send();
        });

        function send() {
            var message = '{"senderId":' + user_info_id + ',"sender":"发送者:12","receiverId":' + $('#receiverId').val() + ',"receiver":"接收者:15","msg":"' + $('#message').val() + '","sendTime":1522480650815,"type":0}';
            websocket.send(message);
        }
    })


    function uuid(len, radix) {
        var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
        var uuid = [], i;
        radix = radix || chars.length;

        if (len) {
            // Compact form
            for (i = 0; i < len; i++) uuid[i] = chars[0 | Math.random() * radix];
        } else {
            // rfc4122, version 4 form
            var r;

            // rfc4122 requires these characters
            uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
            uuid[14] = '4';

            // Fill in random data.  At i==19 set the high bits of clock sequence as
            // per rfc4122, sec. 4.1.5
            for (i = 0; i < 36; i++) {
                if (!uuid[i]) {
                    r = 0 | Math.random() * 16;
                    uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
                }
            }
        }

        return uuid.join('');
    }

    function testUploadArray() {
        var list = new Array();
        list.push(1);
        list.push(2);
        list.push(3);

        $.ajax({
            url: 'http://localhost:8080/test/upload/array',
            type: 'POST',
            data: {
                'list': list
            },
            success: function (data) {
                console.log(data);
            }
        })
    }
</script>
</body>
</html>
