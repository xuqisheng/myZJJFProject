package com.zjjf.analysis.mapper.origin;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.analysis.items.ItemBase;

public interface ItemBaseMapper {

	ItemBase getById(Integer id);

	List<Integer> getByLikeNameOrMisde(HashMap<String, Object> paramMap);
}