<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="views/home.css">
<title>首页</title>
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function(){
		var data = null
		var prodNowPage = 0
		var prodPageCount = 0
		// 事件：获取评论
		var commEvent = function(){
			var url = this.href;
			var a = $(this);
			$.getJSON(url, function(data){
				// 清空列表
				a.next("ul").empty();
				// JSON变为列表显示
				for(var i = 0; i < 10; i++){
					a.next("ul").append('<li><p>'+(i+1)+'、'+data[i].content+'</p></li>');
				}
				
			});
			return false;
		}
		
		function pageImpl(data, nowPage) {
			// JSON变为列表显示
			for(var i = nowPage * 20; i < data.length && i < (nowPage * 20 + 20); i++){
				var item = $(
						'<li class="product-item">'+
							'<a href="'+data[i].page+'">'+
								'<img class="product-img img-rounded" src="'+data[i].img+'">'+
								'<div style="margin-left: 200px;">'+
									'<h3>'+data[i].name+'</h3>'+
									'<p>'+data[i].price+'</p>'+
								'</div>'+
								'<a href="getcomments?clueid='+data[i].commentClueid+'">推荐评论 ('+
									(data[i].commentCount-data[i].waterCount)+'/'+
									(data[i].commentCount-0)+')</a>'+
								'<ul class="comment-list"></ul>'+
							'</a>'+
						'</li>'
				);
				if (data[i].commentCount != null) {
					item.css("background-color", "rgb(145, 218, 173)");
				}
				$("#product-list").append(item);
			}
			
			// 添加事件
			$("#product-list a[href^='getcomments']").click(commEvent);
		}
		
		// 事件：用json组装product-item
		var itemEven = function(json){
			data = json
			prodNowPage = 0;
			prodPageCount = Math.ceil(data.length / 20)
			pageImpl(data, prodNowPage);
			
			$("#page-tag").text((prodNowPage+1) +"/"+ prodPageCount);
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
		
		// 上一页
		$("#pre").click(function(){
			// 翻页
			if (prodNowPage > 0) {
				// 清空列表
				$("#product-list").empty();
				prodNowPage --
				pageImpl(data, prodNowPage);
				
				$("#page-tag").text((prodNowPage+1) +"/"+ prodPageCount);
			}else {
				// 如果不翻页，就不定位
				return false;
			}
		});
		
		// 下一页
		$("#next").click(function(){
			// 翻页
			if (prodNowPage < prodPageCount - 1) {
				// 清空列表
				$("#product-list").empty();
				prodNowPage ++
				pageImpl(data, prodNowPage);
				
				$("#page-tag").text((prodNowPage+1) +"/"+ prodPageCount);
			}else {
				return false;
			}
		});
	})
</script>
</head>
<body>
	
	<ul class="content">
	
	  	<c:forEach var="brand" items="${requestScope.brands }" >
	    	
	    	<li style="display:inline">
		    	<a href="getproducts?brand=${brand.productClueid }">
		    		<img src="${brand.img}" alt="${brand.name }"/> 
		    	</a>
	    	</li>
	    	
    	</c:forEach>
	</ul>
	
	<div id="search-box" class="search-box">
		<form action="search">
			<input type="text" placeholder="输入商品型号" name="wd"/>
			<input type="submit" value="搜索"/>
		</form>
	</div>
	<p id="page-tag" class="product-list"></p>
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
	
	<ul class="pager">
		<li class="previous"><a id="pre" style="position: fixed; top: 435px; left: 226px;" href="#search-box">&larr; 上一页</a></li>
		<li class="next"><a id="next" style="position: fixed; top: 435px; right: 190px;" href="#search-box">下一页 &rarr;</a></li>
	</ul>

</body>
</html>