package com.unicom.eos.excel.exporter;

import com.unicom.eos.excel.exporter.service.DataExporter;
import com.unicom.eos.excel.exporter.service.impl.GridAreaDataExporter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author zhangjiashuai
 * @create 2019/6/18
 */
@SpringBootApplication
@MapperScan("com.unicom.eos.excel.exporter.mapper")
public class ExcelExporterApplication {
    
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ExcelExporterApplication.class);
        DataExporter dataExporter = context.getBean(GridAreaDataExporter.class);
        dataExporter.export();
    }
}
