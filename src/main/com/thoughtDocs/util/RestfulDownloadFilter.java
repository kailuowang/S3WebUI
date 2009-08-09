package com.thoughtDocs.util;

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

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        if(req.getServerName().toLowerCase().indexOf("thoughtfiles.com") >= 0)
        {

            final HttpServletRequest request = (HttpServletRequest) req;
            final HttpServletResponse response = (HttpServletResponse) resp;
            String contextPath = filterConfig.getServletContext().getContextPath();
            if(contextPath.length() > 1)
             contextPath  = contextPath + "/";
            String url = request.getRequestURL().toString().replace("thoughtfiles.com",
                                                                                 "thoughtdocs.com");
            url = url.replace(request.getRequestURI(), contextPath + "documentPublicDownload.seam?key="
                                    + request.getRequestURI().replace(contextPath, ""));

            response.sendRedirect(url);

        }else
            chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
          this.filterConfig = config;
    }

}
