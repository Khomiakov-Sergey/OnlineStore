package by.it.academy.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * This filter class is responsible for encoding. This filter helps to use russian text in request correct.
 */
@WebFilter(filterName = "encodingFilter", urlPatterns = {"/*"})
public class EncodingFilter implements Filter {

    /**
     * This method init filter.
     */
    @Override
    public void init(FilterConfig fConfig) {
    }

    /**
     * This method adds in all request character encoding UTF-8.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

    /**
     * This method destroy filter.
     */
    @Override
    public void destroy() {
    }

}