package by.it.academy.controllers.user;

import by.it.academy.entities.User;
import by.it.academy.repositories.connection.DBConnection;
import by.it.academy.repositories.connection.MySQLConnection;
import by.it.academy.repositories.user.UserApiRepository;
import by.it.academy.repositories.user.UserRepository;
import by.it.academy.services.user.UserApiService;
import by.it.academy.services.user.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/login")
public class LoginController extends HttpServlet {

    private final DBConnection connection = new MySQLConnection();
    private final UserRepository<User> repository = new UserApiRepository(connection);
    private final UserService<User> service = new UserApiService(repository);

    private final static Logger log = Logger.getLogger(LoginController.class);
    private final static String LOGIN_PAGE = "/pages/user/login.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        boolean hasError = false;
        String errorString = null;
        User user = null;
        if (login == null || password == null || login.length() == 0 || password.length() == 0){
            errorString = " Required login and password!";
            hasError = true;
            log.info(errorString);

        } else {
            user = service.getUser(login, password);
            if (user == null) {
                errorString = " Login or Password incorrect";
                hasError = true;
                log.info(errorString);
            }
        }
        if (hasError){
            req.setAttribute("errorString", errorString);
            req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
        } else{
            HttpSession session = req.getSession();
            session.setAttribute("loginedUser", user);
            log.info("Opened session for user: " + user);
            resp.sendRedirect(req.getContextPath() + "/user/userInfo");
        }
    }
}
