<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="websocket聊天系统">
<meta name="description" content="聊天系统">
<title>在线留言系统</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resourcesjs/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resourcesjs/js/lbt.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resourcesjs/css/style.css" type="text/css" media="all" />
<script type="text/javascript">
	$(function(){
		
	});

</script>

</head>
<body>
<!--顶部开始-->
<div class="top">
    <div class="inTop">
        <p>Mr.Qiao：<a href="http://www.baidu.com/" target="_blank">测试版聊天系统</a>|<a href="http://www.baidu.com/" target="_blank">聊天</a></p>
        <span>畅聊，聊出人生！<strong>服务热线：110</strong></span>
    </div>
</div>
<!--顶部end-->
<!--头部开始-->
<div class="header">
	<h1>畅聊·聊天室系统</h1>
</div>
<!--头部end-->
<!--登陆区域开始-->
<div class="loginMain">
	<div class="con">
    	<div class="inCon">
            <ul class="imgList">
                <li><img src="${pageContext.request.contextPath}/resourcesjs/images/p1.jpg" width="680" height="494" /></li>
                <li><img src="${pageContext.request.contextPath}/resourcesjs/images/p2.jpg" width="680" height="494" /></li>
                <li><img src="${pageContext.request.contextPath}/resourcesjs/images/p3.jpg" width="680" height="494" /></li>
                <li><img src="${pageContext.request.contextPath}/resourcesjs/images/p4.jpg" width="680" height="494" /></li>
                <li><img src="${pageContext.request.contextPath}/resourcesjs/images/p5.jpg" width="680" height="494" /></li>
                <li><img src="${pageContext.request.contextPath}/resourcesjs/images/p6.jpg" width="680" height="494" /></li>
                <li><img src="${pageContext.request.contextPath}/resourcesjs/images/p7.jpg" width="680" height="494" /></li>
            </ul>
        </div>
        <ol class="btnList">
        	<li class="current"></li>
        	<li></li>
        	<li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
        </ol>
        <a href="javascript:;" class="left"></a>
        <a href="javascript:;" class="right"></a>    </div>


	<div class="loginArea">
    	<h2>欢迎注册</h2>
        <p>欢迎您来到聊天空间！</p>
        <div><font color="red" size="16">${requestScope.errorTips }</font></div>
        <form action="${pageContext.request.contextPath }/register"  method="post">
        	<input type="text" value="请输入您想显示的昵称" name="nickname" id="myText" />
            <input type="password"  name="password" id="myText" />
            <button>进入聊天室</button>
        </form>
    </div>
</div>
<!--登陆区域结束-->

<div class="footer">
    <p>版权所有 2020 - 2021 坑你点科技股份有限公司   地址：北京市   邮编：未知<br />
        电话：110   邮箱: xxx@163.com</p>
</div>
</body>
<script type="text/javascript">
	var myText=document.getElementById('myText');
	myText.onfocus=function(){
		if(myText.value=='请输入您想显示的昵称'){
			myText.value='';
			myText.style.color='#333';	
		}	
	}
	myText.onblur=function(){
		if(myText.value==''){
			myText.value='请输入您想显示的昵称';
			myText.style.color='#ccc';
		}	
	}

    var mypassword=document.getElementById('mypassword');
    mypassword.onfocus=function(){
        if(mypassword.value=='请输入密码'){
            mypassword.value='';
            mypassword.style.color='#333';
        }
    }
    mypassword.onblur=function(){
        if(mypassword.value==''){
            mypassword.value='请输入密码';
            mypassword.style.color='#ccc';
        }
    }
</script>
</html>