package by.it.academy.filters;


import by.it.academy.entities.User;
import by.it.academy.repositories.connection.DataSource;
import by.it.academy.repositories.user.UserApiRepository;
import by.it.academy.repositories.user.UserRepository;
import by.it.academy.services.user.UserApiService;
import by.it.academy.services.user.UserService;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

import static by.it.academy.utils.Constants.LOGIN_PAGE;
import static by.it.academy.utils.Constants.USER_REGISTRATION_PATH;

/**
 * This filter class is responsible for filtering user`s logins. Only unique login can pass this filter.
 */
@Log4j
@WebFilter(urlPatterns = USER_REGISTRATION_PATH)
public class UniqLoginFilter implements Filter {

    private final Session hibernateSession = DataSource.getInstance().getSession();

    private final UserRepository<User> repository = new UserApiRepository(hibernateSession);
    private final UserService<User> service = new UserApiService(repository, hibernateSession);

    @Override
    public void init(FilterConfig filterConfig) {

    }

    /**
     * This method checks the uniqueness of the user's login.
     */
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

    /**
     * This method destroy filter.
     */
    @Override
    public void destroy() {

    }
}
