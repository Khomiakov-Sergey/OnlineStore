package by.it.academy.filters;


import by.it.academy.entities.User;
import by.it.academy.repositories.user.UserApiRepository;
import by.it.academy.repositories.user.UserRepository;
import by.it.academy.services.user.UserApiService;
import by.it.academy.services.user.UserService;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@Log4j
@WebFilter(urlPatterns = "/user/registration")
public class UniqLoginFilter implements Filter {

    private final static String LOGIN_PAGE = "/pages/user/login.jsp";

    private final UserRepository<User> repository = new UserApiRepository();
    private final UserService<User> service = new UserApiService(repository);

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        final Optional<User> userOptional = service.getAllUsers()
                .stream()
                .filter(user -> user.getLogin().equals(httpServletRequest.getParameter("login")))
                .findFirst();
        if (userOptional.isPresent()) {
            String error = "User with this login is already exist!";
            log.info("User with login" + httpServletRequest.getParameter("login") + " is already exist!");
            httpServletRequest.setAttribute("error", error);
            final RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher(LOGIN_PAGE);
            requestDispatcher.forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
