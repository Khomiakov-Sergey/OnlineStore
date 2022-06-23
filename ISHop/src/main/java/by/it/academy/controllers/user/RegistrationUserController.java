package by.it.academy.controllers.user;

import by.it.academy.entities.User;
import by.it.academy.repositories.connection.DataSource;
import by.it.academy.repositories.user.UserApiRepository;
import by.it.academy.repositories.user.UserRepository;
import by.it.academy.services.user.UserApiService;
import by.it.academy.services.user.UserService;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.it.academy.utils.Constants.*;

@Log4j
@WebServlet(urlPatterns = USER_REGISTRATION_PATH)
public class RegistrationUserController extends HttpServlet {

    private final Session hibernateSession = DataSource.getInstance().getSession();

    private final UserRepository<User> repository = new UserApiRepository(hibernateSession);
    private final UserService<User> service = new UserApiService(repository, hibernateSession);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(USER_REGISTRATION_PAGE);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String firstName = req.getParameter("firstName");
        final String secondName = req.getParameter("secondName");
        final int age = Integer.parseInt(req.getParameter("age"));
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        final User user = new User(firstName, secondName, age, login, password);
        log.info("We are trying to register user from controller" + user);
        service.create(user);
        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(USER_INFO_PATH);
        requestDispatcher.forward(req, resp);
    }
}
