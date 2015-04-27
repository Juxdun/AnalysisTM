/**
 * home.jsp的js操作
 * 
 * 简写：
 * p（product）
 * c（comment）
 */

// 商品json数据
// 商品页号
// 商品总页数
var data = null,
	pNowPage = 0,
	pPageCount = 0;

// 评论json数据
// 评论页号
// 评论总页数
var cData = null,
	cNowPage = 0,
	cPageCount = 0;

//-----------------  home.jsp文档准备好后  ---------------------------
$(function() {
	//1. 文档准备好，加载全部的product
	$.getJSON("getAllProducts", loadPItem);
	
	//2. 每个品牌按钮添加事件
	$(".content a").click(brandEvent);
	
	//3. “搜索”按钮添加事件
	$("form [type='submit']").click(searchEvent);
	
});

/*---------------------------------定义方法--------------------------------------*/

// 品牌按钮事件
function brandEvent() {
	// 清空列表
	$("#product-list").empty();

	var url = this.href;
	$.getJSON(url, loadPItem);
	return false;
}

// “搜索”事件
function searchEvent() {
	var wd = $("[name='wd']").val();
	wd = $.trim(wd);
	if (wd == "") {
		return false;
	}
	// 清空列表
	$("#product-list").empty();

	var url = $("form").attr("action");
	var args = {
		"wd" : wd
	};
	$.getJSON(url, args, loadPItem);
	return false;
}

// 事件：获取评论
function getCommentEvent() {
	var url = this.href;
	var a = $(this);
	var commentList = a.parents(".commentTab").next(".comment-list");
	commentList.slideToggle();
	
	var cList = commentList.children("#cList");
	$.getJSON(url, function(data) {
		loadCItem(cList, data);
	});
	
	commentList.find("[href='preComm']").click(function(){
		cPreEvent(cList);
		return false;
	});
	commentList.find("[href='nextComm']").click(function(){
		cNextEvent(cList);
		return false;
	});
	return false;
}

function loadCItem(cList, data){
	cData = data;
	cNowPage = 0;
	cPageCount = Math.ceil(data.length / 10);
	
	// 清空列表
	cList.empty();
	
	cPager(data, cNowPage, cList);
};

function cPager(data, nowPage, cList){
	
	// JSON变为列表显示
	for (var i = nowPage * 10; i < data.length && i < (nowPage * 10 + 10); i++) {
		cList.append(
				'<li><p>' + (i + 1) + '、' + data[i].content + '</p></li>');
	}
}

function cPreEvent(cList){
	if (cNowPage > 0) {
		// 清空列表
		cList.empty();
		cNowPage --;
		cPager(cData, cNowPage, cList);
	}
}

function cNextEvent(cList){
	if (cNowPage < cPageCount - 1) {
		// 清空列表
		cList.empty();
		cNowPage ++;
		cPager(cData, cNowPage, cList);
	}
}




// 商品上一页事件
function pPreEvent() {
	// 翻页
	if (pNowPage > 0) {
		// 清空列表
		$("#product-list").empty();
		pNowPage--;
		pPager(data, pNowPage);

		$("#page-tag").text((pNowPage + 1) + "/" + pPageCount);
	} else {
		// 如果不翻页，就不定位
		return false;
	}
}

// 商品下一页事件
function pNextEvent() {
	// 翻页
	if (pNowPage < pPageCount - 1) {
		// 清空列表
		$("#product-list").empty();
		pNowPage++;
		pPager(data, pNowPage);

		$("#page-tag").text((pNowPage + 1) + "/" + pPageCount);
	} else {
		return false;
	}
}

// 商品翻页实现
function pPager(data, nowPage) {
	// JSON变为列表显示
	for (var i = nowPage * 20; i < data.length && i < (nowPage * 20 + 20); i++) {
		
		var item = $('<li class="product">'+
			'<div class="productItem clearfix">'+
				'<a class="productImg" href="' + data[i].page + '">'+
					'<img src="' + data[i].img + '" alt="">'+
				'</a>'+
				'<div class="productInfo">'+
					'<h3 class="productTitle">'+
						'<a href="' + data[i].page + '">' + data[i].name + '</a>'+
					'</h3>'+
					'<div class="productSell">'+
						'<p class="proSell-price">' + data[i].price + '</p>'+
					'</div>'+
				'</div>'+
			'</div>'+

			'<div class="productComment">'+
				'<ul class="commentTab clearfix">'+
					'<li><a href="getcomments?clueid=' + data[i].commentClueid + '">推荐评论</a></li>'+
	              	'<li><a href="getKeywordComments?kw=屏幕&clueid=' + data[i].commentClueid + '">屏幕</a></li>'+
	              	'<li><a href="getKeywordComments?kw=内存&clueid=' + data[i].commentClueid + '">内存</a></li>'+
	              	'<li><a href="getKeywordComments?kw=速度&clueid=' + data[i].commentClueid + '">速度</a></li>'+
	              	'<li><a href="getKeywordComments?kw=CPU&clueid=' + data[i].commentClueid + '">CPU</a></li>'+
	              	'<li><a href="getKeywordComments?kw=物流&clueid=' + data[i].commentClueid + '">物流</a></li>'+
	            '</ul>'+
	            '<div class="comment-list">'+
	            	'<ul class="pager">'+
	            		'<li><a href="preComm">上一页</a></li>'+
	            		'<li><a href="nextComm">下一页</a></li>'+
	            	'</ul>'+
	            	'<ul id="cList"></ul>'+
	            '</div>'+
	        '</div>'+
		'</li>');
			
			
			
//			$('<li class="product-item">' + '<a href="' + data[i].page
//				+ '">' + '<img class="product-img img-rounded" src="'
//				+ data[i].img + '">' + '<div style="margin-left: 200px;">'
//				+ '<h3>' + data[i].name + '</h3>' + '<p>' + data[i].price
//				+ '</p>' + '</div>' + '<a href="getcomments?clueid='
//				+ data[i].commentClueid + '">推荐评论 ('
//				+ (data[i].commentCount - data[i].waterCount) + '/'
//				+ (data[i].commentCount - 0) + ')</a>'
//				+ '<ul class="comment-list"></ul>' + '</a>' + '</li>');
		if (data[i].commentCount != null) {
			item.css("background-color", "rgb(145, 218, 173)");
		}
		$("#product-list").append(item);
	}

	// 添加事件
	$(".commentTab a").click(getCommentEvent);
}

// 用JSON组装product-item
function loadPItem(json) {
	data = json;
	pNowPage = 0;
	pPageCount = Math.ceil(data.length / 20);
	pPager(data, pNowPage);

	$("#page-tag").text((pNowPage + 1) + "/" + pPageCount);
	
	//4. 商品上一页
	$("#pre").click(pPreEvent);
	
	//5. 商品下一页
	$("#next").click(pNextEvent);
}
