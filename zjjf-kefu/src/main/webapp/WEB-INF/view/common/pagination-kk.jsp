<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script src="${root}/resources/vendor/kkpager-1.3/kkpager.min.js"></script>
<link rel="stylesheet" href="${root}/resources/vendor/kkpager-1.3/kkpager_blue_custom.css" />
<div style="padding:10px 30px">
	<div id="kkpager"></div>
</div>
<script>
	$(function() {
		var totalPage = ${page.allpagecount};
		//var totalRecords = ${page.totalCount};
		var pageNo = ${page.pageIndex};
		if (!pageNo) {
			pageNo = 1;
		}
		//生成分页
		//有些参数是可选的，比如lang，若不传有默认值
		kkpager.generPageHtml({
			pno : pageNo,
			//总页码
			total : totalPage,
			//总数据条数
			//totalRecords : totalRecords,
			//链接前部
			hrefFormer : '${page.reqURI}',
			//链接尾部
			hrefLatter : '',
			getLink : function(n) {
				if(this.hrefFormer.indexOf("?")>0){
					return this.hrefFormer + this.hrefLatter + "&pageIndex=" + n;
				}else{
					return this.hrefFormer + this.hrefLatter + "?pageIndex=" + n;
				}

			},
			lang : {
				/* firstPageText : '首页',
				lastPageText : '尾页',  */
				prePageText : '上一页',
				nextPageText : '下一页',
				totalPageBeforeText : '共',
				totalPageAfterText : '页',
				totalRecordsAfterText : '条数据',
				gopageBeforeText : '跳转到',
				gopageButtonOkText : '确定',
				gopageAfterText : '',
				buttonTipBeforeText : '第',
				buttonTipAfterText : '页'
			}
		});
	});
</script>
