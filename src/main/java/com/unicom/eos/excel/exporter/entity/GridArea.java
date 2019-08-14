package com.unicom.eos.excel.exporter.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class GridArea extends BaseRowModel implements Serializable {

    private static final long serialVersionUID = 8217774531916588743L;

    @ExcelProperty(value = "省份编码", index = 0)
    private String provinceCode;
    @ExcelProperty(value = "省份名称", index = 1)
    private String provinceName;
    @ExcelProperty(value = "地市编码", index = 2)
    private String cityCode;
    @ExcelProperty(value = "地市名称", index = 3)
    private String cityName;
    @ExcelProperty(value = "网格ID", index = 4)
    private String gridId;
    @ExcelProperty(value = "网格名称", index = 5)
    private String gridName;

}
