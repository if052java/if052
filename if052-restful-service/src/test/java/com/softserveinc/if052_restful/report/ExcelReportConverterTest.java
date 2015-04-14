package com.softserveinc.if052_restful.report;

import com.softserveinc.if052_core.domain.Report;
import com.softserveinc.if052_restful.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.ServletOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:context.xml"})
public class ExcelReportConverterTest {

    @Autowired
    private ExcelReportConverter excelReportConverter;

    @Test
    public void testCreateExcel() throws Exception {


        //System.out.println( excelReportConverter.createExcelReport(new Report()));

        /**InputStream is = new ByteArrayInputStream(excelReportConverter.createExcelReport(new Report()).toByteArray());

        FileOutputStream out = new FileOutputStream(new File("sdf.xlsx"));
        byte[] outputByte = new byte[4096];
        int bytesRead;
        while ((bytesRead = is.read(outputByte)) != -1) {
            out.write(outputByte, 0, bytesRead);
        }
        is.close();
        out.flush();
        out.close();
*/
    }
}