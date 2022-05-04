package by.it.academy.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebFilter(urlPatterns = "/product/buy")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        final HttpSession session = httpServletRequest.getSession();
        if (Objects.nonNull(session) && Objects.nonNull(session.getAttribute("loginedUser"))) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String errorString = "First you have to enter the authorization";
            httpServletRequest.setAttribute("errorString", errorString);
            final RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("/pages/user/login.jsp");
            requestDispatcher.forward(httpServletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
