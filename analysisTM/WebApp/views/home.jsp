<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="views/home.css">
<title>智能电子商务数据分析网</title>
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/highcharts.js"></script>
<script type="text/javascript" src="views/home.js"></script>
</head>
<body>

<nav class="navbar navbar-default">
  <div class="container">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="home">LL</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <p class="navbar-text">智能电子商务数据分析网</p>
      <form class="navbar-form navbar-right" role="search" action="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="请输入商品型号" name="wd">
        </div>
        <button type="submit" class="btn btn-default">搜索</button>
      </form>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>

<div class="container">
  <div class="row">
    <div class="col-md-3">

<ul class="brands list-inline">
	
	  	<c:forEach var="brand" items="${requestScope.brands }" >
	    	
	    	<li class="brandItem">
		    	<a href="getproducts?brand=${brand.id }">
		    		<img src="${brand.img}" alt="${brand.name }"/> 
		    	</a>
	    	</li>
	    	
    	</c:forEach>
	</ul>

    </div>
    <div class="col-md-8 col-md-offset-1">
<!-- 
	<div id="search-box" class="search-box">
		<form action="search">
			<input type="text" placeholder="输入商品型号" name="wd"/>
			<input type="submit" value="搜索"/>
		</form>
	</div>
 -->
	
	<p id="page-tag" class="product-list"></p>
	
	<nav>
		<ul class="pager">
			<li><a id="pre" href="#search-box">&larr; 上一页</a></li>
			<li><a id="next" href="#search-box">下一页 &rarr;</a></li>
		</ul>
	</nav>
	
	<div id="product-list" class=" list-group">
	</div>

    </div>
  </div>
	
	
<!-- Large modal -->
<div id="detailmodal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h3 class="modal-title" id="myModalLabel">未指定商品名</h3>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-sm-8">
            <div id="myChart"></div>
          </div>
          <div class="col-sm-4">
            <img id="modalImg" src="" class="img-rounded img-responsive">
            <h5 id="modalResume"></h5>
            <p id="modalPrice" class="proSell-price"></p>
            <a id="modalPage" class="btn btn-primary" href="#" target="_blank">去天猫</a>
          </div>
        </div>
        
<div role="tabpanel">
  <!-- Nav tabs -->
  <ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#recommend" aria-controls="recommend" role="tab" data-toggle="tab">推荐评论 <span id="recommend-count" class="badge">42</span></a></li>
    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">屏幕  </a></li>
    <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">像素 </a></li>
    <li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">内存 </a></li>
  </ul>

  <!-- Tab panes -->
  <div class="tab-content">
    <div role="tabpanel" class="tab-pane active" id="recommend">
      <ul class="pager">
        <li><a id="preComm" href="preComm">上一页</a></li>
        <li><a id="nextComm" href="nextComm">下一页</a></li>
      </ul>
      <p id="cPage-label"></p>
<ul id="cList" class="list-group">
  <li class="list-group-item">
    <span class="badge">14</span>
    Cras justo odio
  </li>
</ul>
    </div>
    <div role="tabpanel" class="tab-pane" id="profile">...</div>
    <div role="tabpanel" class="tab-pane" id="messages">...</div>
    <div role="tabpanel" class="tab-pane" id="settings">...</div>
  </div>

</div>

      </div>
    </div>
  </div>
</div>
	
	
	<a style="position: fixed; bottom: 100px; right: 100px;" href="#">顶部</a>
</div>
</body>
</html>