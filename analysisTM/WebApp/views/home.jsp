<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="views/home.css">
<title>首页</title>
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		// 事件：获取评论
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
		
		// 事件：用json组装product-item
		var itemEven = function(data){
			// JSON变为列表显示
			for(var i = 0; i < data.length; i++){
				var item = $(
						'<li class="product-item">'+
							'<a href="'+data[i].page+'">'+
								'<img class="product-img" src="'+data[i].img+'">'+
								'<div style="margin-left: 100px;">'+
									'<div>'+data[i].name+'</div>'+
									'<div>'+data[i].price+'</div>'+
								'</div>'+
								'<a href="getcomments?clueid='+data[i].commentClueid+'">推荐评论 ('+
									(data[i].commentCount-data[i].waterCount)+'/'+
									(data[i].commentCount-0)+')</a>'+
								'<div class="comment-list"></div>'+
							'</a>'+
						'</li>'
				);
				if (data[i].commentCount != null) {
					item.css("background-color", "#e3e4e2");
				}
				$("#product-list").append(item);
			}
			
			// 添加事件
			$("#product-list a[href^='getcomments']").click(commEvent);
		};
		
		// 每个品牌添加事件
		$(".content a").click(function(){
			// 清空列表
			$("#product-list").empty();
			
			var url = this.href;
			
			$.getJSON(url, itemEven);
			return false;
		});
		
		// 每个“推荐评论”添加事件
		$("#product-list a[href^='getcomments']").click(commEvent);
		
		// “搜索”按钮添加事件
		$("form [type='submit']").click(function(){
			var wd = $("[name='wd']").val();
			wd = $.trim(wd);
			if(wd == ""){
				return false;
			}
			// 清空列表
			$("#product-list").empty();
			
			var url = $("form").attr("action");
			var args = {"wd":wd}
			$.getJSON(url, args, itemEven);
			return false;
		});
		
		// 文档准备好，加载全部的product
		$.getJSON("getAllProducts", itemEven);
	})
</script>
</head>
<body>
	<ul class="content">
		<form action="search">
			<input type="text" value="苹果" name="wd"/>
			<input type="submit" value="搜索"/>
		</form>
	
	  	<c:forEach var="brand" items="${requestScope.brands }" >
	    	
	    	<li style="display:inline">
		    	<a href="getproducts?brand=${brand.productClueid }">
		    		<img src="${brand.img}" /> 
		    	</a>
	    	</li>
	    	
    	</c:forEach>
	</ul>

	<ul id="product-list" class="product-list">
	  	<!-- 遍历 -->
	  	<!-- 
	  	<c:forEach var="product" items="${requestScope.products}" >
	  	
	      <li class="product-item">
	      	<a href="${product.page}">
		      	<img class="product-img" src="${product.img }">
		      	<div style="margin-left: 100px;">
		        	<div>${product.name}</div>
		        	<div>${product.price}</div>
		        </div>
		        <a href="getcomments?clueid=${product.commentClueid }">推荐评论</a>
		        <div class="comment-list"></div>
	        </a>
	      </li>

    	</c:forEach>
    	 -->
	</ul>
	
	<a style="position: fixed; bottom: 100px; right: 100px;" href="#">顶部</a>

</body>
</html>