<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>首页</title>
<link rel="stylesheet" type="text/css" href="views/home.css">
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		$(".content a").click(function(){
			var url = this.href;
			$.getJSON(url, function(data){
				// 清空列表
				$(".list").empty();
				
				// 把Json数据生成列表项
				for(var i = 0; i < data.length; i++){
					$(".list").append('<div class="product-item"><div style="margin: 3px;">'+data[i].name+' &nbsp;&nbsp; <a style="float: right;" href="'+data[i].page+'">详情</a></div><div style="margin: 3px;">'+data[i].price+'</div><a href="#">有效评论</a></div>')
				}
			});
			return false;
		});
	})
</script>
</head>
<body>
	<div class="content">
	  <c:forEach var="brand" items="${requestScope.brands }" >
	    <a href="getproducts?brand=${brand.productClueid }" style="text-decoration: none;"><img src="${brand.img}" /> </a>

    </c:forEach>
	</div>

	<div id="list" class="list">
	  <c:forEach var="product" items="${requestScope.products}" >
      <div class="product-item">
        <div style="margin: 3px;">${product.name} &nbsp;&nbsp; <a style="float: right;" href="${product.page}">详情</a></div>
        <div style="margin: 3px;">${product.price}</div>
        <a href="#">有效评论</a>
      </div>

    </c:forEach>
	</div>

</body>
</html>