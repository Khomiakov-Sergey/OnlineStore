package by.it.academy.filters;

import by.it.academy.entities.UserType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebFilter(urlPatterns = {"/product/update", "/product/create", "/product/delete"})
public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        final HttpSession session = httpServletRequest.getSession();
        if (Objects.nonNull(session) && Objects.nonNull(session.getAttribute("userType"))) {
            final UserType userType = (UserType) session.getAttribute("userType");
            if (userType == UserType.ADMIN) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                String errorString = "You are not ADMIN!";
                httpServletRequest.setAttribute("errorString", errorString);
                final RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("/pages/user/login.jsp");
                requestDispatcher.forward(httpServletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
