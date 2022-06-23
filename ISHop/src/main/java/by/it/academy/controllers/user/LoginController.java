package by.it.academy.controllers.user;

import by.it.academy.entities.User;
import by.it.academy.repositories.connection.DataSource;
import by.it.academy.repositories.user.UserApiRepository;
import by.it.academy.repositories.user.UserRepository;
import by.it.academy.services.user.UserApiService;
import by.it.academy.services.user.UserService;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

import static by.it.academy.utils.Constants.*;

@Log4j
@WebServlet(urlPatterns = USER_LOGIN_PATH)
public class LoginController extends HttpServlet {

    private final Session hibernateSession = DataSource.getInstance().getSession();

    private final UserRepository<User> repository = new UserApiRepository(hibernateSession);
    private final UserService<User> service = new UserApiService(repository, hibernateSession);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        boolean hasError = false;
        String error = null;
        Optional<User> checkedUser = service.getUser(login, password);
        if (!checkedUser.isPresent()) {
            error = " Login or Password incorrect";
            hasError = true;
            log.info("Can`t find user in data base: " + error);
        }
        if (hasError) {
            req.setAttribute("error", error);
            req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
        } else {
            User user = checkedUser.get();
            HttpSession session = req.getSession();
            session.setAttribute("loginedUser", user);
            session.setAttribute("userType", user.getUserType());
            log.info("Opened session for user: " + user.getLogin());
            resp.sendRedirect(req.getContextPath() + USER_INFO_PATH);
        }
    }


}

