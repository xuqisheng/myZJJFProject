package com.zjjf.analysis.mapper.origin;

import java.util.List;

import com.zjjf.analysis.beans.origin.base.Region;

public interface RegionMapper {
    
   List<Region> selectByIndex(Integer index);
   
   Region getById(Integer id);
}