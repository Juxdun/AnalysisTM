<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>首页</title>
	<style>
	.content{
		width: 1000px;
    margin: auto;
	}
	img{
	  width: 102px;
	  height: 50px;
	  border: 1px solid gray;
	}
	.list{
	  width: 1000px;
    margin: auto;
	}
	.product-item{
	  padding: 10px;
	  border: 1px solid darkgray;
	  margin: 3px 0px;
	}
	</style>
</head>
<body>
	<div class="content">
	  <c:forEach var="brand" items="${requestScope.brands }" >
	    <a href=""  style="text-decoration: none;"><img src="${brand.img}" /> </a>

    </c:forEach>

	</div>

	<div id="list" class="list">
	  <c:forEach var="product" items="${requestScope.products}" >
      <div class="product-item">
        <div style="margin: 3px;">${product.name} &nbsp;&nbsp; <a style="float: right;" href="${product.page}">详情</a></div>
        <div style="margin: 3px;">${product.price}</div>
      </div>

    </c:forEach>

	</div>




    <br /><br />
	${requestScope.comments }
    <br /><br />


home
</body>
</html>