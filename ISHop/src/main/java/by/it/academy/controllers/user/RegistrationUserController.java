package by.it.academy.controllers.user;

import by.it.academy.entities.User;
import by.it.academy.repositories.connection.DBConnection;
import by.it.academy.repositories.connection.MySQLConnection;
import by.it.academy.repositories.user.UserApiRepository;
import by.it.academy.repositories.user.UserRepository;
import by.it.academy.services.user.UserApiService;
import by.it.academy.services.user.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/registration")
public class RegistrationUserController extends HttpServlet {

    private final DBConnection connection = new MySQLConnection();
    private final UserRepository<User> repository = new UserApiRepository(connection);
    private final UserService<User> service = new UserApiService(repository);

    private final static String CREATE_USER_PAGE = "/pages/user/registrationUserPage.jsp";
    private final static String USER_INFO_PATH = "/user/userInfo";

    public RegistrationUserController() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(CREATE_USER_PAGE);
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
        service.create(user);
        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(USER_INFO_PATH);
        requestDispatcher.forward(req, resp);
    }
}
