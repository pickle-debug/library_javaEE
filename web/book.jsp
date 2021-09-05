
<%@ page import="user.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>图书系统</title>
</head>
<body>
<%
    User user=(User)request.getAttribute("user");//得到servlet传来的属性“user”
    String username=user.getUsername();
%>
<h3>图书系统</h3>&nbsp;&nbsp;&nbsp;&nbsp;用户名：<%=username %>&nbsp;&nbsp;<a href="index.html"><button>退出</button></a><br><br>
<form action="bookServlet">
    <button>查询图书信息</button>
</form>
</body>
</html>

