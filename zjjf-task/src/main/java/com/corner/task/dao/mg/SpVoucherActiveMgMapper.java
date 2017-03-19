package com.corner.task.dao.mg;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.corner.task.beans.SpVoucherActive;

@Repository
public interface SpVoucherActiveMgMapper {

	List<SpVoucherActive> getAllFinishActive();

	void updateFinishActive(List<SpVoucherActive> list);

	void updateAllFinishActive();

	void updateStartAllActive();

}
