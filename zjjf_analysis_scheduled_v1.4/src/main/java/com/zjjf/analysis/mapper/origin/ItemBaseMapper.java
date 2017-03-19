package com.zjjf.analysis.mapper.origin;

import java.util.List;

import com.zjjf.analysis.beans.origin.items.ItemBase;

public interface ItemBaseMapper {

    List<ItemBase> selectByIndex(Integer index);
    
    ItemBase getById(Integer id);
}