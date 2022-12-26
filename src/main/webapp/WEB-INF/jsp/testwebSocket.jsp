<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getServerName() + ":" + request.getServerPort() + path + "/";
	String baseUrlPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="websocket测试系统">
<meta name="description" content="测试系统">
<title>测试</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resourcesjs/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resourcesjs/js/sockjs.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resourcesjs/css/style.css" type="text/css" media="all" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resourcesjs/css/chat.css" type="text/css" media="all" />
<script type="text/javascript">
	var path = 'localhost:9527/websocket/2';
	
	// 创建一个Socket实例
	//参数为URL，ws表示WebSocket协议。onopen、onclose和onmessage方法把事件连接到Socket实例上。每个方法都提供了一个事件，以表示Socket的状态。
	var websocket;
	//不同浏览器的WebSocket对象类型不同
	//alert("ws://" + path + "/ws?uid="+uid);
	if ('WebSocket' in window) {
		websocket = new WebSocket("ws://" + path + "ws");
		console.log("=============WebSocket"+path);
		//火狐
	} else if ('MozWebSocket' in window) {
		websocket = new MozWebSocket("ws://" + path + "ws");
		console.log("=============MozWebSocket");
	} else {
		websocket = new SockJS("http://" + path + "ws/sockjs");
		console.log("=============SockJS");
	}
	
	console.log("ws://" + path + "ws");
	
	//打开Socket,
	websocket.onopen = function(event) { 
		console.log("WebSocket:已连接");
	}
	
	// 监听消息
	//onmessage事件提供了一个data属性，它可以包含消息的Body部分。消息的Body部分必须是一个字符串，可以进行序列化/反序列化操作，以便传递更多的数据。
	websocket.onmessage = function(event) { 
		console.log('Client received a message',event);

        $("#contentUl").append("<li><b>"+event.data+"</b></li>");
		console.log("WebSocket:收到一条消息",event.data);

	}; 
	
	// 监听WebSocket的关闭
	websocket.onclose = function(event) { 
		$("#contentUl").append("<li><b>"+new Date().Format("yyyy-MM-dd hh:mm:ss")+"</b><em>系统消息：</em><span>连接已断开！</span></li>");
		scrollToBottom();
		console.log("WebSocket:已关闭：Client notified socket has closed",event);
	}; 
	
	//监听异常
	websocket.onerror = function(event) {
		$("#contentUl").append("<li><b>"+new Date().Format("yyyy-MM-dd hh:mm:ss")+"</b><em>系统消息：</em><span>连接异常，建议重新登录</span></li>");
		scrollToBottom();
		console.log("WebSocket:发生错误 ",event);
	};
	
	//onload初始化
	$(function(){
		//发送消息
		$("#sendBtn").on("click",function(){
			sendMsg();
		});
		
		//给退出聊天绑定事件
		$("#exitBtn").on("click",function(){
			closeWebsocket();
			location.href="${pageContext.request.contextPath}/index.jsp";
		});
		
		//给输入框绑定事件
		$("#msg").on("keydown",function(event){
			keySend(event);
		});
		
		//初始化时如果有消息，则滚动条到最下面：
		scrollToBottom();
		
	});

	//使用ctrl+回车快捷键发送消息
	function keySend(e) {
		var theEvent = window.event || e; 
		var code = theEvent.keyCode || theEvent.which; 
		if (theEvent.ctrlKey && code == 13) {
			var msg=$("#msg");
			if (msg.innerHTML == "") {
				msg.focus();
				return false;
			}
			sendMsg();
		}
	}
	
	//发送消息
	function sendMsg(){
		//对象为空了
		if(websocket==undefined||websocket==null){
			//alert('WebSocket connection not established, please connect.');
			alert('您的连接已经丢失，请退出聊天重新进入');
			return;
		}
		//获取用户要发送的消息内容
		var msg=$("#msg").val();
		if(msg==""){
			return;
		}else{
			var data={};
			data["from"]=from;
			data["fromName"]=fromName;
			data["to"]=to;
			data["text"]=msg;
			//发送消息
			websocket.send(JSON.stringify(data));
			//发送完消息，清空输入框
			$("#msg").val("");
		}
	}

	//关闭Websocket连接
	function closeWebsocket(){
		if (websocket != null) {
			websocket.close();
			websocket = null;
		}
		
	}
	
	//div滚动条(scrollbar)保持在最底部
	function scrollToBottom(){
		//var div = document.getElementById('chatCon');
		var div = document.getElementById('up');
		div.scrollTop = div.scrollHeight;
	}	
	//格式化日期
	Date.prototype.Format = function (fmt) { //author: meizz 
	    var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "h+": this.getHours(), //小时 
	        "m+": this.getMinutes(), //分 
	        "s+": this.getSeconds(), //秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	        "S": this.getMilliseconds() //毫秒 
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    return fmt;
	}
	
	

		
				
</script>
</head>

<body>
<!--顶部开始-->
<div class="top">
	<div class="inTop">
    	<p>Mr.Qiao：<a href="http://www.baidu.com/" target="_blank">测试版本</a>|<a href="http://www.baidu.com/" target="_blank">聊天室</a></p>
        <span>聊天就是开心<strong>服务热线：110</strong></span>
    </div>
</div>
<!--顶部end-->
<!--头部开始-->
<div class="header">
	<div class="inHeader">
    	<h1>聊天室系统</h1>
        <div class="style">
        	<p>当前登录用户：${sessionScope.loginUser!=null?sessionScope.loginUser.nickname:"请登录" }&nbsp;&nbsp;&nbsp;<button id="exitBtn">退出或重新登录</button></p>
        </div>
    </div>
</div>
<!--头部end-->
<!--聊天区域开始-->
<div class="chatArea" id="chatArea">
	<div class="inChatArea">

    	<div class="chatCon">
        	<div class="up" id="up">
            	<ul id="contentUl">
                	<!-- <li><b>14:08</b><em>江山如此多娇</em><span>今天天气不大家出来嗨！！！！！</span></li>
                	-->
                </ul>
            </div>

        </div>
    </div>
</div>
<!--聊天区域结束-->
<div class="footer">
    <p>版权所有 2020 - 2021 坑你点股份有限公司   地址：北京市昌平区   邮编：未知<br />
    电话：110   邮箱: XXX@163.com</p>
</div>
</body>
</html>
