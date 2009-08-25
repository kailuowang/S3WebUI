package com.thoughtDocs.util;

import com.thoughtDocs.model.impl.s3.UserS3Store;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Aug 9, 2009
 * Time: 10:55:43 AM
 */

public class RestfulDownloadFilter implements Filter {
    private FilterConfig filterConfig;
    private static final String PUBLIC_DOWNLOAD_SITE = "thoughtfiles.com";

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        if(req.getServerName().toLowerCase().indexOf(PUBLIC_DOWNLOAD_SITE) >= 0)
        {

            final HttpServletRequest request = (HttpServletRequest) req;
            final HttpServletResponse response = (HttpServletResponse) resp;
            String contextPath = filterConfig.getServletContext().getContextPath() + "/";
            String url = request.getRequestURL().toString().replaceFirst(PUBLIC_DOWNLOAD_SITE,
                                                                      "thoughtdocs.com");
            String username =   getUsername(request.getRequestURL().toString());
            url = url.replace(request.getRequestURI(), contextPath + "documentPublicDownload.seam?username="+ username + "&key="
                                    + request.getRequestURI().replaceFirst(contextPath, ""));

            response.sendRedirect(url);

        }else
            chain.doFilter(req, resp);
    }

    private String getUsername(String url) {
        int end = url.toLowerCase().indexOf( "."+ PUBLIC_DOWNLOAD_SITE);
        if(end > 0){
            int begin = url.toLowerCase().indexOf("://") + 3;
            return url.substring(begin, end);
        }
        return UserS3Store.DEFAULT_USERNAME;
    }

    public void init(FilterConfig config) throws ServletException {
          this.filterConfig = config;
    }

}
