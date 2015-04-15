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
		// 获取评论的点击事件
		var commEvent = function(){
			var url = this.href;
			var a = $(this);
			$.getJSON(url, function(data){
				// 清空列表
				a.next("div").empty();
				// JSON变为列表显示
				for(var i = 0; i < 10; i++){
					a.next("div").append('<p>'+(i+1)+'、'+data[i].content+'</p>');
				}
				
			});
			return false;
		}
		
		$(".content a").click(function(){
			var url = this.href;
			$.getJSON(url, function(data){
				// 清空列表
				$(".list").empty();
				
				// 把Json数据生成列表项
				for(var i = 0; i < data.length; i++){
					$(".list").append('<div class="product-item"><div style="margin: 3px;">'+data[i].name+'   <a style="float: right;" href="'+data[i].page+'">详情</a></div><div style="margin: 3px;">'+data[i].price+'</div><a href="getcomments?clueid='+data[i].commentClueid+'">有效评论'+data[i].waterCount+'/'+data[i].commentCount+'</a><div style="background-color: #e3e4e2;"></div></div>')
				}
				
				// 添加事件
				$("#list a[href^='getcomments']").click(commEvent);
			});
			return false;
		});
		
		// 添加事件
		$("#list a[href^='getcomments']").click(commEvent);
		
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
        <div style="margin: 3px;">${product.name}                   <a style="float: right;" href="${product.page}">详情</a></div>
        <div style="margin: 3px;">${product.price}</div>
        <a href="getcomments?clueid=${product.commentClueid }">有效评论</a>
        <div style="background-color: #e3e4e2;"></div>
      </div>

    </c:forEach>
	</div>

</body>
</html>