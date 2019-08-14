package com.unicom.eos.excel.exporter.mapper;

import com.unicom.eos.excel.exporter.entity.GridArea;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GridAreaMapper {


    @Select("select g.province_code_post,g.city_code,g.grid_name, g.grid_id " +
            "from grid_area g where g.grid_state = 1 and g.province_code_post = #{provinceCode} ORDER BY g.city_code")
    @Results({
            @Result(column = "province_code_post", property = "provinceCode"),
            @Result(column = "grid_name", property = "gridName"),
            @Result(column = "grid_id", property = "gridId"),
            @Result(column = "city_code", property = "cityCode")
    })
    List<GridArea> findGridAreaListByProvinceCode(@Param("provinceCode") String provinceCode);
}