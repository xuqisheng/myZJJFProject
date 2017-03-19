/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.corner.salesman.modules.gen.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corner.salesman.common.config.Global;
import com.corner.salesman.common.persistence.Page;
import com.corner.salesman.common.service.BaseService;
import com.corner.salesman.common.utils.StringUtils;
import com.corner.salesman.modules.gen.dao.GenSchemeDao;
import com.corner.salesman.modules.gen.dao.GenTableColumnDao;
import com.corner.salesman.modules.gen.dao.GenTableDao;
import com.corner.salesman.modules.gen.entity.GenConfig;
import com.corner.salesman.modules.gen.entity.GenScheme;
import com.corner.salesman.modules.gen.entity.GenTable;
import com.corner.salesman.modules.gen.entity.GenTableColumn;
import com.corner.salesman.modules.gen.entity.GenTemplate;
import com.corner.salesman.modules.gen.util.GenUtils;

/**
 * 生成方案Service
 * @author ThinkGem
 * @version 2013-10-15
 */
@Service
@Transactional(readOnly = true)
public class GenSchemeService extends BaseService {

	@Autowired
	private GenSchemeDao genSchemeDao;
//	@Autowired
//	private GenTemplateDao genTemplateDao;
	@Autowired
	private GenTableDao genTableDao;
	@Autowired
	private GenTableColumnDao genTableColumnDao;
	
	public GenScheme get(String id) {
		return genSchemeDao.get(id);
	}
	
	public Page<GenScheme> find(Page<GenScheme> page, GenScheme genScheme) {
		GenUtils.getTemplatePath();
		genScheme.setPage(page);
		page.setList(genSchemeDao.findList(genScheme));
		return page;
	}
	
	@Transactional(readOnly = false)
	public String save(GenScheme genScheme) {
		if (StringUtils.isBlank(genScheme.getId())){
			genScheme.preInsert();
			genSchemeDao.insert(genScheme);
		}else{
			genScheme.preUpdate();
			genSchemeDao.update(genScheme);
		}
		// 生成代码
		if ("1".equals(genScheme.getFlag())){
			return generateCode(genScheme);
		}
		return "";
	}
	
	@Transactional(readOnly = false)
	public void delete(GenScheme genScheme) {
		genSchemeDao.delete(genScheme);
	}
	
	/**
	 * 检查配置文件中配置的srcPath是否正确。
	 * @return
	 */
	public boolean checkSrcPathConfig(){
		String srcPath = Global.getConfig("srcPath");
		if(StringUtils.isBlank(srcPath)){
			logger.error("未在“salesman-web.properties”中配置“srcPath”！");
			return false;
		}
		
		//获取包的路径
		String packagePath = StringUtils.replaceEach("src.main.java." + GenUtils.class.getName(),
				new String[]{"util." + GenUtils.class.getSimpleName(), "."},
				new String[]{"template", File.separator});
		
		//拼接config文件路径，用来验证设置的path是否正确。
		String configPath = srcPath + File.separator + packagePath + File.separator + "config.xml";
		
		logger.info("计算出来的config.xml的位置为：" + configPath);
		
		File configFile = new File(configPath);
		if(!configFile.exists()){
			return false;
		}
		
		return true;
	}
	
	private String generateCode(GenScheme genScheme){

		StringBuilder result = new StringBuilder();
		
		// 查询主表及字段列
		GenTable genTable = genTableDao.get(genScheme.getGenTable().getId());
		genTable.setColumnList(genTableColumnDao.findList(new GenTableColumn(new GenTable(genTable.getId()))));
		
		// 获取所有代码模板
		GenConfig config = GenUtils.getConfig();
		
		// 获取模板列表
		List<GenTemplate> templateList = GenUtils.getTemplateList(config, genScheme.getCategory(), false);
		List<GenTemplate> childTableTemplateList = GenUtils.getTemplateList(config, genScheme.getCategory(), true);
		
		// 如果有子表模板，则需要获取子表列表
		if (childTableTemplateList.size() > 0){
			GenTable parentTable = new GenTable();
			parentTable.setParentTable(genTable.getName());
			genTable.setChildList(genTableDao.findList(parentTable));
		}
		
		// 生成子表模板代码
		for (GenTable childTable : genTable.getChildList()){
			childTable.setParent(genTable);
			childTable.setColumnList(genTableColumnDao.findList(new GenTableColumn(new GenTable(childTable.getId()))));
			genScheme.setGenTable(childTable);
			Map<String, Object> childTableModel = GenUtils.getDataModel(genScheme);
			for (GenTemplate tpl : childTableTemplateList){
				result.append(GenUtils.generateToFile(tpl, childTableModel, genScheme.getReplaceFile()));
			}
		}
		
		// 生成主表模板代码
		genScheme.setGenTable(genTable);
		Map<String, Object> model = GenUtils.getDataModel(genScheme);
		for (GenTemplate tpl : templateList){
			result.append(GenUtils.generateToFile(tpl, model, genScheme.getReplaceFile()));
		}
		return result.toString();
	}
}
