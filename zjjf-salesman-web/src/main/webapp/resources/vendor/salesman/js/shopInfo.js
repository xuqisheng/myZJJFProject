function uploadPicFunc(){
    layer.open({
        type: 2,
        title: '',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['800px' , '500px'],
        content: rootPath+"/base/toUploadPic.do"
    }); 
}

function initPicUrl(){
	var picUrls = null;
	$(".picUl").find("img").each(function(){
		//alert(this.src);
		if(picUrls == null ){
			picUrls = this.src;
		}else{
			picUrls += ","+this.src;
		}
	});
	
	$("#picUrl").val(picUrls);
}

function delPic(obj){
	var picUrls = null;
	$(".picUl").find("img").each(function(){
		//alert(this.src);
		if(picUrls == null ){
			picUrls = this.src;
		}else{
			picUrls += ","+this.src;
		}
	});
	//alert(picUrls);
	 if(confirm('确定删除吗?')){
		 $(obj).parent().remove();
	 }
}

function uploadPicCallback(urls){
	//alert("测试上传图片为："+urls);
	var urlSet = urls.split(",");
	for(i=0;i<urlSet.length;i++){
		//alert(urlSet[i]);
		var html = "<li><img src=\""+urlSet[i]+"\" width=\"185px;\" height=\"135px;\"/></br><a onclick=\"delPic(this);\">删除</a></li>";
		$(".picUl").append(html);
	}
	
	layer.closeAll('iframe'); //关闭所有的iframe层
}