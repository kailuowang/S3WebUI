package com.thoughtDocs.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 24, 2009
 * Time: 4:41:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class EntranceUrlMappingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s = request.getRequestURI();
        StringBuffer sb = new StringBuffer(s);
        if (s.equals("/")) {
            response.sendRedirect("/s3/applets/");
        } else if (!s.matches("(/s3/|/manager/|/index.html).*")) {
            response.sendRedirect("/s3/get" + request.getRequestURI());
        }
        response.sendRedirect(request.getRequestURI().replaceAll(".com/", ".com/DocumentDownloadServlet.seam?key="));
    }
}
