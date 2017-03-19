$(function($) {
	//复选框的事件  
	$("#chk_all").click(function(){
		//alert($("#chk_all").prop("checked"));
		if($("#chk_all").prop("checked")){
			$("input[name='chk_list']").prop("checked",true);  
		}else{
			$("input[name='chk_list']").prop("checked",false);  
		}
		
	});
});

//子复选框的事件  
function setSelectAll(){  
    //当没有选中某个子复选框时，SelectAll取消选中  
    if (!$("#chk_list").checked) {  
        $("#chk_all").prop("checked", false);  
    }  
    var chsub = $("input[type='checkbox'][name='chk_list']").length; //获取subcheck的个数  
    var checkedsub = $("input[type='checkbox'][name='chk_list']:checked").length; //获取选中的subcheck的个数
    if (checkedsub == chsub) {  
        $("#chk_all").prop("checked", true);  
    }  
}  