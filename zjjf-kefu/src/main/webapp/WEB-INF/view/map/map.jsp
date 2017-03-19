<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="百度地图,百度地图API，百度地图自定义工具，百度地图所见即所得工具" />
    <meta name="description" content="百度地图API自定义地图，帮助用户在可视化操作下生成百度地图" />
    <title>转角加盟商铺分布图</title>
    <%@ include file="../common/head.jsp"%>
	<script src="${root}/resources/vendor/My97DatePicker/WdatePicker.js"></script>
	<script src="${root}/resources/js/comm.js"></script>
    <!--引用百度地图API-->
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=8xuBaRnKq8PdXeLXCUDdQRvO"></script>
</head>
  
  <body>
    <!--百度地图容器-->
    <div style="width:100%;height:730px;border:#ccc solid 1px;font-size:12px" id="map"></div>
    <div>
    	<input type="hidden" id="regionId" value="707">
    	<input type="button" value="南山区" onclick="selectRegion(707)">
    	<input type="button" value="宝安区" onclick="selectRegion(708)">
    	<input type="button" value="龙岗区" onclick="selectRegion(709)">
    	<input type="button" value="福田区" onclick="selectRegion(705)">
    	<!-- <input type="button" value="坂田区" onclick="selectRegion()">
    	<input type="button" value="布吉区" onclick="selectRegion()"> -->
    	<input type="button" value="横岗区" onclick="selectRegion(3407)">
    	<!-- <input type="button" value="龙华区" onclick="selectRegion()"> -->
    	<input type="button" value="坪山区" onclick="selectRegion(3406)">
    	<input type="button" value="罗湖区" onclick="selectRegion(706)">
    </div>
  </body>
  <script type="text/javascript">
  var zuoBiao = {"x":"113.93695","y":"22.539017"};
  function selectRegion(regionId){
  	if(regionId == 708){
  		zuoBiao = {"x":"113.889506","y":"22.559626"}
  	}else if(regionId == 709){
  		zuoBiao = {"x":"114.254244","y":"22.726228"}
  	}else if(regionId == 705){
  		zuoBiao = {"x":"114.061087","y":"22.528578"}
  	}else if(regionId == 3407){
  		zuoBiao = {"x":"114.220749","y":"22.646695"}
  	}else if(regionId == 3406){
  		zuoBiao = {"x":"114.352756","y":"22.69697"}
  	}else if(regionId == 706){
  		zuoBiao = {"x":"114.137543","y":"22.555234"}
  	}else if(regionId == 707){
  		zuoBiao = {"x":"113.93695","y":"22.539017"};
  	}else{
  		zuoBiao = {"x":"114.066112","y":"22.548515"};
  	}
  	
  	$("#regionId").val(regionId);
  	initMap();
  }
  
    //创建和初始化地图函数：
    function initMap(){
      createMap();//创建地图
      setMapEvent();//设置地图事件
      addMapControl();//向地图添加控件
      addMapOverlay();//向地图添加覆盖物
    }
    function createMap(){ 
      map = new BMap.Map("map"); 
      map.centerAndZoom(new BMap.Point(zuoBiao.x,zuoBiao.y),14);
    }
    function setMapEvent(){
      map.enableScrollWheelZoom();
      map.enableKeyboard();
      map.enableDragging();
      map.enableDoubleClickZoom()
    }
    function addClickHandler(target,window){
      target.addEventListener("click",function(){
        target.openInfoWindow(window);
      });
    }
    function addMapOverlay(){
      var markers = [];
      $.post("${root}/customer/appStore/getAllStoreCoordinates.do",{regionId:$("#regionId").val()},function(data){
	  		if(data.success){
	  			markers=JSON.parse(data.message);

	  			console.log(typeof markers);
	  			for(var index = 0; index < markers.length; index++ ){
	  		        var point = new BMap.Point(markers[index].position.lng,markers[index].position.lat);
	  		        var marker = new BMap.Marker(point,{icon:new BMap.Icon("http://www.izjjf.cn/group1/M00/01/51/cEpChldjyXaAC1j8AAAJEPjuBww616.png",new BMap.Size(36,58),{
	  		          imageOffset: new BMap.Size(markers[index].imageOffset.width,markers[index].imageOffset.height)
	  		        })});
	  		        var label = new BMap.Label(markers[index].title,{offset: new BMap.Size(25,5)});
	  		        var opts = {
	  		          width: 200,
	  		          title: markers[index].title,
	  		          enableMessage: false
	  		        };
	  		        var infoWindow = new BMap.InfoWindow(markers[index].content,opts);
	  		       /* marker.setLabel(label); */
	  		        addClickHandler(marker,infoWindow);
	  		        map.addOverlay(marker);
	  		      };
	  		    console.log(markers)
	  		}
  	  },'json')
     
    }
    //向地图添加控件
    function addMapControl(){
      var scaleControl = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
      scaleControl.setUnit(BMAP_UNIT_IMPERIAL);
      map.addControl(scaleControl);
      var navControl = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
      map.addControl(navControl);
      var overviewControl = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:true});
      map.addControl(overviewControl);
    }
    var map;
      initMap();
  </script>
</html>