package com.softserveinc.if052_webapp.utils;


import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Danylo Tiahun on 17.03.2015.
 */

@Component
public class FileDownloader {

    public static final int BUFFER_SIZE = 4096;

    public void downloadFile(HttpServletRequest request, HttpServletResponse response, String report) throws IOException {

        response.setContentType("application/xml");
        response.setHeader("Content-Disposition", "attachment;filename=report.xml");
        InputStream in = new ByteArrayInputStream(report.getBytes("UTF-8"));
        ServletOutputStream out = response.getOutputStream();
        byte[] outputByte = new byte[BUFFER_SIZE];
        int bytesRead;
        while ((bytesRead = in.read(outputByte)) != -1) {
            out.write(outputByte, 0, bytesRead);
        }
        in.close();
        out.flush();
        out.close();

    }
}
