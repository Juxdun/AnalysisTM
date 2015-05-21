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

// 当前Product的位置id
var nowProId = null;

//-----------------  home.jsp文档准备好后  ---------------------------
$(function() {
	//1. 文档准备好，加载全部的product
	$.getJSON("getAllProducts", loadPItem);
	
	//2. 每个品牌按钮添加事件
	$(".brands a").click(brandEvent);
	
	//3. “搜索”按钮添加事件
	$("form [type='submit']").click(searchEvent);
	
	//4. 商品上一页
	$("#pre").click(pPreEvent);
	
	//5. 商品下一页
	$("#next").click(pNextEvent);
	
});

/*---------------------------------定义方法--------------------------------------*/

function detailEvent() {

	$("#detailmodal").modal();
	
	nowProId = $(this).attr("id");
	var pro = data[nowProId];
	
	$("#myModalLabel").text(pro.name);
	$("#modalImg").attr("src", pro.img);
	$("#modalPage").attr("href", pro.page);
	$("#modalResume").text(pro.resume);
	$("#modalPrice").text(pro.price);
	
	// 调用highcharts.js的饼状图
	$('#myChart').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '好评度分布情况：'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.y}</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: '数量',
            data: [
                ['好评', pro.goodCount],
                ['差评', pro.badCount],
                {
                    name: '水军',
                    y: pro.waterCount,
                    sliced: true,
                    selected: true
                }
            ]
        }]
    });
	
	return false;
}

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
	commentList.slideDown();
	
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

		$("#page-tag").text("第" + (pNowPage + 1) + "/" + pPageCount + "页        总商品数：" + data.length);
	} else {
		// 如果不翻页，就不定位
	}
	return false;
}

// 商品下一页事件
function pNextEvent() {
	// 翻页
	if (pNowPage < pPageCount - 1) {
		// 清空列表
		$("#product-list").empty();
		pNowPage++;
		pPager(data, pNowPage);

		$("#page-tag").text("第" + (pNowPage + 1) + "/" + pPageCount + "页        总商品数：" + data.length);
	} else {
	}
	return false;
}

// 商品翻页实现
function pPager(data, nowPage) {
	// JSON变为列表显示
	for (var i = nowPage * 20; i < data.length && i < (nowPage * 20 + 20); i++) {
		// 用a标签记录位置
		var item = $('<a id="'+ i +'" href="'+ i +'" class="list-group-item pro-item">'+
			'<div class="row">'+
			  '<div class="col-xs-4 col-sm-4">'+
			    '<img src="' + data[i].img + '" class="img-rounded img-responsive"  style="max-height: 150px;" alt="">'+
			  '</div>'+
			  '<div class="col-xs-8 col-sm-8">'+
			    '<h3 class="productTitle list-group-item-heading">'+ data[i].name +
			      '<br/><small>'+ data[i].resume +'</small>'+
			    '</h3>'+
			    '<p class="proSell-price list-group-item-text">' + data[i].price + '</p>'+
			    '<p>star<span class="label label-info">' + data[i].star + '</span>'+
			    '评论总数<span class="label label-info">' + data[i].commentCount + '</span>'+
			    '水军评论<span class="label label-info">' + data[i].waterCount + '</span></p>'+
			  '</div>'+
			'</div>'+
			'</a>');

		/*var item = $('<li class="product">'+
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
					'<li><a href="getcomments?clueid=' + data[i].id + '">推荐评论</a></li>'+
	              	'<li><a href="getKeywordComments?kw=屏幕&clueid=' + data[i].id + '">屏幕</a></li>'+
	              	'<li><a href="getKeywordComments?kw=像素&clueid=' + data[i].id + '">像素</a></li>'+
	              	'<li><a href="getKeywordComments?kw=内存&clueid=' + data[i].id + '">内存</a></li>'+
	              	'<li><a href="getKeywordComments?kw=速度&clueid=' + data[i].id + '">速度</a></li>'+
	              	'<li><a href="getKeywordComments?kw=CPU&clueid=' + data[i].id + '">CPU</a></li>'+
	              	'<li><a href="getKeywordComments?kw=物流&clueid=' + data[i].id + '">物流</a></li>'+
	            '</ul>'+
	            '<div class="comment-list">'+
	            	'<ul class="pager">'+
	            		'<li><a href="preComm">上一页</a></li>'+
	            		'<li><a href="nextComm">下一页</a></li>'+
	            	'</ul>'+
	            	'<ul id="cList"></ul>'+
	            '</div>'+
	        '</div>'+
		'</li>');*/
			
			
			
//			$('<li class="product-item">' + '<a href="' + data[i].page
//				+ '">' + '<img class="product-img img-rounded" src="'
//				+ data[i].img + '">' + '<div style="margin-left: 200px;">'
//				+ '<h3>' + data[i].name + '</h3>' + '<p>' + data[i].price
//				+ '</p>' + '</div>' + '<a href="getcomments?clueid='
//				+ data[i].commentClueid + '">推荐评论 ('
//				+ (data[i].commentCount - data[i].waterCount) + '/'
//				+ (data[i].commentCount - 0) + ')</a>'
//				+ '<ul class="comment-list"></ul>' + '</a>' + '</li>');
		/*if (data[i].commentCount != null) {
			item.css("background-color", "rgb(145, 218, 173)");
		}*/
		$("#product-list").append(item);
		
	}
	$(".pro-item").click(detailEvent);
/*
	// 添加事件
	$(".cTab a").toggle(getCommentEvent, function(){
		var commentList = $(this).parents(".commentTab").next(".comment-list");
		if (commentList.is(":hidden")) {
			getCommentEvent();
		} else {
			commentList.slideUp();
		}
	});*/
}

// 用JSON组装product-item
function loadPItem(json) {
	data = json;
	pNowPage = 0;
	pPageCount = Math.ceil(data.length / 20);
	pPager(data, pNowPage);

	$("#page-tag").text("第" + (pNowPage + 1) + "/" + pPageCount + "页        总商品数：" + data.length);

}
