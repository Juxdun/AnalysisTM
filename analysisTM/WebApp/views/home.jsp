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
<script type="text/javascript" src="views/home.js"></script>
</head>
<body>
	
	<ul class="content clearfix">
	
	  	<c:forEach var="brand" items="${requestScope.brands }" >
	    	
	    	<li class="brandItem">
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
	  	<%-- 
	  	<c:forEach var="product" items="${requestScope.products}" >
	  	
	      <li class="product-item">
		      	<div class="img">
		      		<a href="${product.page}">
		      			<img class="product-img" src="${product.img }">
		      		</a>
		      	</div>
		      	<div class="product-info" style="margin-left: 100px;">
		        	<div><a href="${product.page}">${product.name}</a></div>
		        	<div>${product.price}</div>
		        </div>
		        <div class="product-comment">
		        	<ul class="comment-tab">
		        		<li>
				        	<a href="getcomments?clueid=${product.commentClueid }">推荐评论</a>
				        </li>
		        	</ul>
			        <ul id="comment-list"></ul>
		        </div>
	      </li>

    	</c:forEach> --%>
    	
	</ul>
	
	<a style="position: fixed; bottom: 100px; right: 100px;" href="#">顶部</a>
	
	<ul class="pager">
		<li class="previous"><a id="pre" style="position: fixed; top: 435px; left: 226px;" href="#search-box">&larr; 上一页</a></li>
		<li class="next"><a id="next" style="position: fixed; top: 435px; right: 190px;" href="#search-box">下一页 &rarr;</a></li>
	</ul>

</body>
</html>