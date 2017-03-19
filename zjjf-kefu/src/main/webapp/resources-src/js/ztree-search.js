var ztreeNodeList = [];
function search_ztree(treeId, searchConditionId) {
    var zTreeObj = $.fn.zTree.getZTreeObj(treeId);
    var searchCondition = $.trim($('#' + searchConditionId).val());
    if(searchCondition === "") return;
    updateNodes_ztree(treeId, false);
    ztreeNodeList = zTreeObj.getNodesByParamFuzzy("name", searchCondition);
    updateNodes_ztree(treeId, true);
}
function updateNodes_ztree(treeId, flag) {
    var zTreeObj = $.fn.zTree.getZTreeObj(treeId);
    zTreeObj.expandAll(false);
    for(var i = 0; i < ztreeNodeList.length; i++) {
        ztreeNodeList[i].highlight = flag;
        var parentNode = ztreeNodeList[i].getParentNode();
        zTreeObj.expandNode(parentNode, true, false, true);
        zTreeObj.updateNode(ztreeNodeList[i]);
    }
    zTreeObj.refresh();
}
function setFontCss_ztree(treeId, treeNode) {
    return (!!treeNode.highlight) ? {color:"#ff0000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
}
