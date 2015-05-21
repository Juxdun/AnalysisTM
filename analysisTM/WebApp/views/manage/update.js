/**
 * update.jsp的js操作
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
	//1. “搜索”按钮添加事件
	$("form .search-pro").click(searchEvent);
	
	// 2.修改商品
	$("form .change-pro-info").click(chageProInfoEvent);
	
	$("#gotoPageCI").click(function () {
		// 跳到相应Tab
		$('#myTab a[href="#change-info"]').tab('show');
	});
	
// 3.插入和连接所有点击事件
	$("a[href='insertBrands']").click(insertBrandsEvent);
	
	$("a[href='insertProducts']").click(insertProductsEvent);
	
	$("a[href='insertComments']").click(insertCommentsEvent);
	
	$("a[href='linkB2P']").click(linkEvent);
	
	$("a[href='linkP2C']").click(linkEvent);
	
	$("a[href='insertAndLink']").click(insertAndLinkEvent);
	
// 分析和统计所有点击事件
	$("a[href='signWater']").click(analyseEvent);
	$("a[href='analysePositive']").click(analyseEvent);
	$("a[href='analyseNegative']").click(analyseEvent);
	
	$("a[href='countComment']").click(couEvent);
	$("a[href='countWater']").click(couEvent);
	$("a[href='countGood']").click(couEvent);
	$("a[href='countBad']").click(couEvent);
	$("a[href='countStar']").click(couEvent);
	$("a[href='oneshotCount']").click(couEvent);
	
	$("a[href='analyseAndCount']").click(analyseAndCouEvent);
	
	//5. 商品上一页
	$("#pre").click(pPreEvent);
	
	//6. 商品下一页
	$("#next").click(pNextEvent);
	
	
});

/*---------------------------------定义方法--------------------------------------*/

function analyseAndCouEvent() {
	
	$("#sureModal").modal();
	$("#sureTitle").text("确定分析并统计？");
	$("#sureContent").text("这一步需要比较长时间，大于4小时！2、没事不要乱搞！");
	
	var url = this.href;
	
	$("#sureBtn").unbind("click").one("click", function(){
		var start = new Date();
		
		$.post(url, function (msg) {
			var end = new Date();
			alert(msg ? "成功" + (start-end) / 1000 + "s" :
				"失败" + (start-end) / 1000 + "s");
		});
		alert("开始。完成后弹出提示框提示你！" + start);
	});
	
	return false;
}

function couEvent() {
	
	$("#sureModal").modal();
	$("#sureTitle").text("确定统计？");
	$("#sureContent").text("1、这一步并不需要很长时间！");
	
	var url = this.href;
	
	$("#sureBtn").unbind("click").one("click", function(){
		var start = new Date();
		
		$.post(url, function (msg) {
			var end = new Date();
			alert(msg ? "成功" + (start-end) / 1000 + "s" :
				"失败" + (start-end) / 1000 + "s");
		});
		alert("统计开始。完成后弹出提示框提示你！" + start);
	});
	
	return false;
}

function analyseEvent() {
	
	$("#sureModal").modal();
	$("#sureTitle").text("确定分析？");
	$("#sureContent").text("1、这一步需要比较长时间，大于0.5小时！2、没事不要乱搞！");
	
	var url = this.href;
	
	$("#sureBtn").unbind("click").one("click", function(){
		var start = new Date();
		
		$.post(url, function (msg) {
			var end = new Date();
			alert(msg ? "成功" + (start-end) / 1000 + "s" :
				"失败" + (start-end) / 1000 + "s");
		});
		alert("分析开始。完成后弹出提示框提示你！" + start);
	});
	
	return false;
}

function insertAndLinkEvent() {
	
	$("#sureModal").modal();
	$("#sureTitle").text("确定插入所有表并连接吗？");
	$("#sureContent").text("1、这一步需要超级长时间，大于8小时！2、原理是把以上步骤集合起来！3、没事不要乱搞！");
	
	var url = this.href;
	
	$("#sureBtn").unbind("click").one("click", function(){
		var start = new Date();
		
		$.post(url, function (msg) {
			var end = new Date();
			alert(msg ? "一键插入并连接成功" + (start-end) / 1000 + "s" :
				"一键插入并连接失败" + (start-end) / 1000 + "s");
		});
		alert("一键插入并连接。完成后弹出提示框提示你！" + start);
	});
	
	return false;
}

function linkEvent() {
	
	$("#sureModal").modal();
	$("#sureTitle").text("确定链接2表吗？");
	$("#sureContent").text("1、如果没什么事情请不要重新链接2表，数据量大需要很长时间！2、连接后可能因为抓取数据里面的URL可能不同，导致有些连接不准确。需要手动到数据库整理！");
	
	var url = this.href;
	
	$("#sureBtn").unbind("click").one("click", function(){
		var start = new Date();
		
		$.post(url, function (msg) {
			var end = new Date();
			alert(msg ? "成功" : "失败" + (start-end) / 1000 + "s");
		});
		alert("链接。完成后弹出提示框提示你！" + start);
	});
	
	return false;
}

function insertBrandsEvent() {
	
	$("#sureModal").modal();
	$("#sureTitle").text("确定重新插入Brand表吗？");
	$("#sureContent").text("1、如果没什么事情请不要重新插入表，需要很长时间。\n2、先清空Brand表。再执行这个操作。会比较干净。");
	
	var url = this.href;
	
	$("#sureBtn").unbind("click").one("click", function(){
		var start = new Date();
		
		$.post(url, function (msg) {
			var end = new Date();
			alert(msg ? "更新成功" : "更新失败" + (start-end) / 1000 + "s");
		});
		alert("开始插入数据。完成后弹出提示框提示你！" + start);
	});
	
	return false;
}

function insertProductsEvent() {
	
	$("#sureModal").modal();
	$("#sureTitle").text("确定重新插入Product表吗？");
	$("#sureContent").text("1、如果没什么事情请不要重新插入表，需要很长时间。\n2、先清空Product表。再执行这个操作。会比较干净。");
	
	var url = this.href;
	
	$("#sureBtn").unbind("click").one("click", function(){
		var start = new Date();
		
		$.post(url, function (msg) {
			var end = new Date();
			alert(msg ? "更新成功" : "更新失败" + (start-end) / 1000 + "s");
		});
		alert("开始插入数据。完成后弹出提示框提示你！" + start);
	});
	
	return false;
}

function insertCommentsEvent() {
	
	$("#sureModal").modal();
	$("#sureTitle").text("确定重新插入Comment表吗？");
	$("#sureContent").text("1、如果没什么事情请不要重新插入表，需要很长时间，大约6个小时插入30w条数据。\n2、先清空Comment表。再执行这个操作。会比较干净。");
	
	var url = this.href;
	
	$("#sureBtn").unbind("click").one("click", function(){
		var start = new Date();
		
		$.post(url, function (msg) {
			var end = new Date();
			alert(msg ? "更新成功" : "更新失败" + (start-end) / 1000 + "s");
		});
		alert("开始插入数据。完成后弹出提示框提示你！" + start);
	});
	
	return false;
}

function chageProInfoEvent() {
	
	var id = $("#id").val();
	var name = $("#name").val();
	var resume = $("#resume").val();
	var price = $("#price").val();
	var img = $("#img").val();
	var star = $("#star").val();
	
	var url = $(this).parents("form").attr("action");
	var args = {
			"id": id,
			"name": name,
			"resume": resume,
			"price": price,
			"img": img,
			"star": star
	};
	$.post(url, args, function (msg) {
		alert(msg ? "更新成功" : "更新失败");
	});
	
	$('#detailmodal').modal('hide');
	return false;
}

function detailEvent() {
	
	$("#detailmodal").modal();
	
	nowProId = $(this).attr("id");
	var pro = data[nowProId];
	
	$("#myModalLabel").text(pro.name);
	$("#modalImg").attr("src", pro.img);
	$("#modalPage").attr("href", pro.page);
	$("#modalResume").text(pro.resume);
	$("#modalPrice").text(pro.price);
	
	$("#id").val(pro.id);
	$("#name").val(pro.name);
	$("#resume").val(pro.resume);
	$("#price").val(pro.price);
	$("#img").val(pro.img);
	$("#star").val(pro.star);
	
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
	// 跳到相应Tab
	$('#myTab a[href="#change-info"]').tab('show');
	
	var wd = $(this).parent().find(".keyword").val();
	wd = $.trim(wd);
	if (wd == "") {
		return false;
	}
	// 清空列表
	$("#product-list").empty();

	var url = $(this).parent().attr("action");
	
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
		$("#product-list").append(item);
		
	}
	$(".pro-item").click(detailEvent);

}

// 用JSON组装product-item
function loadPItem(json) {
	data = json;
	pNowPage = 0;
	pPageCount = Math.ceil(data.length / 20);
	pPager(data, pNowPage);

	$("#page-tag").text("第" + (pNowPage + 1) + "/" + pPageCount + "页        总商品数：" + data.length);

}
