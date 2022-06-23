package by.it.academy.filters;

import by.it.academy.entities.UserType;
import lombok.extern.log4j.Log4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

import static by.it.academy.utils.Constants.*;

@Log4j
@WebFilter(urlPatterns = {PRODUCT_EDIT_PATH, PRODUCT_CREATE_PATH, PRODUCT_DELETE_PATH})
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
                log.info("ADMIN went through AuthorizationFilter");
            } else {
                String error = "You are not ADMIN!";
                log.info("NOT ADMIN was trying to go through AuthorizationFilter and get access to ADMIN methods");
                httpServletRequest.setAttribute("error", error);
                final RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher(LOGIN_PAGE);
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
