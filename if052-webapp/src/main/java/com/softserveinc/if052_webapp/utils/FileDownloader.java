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

    public void downloadFile(HttpServletRequest request, HttpServletResponse response, byte[] output,
                             String contentType, String contentDisposition) {
        response.setContentType(contentType);
        response.setHeader("Content-Disposition", contentDisposition);
        try {
            ServletOutputStream out = response.getOutputStream();
            out.write(output);
            out.flush();
            out.close();
        } catch (IOException e) {

        }

    }
}
