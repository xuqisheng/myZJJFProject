package com.zjjf.analysis.mapper.origin;

import com.zjjf.analysis.beans.analysis.items.ItemBase;

import java.util.HashMap;
import java.util.List;

public interface ItemBaseMapper {

	ItemBase getById(Integer id);

	List<Integer> getByLikeNameOrMisde(HashMap<String, Object> paramMap);
}