package com.unicom.eos.excel.exporter.service.impl;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.unicom.eos.excel.exporter.cache.AreaCache;
import com.unicom.eos.excel.exporter.entity.GridArea;
import com.unicom.eos.excel.exporter.mapper.AreaMapper;
import com.unicom.eos.excel.exporter.mapper.GridAreaMapper;
import com.unicom.eos.excel.exporter.service.DataExporter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

@Slf4j
@Service("gridAreaDataExporter")
public class GridAreaDataExporter implements DataExporter {

    @Autowired
    private AreaCache areaCache;
    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private GridAreaMapper gridAreaMapper;

    @Value("${export.excelDir}")
    private String exportDir;

    private static final String FILE_SUFFIX = ".xlsx";

    @Override
    public void export() {
        log.info("导出网格数据开始");
        List<String> allProvinceCode = areaMapper.findAllProvinceCode();
        for (String provinceCode : allProvinceCode) {
            List<GridArea> gridAreaList = fetchData(provinceCode);
            doExport(gridAreaList);
        }
        log.info("导出网格数据结束");
    }


    private void doExport(List<GridArea> gridAreaList){
        if(gridAreaList.isEmpty()){
            return;
        }
        String provinceName = gridAreaList.get(0).getProvinceName();
        log.info("开始导出数据, provinceName: [{}]" , provinceName);
        String fileName = exportDir + provinceName + FILE_SUFFIX;
        try(OutputStream out = new FileOutputStream(new File(fileName))){
            ExcelWriter writer = EasyExcelFactory.getWriter(out);
            Sheet sheet1 = new Sheet(1, 0, GridArea.class);
            sheet1.setSheetName(provinceName);
//            Map<Integer, Integer> columnWidth = new HashMap<>();
//            columnWidth.put(0,10000);
//            columnWidth.put(1,10000);
//            columnWidth.put(2,10000);
//            columnWidth.put(3,50000);
//            columnWidth.put(4,40000);
//            sheet1.setColumnWidthMap(columnWidth);
            sheet1.setAutoWidth(true);
            writer.write(gridAreaList, sheet1);
            writer.finish();
        } catch (Exception e) {
           log.error("导出失败,provineName = " + provinceName, e);
        }
    }
    private List<GridArea> fetchData(String provinceCode){
        log.info("开始查询数据，provinceCode:[{}]", provinceCode);
        List<GridArea> gridAreaList = gridAreaMapper.findGridAreaListByProvinceCode(provinceCode);
        gridAreaList.forEach(gridArea -> {
            gridArea.setProvinceName(areaCache.findProvinceNameByProvinceCode(provinceCode).get());
            gridArea.setCityName(areaCache.findCityNameByCityCode(gridArea.getCityCode()).get());
        });
        return gridAreaList;
    }


}
