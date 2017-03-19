package com.corner.salesman.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.salesman.dao.TmplDetailInfoMapper;
import com.corner.salesman.model.TmplDetailInfo;
import com.corner.salesman.service.TmplDetailInfoService;
@Service
public class TmplDetailInfoServiceImpl implements TmplDetailInfoService {
	
	@Autowired
	private TmplDetailInfoMapper tmplDetailInfoMapper;
	
	@Override
	public List<TmplDetailInfo> findTmplDetailByTmplId(String tmplId) throws Exception {
		return tmplDetailInfoMapper.findTmplDetailByTmplId(tmplId);
	}

	@Override
	public void addTmplDetailInfo(String tmplId,String[] tmplTrVals, String userId) throws Exception {
		//删除模板信息数据
		tmplDetailInfoMapper.delTmplDetailByTmplId(tmplId);
		//插入最新的模板信息属性
		Date date = new Date();
		for(String tmplTrVal : tmplTrVals){
			TmplDetailInfo detailVo = new TmplDetailInfo();
			String[] tdSet = tmplTrVal.split(",");
			String fieldCnName = tdSet[0];
			String fieldType = tdSet[1];
			String isRequired = tdSet[2];
			String isDelete = tdSet[3];
			String length = tdSet[4];
			String sort = tdSet[5];
			String description = tdSet[6];
			detailVo.setCreateBy(userId);
			detailVo.setCreateTime(date);
			detailVo.setUpdateBy(userId);
			detailVo.setUpdateTime(date);
			detailVo.setTmplId(tmplId);
			detailVo.setIsDelete(0);
			detailVo.setFieldCnName(fieldCnName);
			detailVo.setFieldType(Integer.parseInt(fieldType));
			detailVo.setIsRequired(Integer.parseInt(isRequired));
			detailVo.setIsDelete(Integer.parseInt(isDelete));
			if(!length.equals("NULL")){
				detailVo.setLength(Integer.parseInt(length));
			}
			if(!sort.equals("NULL")){
				detailVo.setSort(Integer.parseInt(sort));
			}
			detailVo.setDescription(description.equals("NULL")?null:description);
			tmplDetailInfoMapper.insertSelective(detailVo);
		}
	}

	@Override
	public void addSingleTmplDetailInfo(String tmplId, String[] tdSet,String userId) throws Exception {
		//删除模板信息数据
		tmplDetailInfoMapper.delTmplDetailByTmplId(tmplId);
		//插入最新的模板信息属性
		Date date = new Date();
		TmplDetailInfo detailVo = new TmplDetailInfo();
		String fieldCnName = tdSet[0];
		String fieldType = tdSet[1];
		String isRequired = tdSet[2];
		String isDelete = tdSet[3];
		String length = tdSet[4];
		String sort = tdSet[5];
		String description = tdSet[6];
		detailVo.setCreateBy(userId);
		detailVo.setCreateTime(date);
		detailVo.setUpdateBy(userId);
		detailVo.setUpdateTime(date);
		detailVo.setTmplId(tmplId);
		detailVo.setIsDelete(0);
		detailVo.setFieldCnName(fieldCnName);
		detailVo.setFieldType(Integer.parseInt(fieldType));
		detailVo.setIsRequired(Integer.parseInt(isRequired));
		detailVo.setIsDelete(Integer.parseInt(isDelete));
		if(!length.equals("NULL")){
			detailVo.setLength(Integer.parseInt(length));
		}
		if(!sort.equals("NULL")){
			detailVo.setSort(Integer.parseInt(sort));
		}
		detailVo.setDescription(description.equals("NULL")?null:description);
		tmplDetailInfoMapper.insertSelective(detailVo);
	}
	
	/**
	 * 根据ID删除模板明细信息方法
	 * @param tmplId
	 * @return
	 * @throws Exception
	 */
	@Override
	public void delTmplDetailInfo(String tmplId) throws Exception{
		tmplDetailInfoMapper.delTmplDetailByTmplId(tmplId);
	}

}
