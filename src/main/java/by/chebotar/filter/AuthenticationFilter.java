package by.chebotar.filter;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.ServletException;
import javax.servlet.FilterChain;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "authenticationFilter" /*, urlPatterns = {"/index"}*/)
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        /*User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user != null){
            httpServletRequest.getRequestDispatcher("../jsp/main.jsp")
                    .forward(httpServletRequest,httpServletResponse);
        } else {
            httpServletRequest.getRequestDispatcher("../jsp/login.jsp")
                    .forward(httpServletRequest,httpServletResponse);
        }*/
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}