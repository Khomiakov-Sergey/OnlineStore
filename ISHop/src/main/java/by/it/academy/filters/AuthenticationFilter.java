package by.it.academy.filters;

import lombok.extern.log4j.Log4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

import static by.it.academy.utils.Constants.LOGIN_PAGE;
import static by.it.academy.utils.Constants.PRODUCT_BUY_PATH;

@Log4j
@WebFilter(urlPatterns = PRODUCT_BUY_PATH)
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
            log.info("User " + session.getAttribute("loginedUser") + " went through the AuthenticationFilter");
        } else {
            String error = "First you have to enter the authorization";
            httpServletRequest.setAttribute("error", error);
            log.info("Unknown user trying to buy product");
            final RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher(LOGIN_PAGE);
            requestDispatcher.forward(httpServletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
