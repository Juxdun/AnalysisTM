<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="css/bootstrap.min.css">
<title>智能电子商务数据分析网--后台数据处理</title>
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="views/manage/update.js"></script>
<script type="text/javascript">
	$(function(){
		$("#getWaCount").click(function(){
			var url = this.href;
			$.getJSON(url, function(data){
				$("#content").empty()
					.append('<li class="list-group-item"><p>水军数：'+data[0]+'</p></li>')
					.append('<li class="list-group-item"><p>总数：'+data[1]+'</p></li>')
			})
			return false;
		});
		
		$("#getWaComments").click(function(){
			var url = this.href;
			$.getJSON(url, function(data){
				$("#content").empty();
				// JSON变为列表显示
				for(var i = 0; i < data.length; i++){
					$("#content").append('<li class="list-group-item"><p>'+data[i].person+'：'+data[i].content+'</p></li>');
				}
			})
			return false;
		});
	});
</script>
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="update">LL</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <p class="navbar-text">智能电子商务数据分析网--后台数据处理</p>
      <ul class="nav navbar-nav">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">导入 <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="insertBrands">导入品牌表</a></li>
            <li><a href="insertProducts">导入商品表</a></li>
            <li><a href="insertComments">导入评论表</a></li>
            <li class="divider"></li>
            <li><a href="linkB2P">链接品牌-商品</a></li>
            <li><a href="linkP2C">链接商品-评论</a></li>
            <li class="divider"></li>
            <li><a href="insertAndLink">一键搞定以上步骤</a></li>
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">分析 <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="signWater">标记水军评论</a></li>
            <li><a href="analysePositive">分析好评度</a></li>
            <li><a href="analyseNegative">分析差评度</a></li>
            <li class="divider"></li>
            <li><a href="countComment">统计评论总数</a></li>
            <li><a href="countWater">统计水军数</a></li>
            <li><a href="countGood">统计好评数</a></li>
            <li><a href="countBad">统计差评数</a></li>
            <li><a href="countStar">计算Star</a></li>
            <li><a href="oneshotCount">一键统计</a></li>
            <li class="divider"></li>
            <li><a href="analyseAndCount">一键搞定以上步骤</a></li>
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">一键 <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="insertAndLink">一键搞定导入</a></li>
            <li><a href="analyseAndCount">一键搞定分析</a></li>
            <li class="divider"></li>
            <li><a href="insertAndAnalyse">一键搞定导入和分析</a></li>
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">后期加工 <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
<!--             <li><a id="getWaCount" href="#">手动标记水军</a></li>
            <li><a id="getWaCount" href="getWaCount">查看水军数量</a></li>
            <li><a id="getWaComments" href="getWaComments">查看水军评论</a></li>
            <li class="divider"></li> -->
            <li><a id="gotoPageCI" href="#change-info">修改商品信息</a></li>
          </ul>
        </li>
      </ul>
      <form class="navbar-form navbar-right" role="search" action="search">
        <div class="form-group">
          <input type="text" class="keyword form-control" placeholder="请输入商品型号">
        </div>
        <button type="submit" class="search-pro btn btn-default">搜索</button>
      </form>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>

<div class="container">
<div role="tabpanel">

  <!-- Nav tabs -->
  <ul class="nav nav-tabs" role="tablist" id="myTab">
    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">主页</a></li>
<!--     <li role="presentation"><a href="#sign-wa" aria-controls="sign-wa" role="tab" data-toggle="tab">手动标记水军</a></li> -->
    <li role="presentation"><a href="#change-info" aria-controls="change-info" role="tab" data-toggle="tab">修改商品信息</a></li>
<!--     <li role="presentation"><a href="#info" aria-controls="info" role="tab" data-toggle="tab">统计信息</a></li> -->
  </ul>

  <!-- Tab panes -->
<div class="tab-content">
  <div role="tabpanel" class="tab-pane fade in active" id="home">
    <h1 class="text-center">智能分析操作步骤</h1>
    <ul>
      <li><h2>步骤1：导入</h2></li>
      <ul>
        <li><h4>1)、导入品牌表 <small>  </small></h4></li>
        <li><h4>2)、导入商品表 <small>  </small></h4></li>
        <li><h4>3)、导入评论表 <small>  </small></h4></li>
        <li><h4>4)、链接品牌-商品 <small>  （ps：先导入品牌和商品表）</small></h4></li>
        <li><h4>5)、链接商品-评论 <small>  （ps：先导入商品和评论表）</small></h4></li>
      </ul>
      <li><h2>步骤2：分析</h2></li>
      <ul>
        <li><h4>1)、标记水军评论 </h4></li>
        <li><h4>2)、分析好评度 </h4></li>
        <li><h4>3)、分析差评度 </h4></li>
        <li><h4>4)、统计评论总数 </h4></li>
        <li><h4>5)、统计水军数 <small>  （ps：先标记水军）</small></h4></li>
        <li><h4>6)、统计好评数 <small>  （ps：先分析好评度）</small></h4></li>
        <li><h4>7)、统计差评数 <small>  （ps：统计差评数）</small></h4></li>
        <li><h4>8)、统计推荐值 Star <small>  （ps：先分析好评差评）</small></h4></li>
      </ul>
    </ul>
  
  </div>
<!--   <div role="tabpanel" class="tab-pane fade" id="sign-wa">
  
	<div class="list-group">
	  <a class="list-group-item" href="analysis">
	    <h4 class="list-group-item-heading">重新整理数据</h4>
	    <p class="list-group-item-text">tip：确定已经爬虫抓取了数据</p>
	  </a>
	  <a class="list-group-item" id="getWaCount" href="getWaCount">
	    <h4 class="list-group-item-heading">查看水军数量</h4>
	    <p class="list-group-item-text">会看到整理后的水军数据</p>
	  </a>
	  <a class="list-group-item" id="getWaComments" href="getWaComments">
	    <h4 class="list-group-item-heading">查看水军评论</h4>
	    <p class="list-group-item-text">看到整理后的评论</p>
	  </a>
	</div>
	
  </div> -->
  <div role="tabpanel" class="tab-pane fade" id="change-info">
    <form class="navbar-form" role="search" action="search">
      <div class="form-group">
        <input type="text" class="keyword form-control" placeholder="请输入商品型号">
      </div>
      <button type="submit" class="search-pro btn btn-default">搜索</button>
    </form>
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
<!--   <div role="tabpanel" class="tab-pane fade" id="info">info</div> -->
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
          <div class="col-sm-4">
            <img id="modalImg" src="" class="img-rounded img-responsive">
            <h5 id="modalResume"></h5>
            <p id="modalPrice" class="proSell-price"></p>
            <a id="modalPage" class="btn btn-primary" href="#" target="_blank">去天猫</a>
          </div>
          <div class="col-sm-8">
<!-- 模态框中的表单 -->
<form class="form-horizontal" action="changeProInfo" method="post">
  <input type="hidden" id="id" name="id">
  <div class="form-group">
    <label for="inputName" class="col-sm-2 control-label">商品名</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="name" name="name" placeholder="商品名">
    </div>
  </div>
  <div class="form-group">
    <label for="inputResume" class="col-sm-2 control-label">简介</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="resume" name="resume" placeholder="简介">
    </div>
  </div>
  <div class="form-group">
    <label for="inputPrice" class="col-sm-2 control-label">价格</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="price" name="price" placeholder="价格">
    </div>
  </div>
  <div class="form-group">
    <label for="inputImg" class="col-sm-2 control-label">图片</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="img" name="img" placeholder="图片">
    </div>
  </div>
  <div class="form-group">
    <label for="inputStar" class="col-sm-2 control-label">Star</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="star" name="star" placeholder="Star">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="change-pro-info btn btn-default">修改商品</button>
    </div>
  </div>
</form>
          </div>
        </div>
        
      </div>
    </div>
  </div>
</div>


<!-- 确认模态框 -->
<div class="modal fade" id="sureModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="sureTitle">确定标题</h4>
      </div>
      <div class="modal-body">
        <p id="sureContent">...</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button id="sureBtn" type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
      </div>
    </div>
  </div>
</div>

</div>
</body>
</html>