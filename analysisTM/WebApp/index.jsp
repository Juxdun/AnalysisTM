<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>正在跳转到主页。。</title>
</head>
<body>
<% response.sendRedirect("home");%>
正在跳转到主页。。
  <a href="home">home</a>
  <a href="update">update</a>
</body>
</html>