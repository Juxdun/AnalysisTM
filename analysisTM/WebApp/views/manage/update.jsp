<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	ul {
       width: 800px;
       margin: auto;
       border:2px solid;
       border-radius:25px;
	}
</style>
<title>后台管理</title>
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		$("#getWaCount").click(function(){
			var url = this.href;
			$.getJSON(url, function(data){
				$("#content").empty()
					.append('<li><p>水军数：'+data[0]+'</p></li>')
					.append('<li><p>总数：'+data[1]+'</p></li>')
			})
			return false;
		});
		
		$("#getWaComments").click(function(){
			var url = this.href;
			$.getJSON(url, function(data){
				$("#content").empty();
				// JSON变为列表显示
				for(var i = 0; i < data.length; i++){
					$("#content").append('<li><p>'+data[i].person+'：'+data[i].content+'</p></li>');
				}
			})
			return false;
		});
	});
</script>
</head>
<body>
	<ul>
	    <li><a href="analysis">重新整理数据（tip：确定已经爬虫抓取了数据）</a></li>
	    <li><a id="getWaCount" href="getWaCount">查看水军数量</a></li>
	    <li><a id="getWaComments" href="getWaComments">查看水军评论</a></li>
	</ul>
	
	<ul id="content"></ul>
</body>
</html>