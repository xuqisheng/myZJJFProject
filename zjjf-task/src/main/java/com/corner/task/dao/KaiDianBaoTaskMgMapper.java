package com.corner.task.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.corner.task.beans.PlantItem;
import com.corner.task.beans.PlantItemPre;

/**
 * 
 * @ClassName: ItemBaseDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 铁中棠 tiezhongtang@izjjf.cn
 * @date 2016年3月9日 下午5:46:21
 *
 */
@Repository
public interface KaiDianBaoTaskMgMapper {

	/**updateKDBPlantItemPrice**/
	public List<PlantItemPre> getAllPlantItemPre();
	public void insertPlantItemList(List<PlantItemPre> insertList);
	public void removeAllPlantItemPre();
	public void insertPlantItemLogList(List<PlantItemPre> listPre);
	public void updateKDBPlantItemPrice(String splitNo);
	public void insertOrUpdatePlantItem(List<PlantItemPre> list);
	public void batchAddPlantItemLog(List<PlantItemPre> list);
	public PlantItem getPlantItemByPlantItemPreId(String id);
	public void updatePlantItemByPlantItemPre(PlantItemPre plantItemPre);
	public void insertLog(PlantItemPre plantItemPre);
	public String checkItemIsHave(PlantItemPre plantItemPre);
	public void deleteFromPlantItemPreById(PlantItemPre plantItemPre);

}
