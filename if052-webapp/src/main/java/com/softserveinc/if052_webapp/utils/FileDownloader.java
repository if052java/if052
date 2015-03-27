package com.softserveinc.if052_webapp.utils;

import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Danylo Tiahun on 17.03.2015.
 */

@Component
public class FileDownloader {

    public static final int BUFFER_SIZE = 4096;

    public void downloadFile(HttpServletRequest request, HttpServletResponse response, String report) throws IOException{

        String filePath = "/report.xml";
        ServletContext context = request.getServletContext();
        String appPath = context.getRealPath("");
        String fullPath = appPath + filePath;
        File downloadFile = new File(fullPath);
        PrintWriter writer = new PrintWriter(fullPath, "UTF-8");
        writer.println(report);
        writer.close();
        FileInputStream inputStream = new FileInputStream(downloadFile);
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);
        OutputStream outStream = response.getOutputStream();
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        inputStream.close();
        outStream.close();
    }

}
