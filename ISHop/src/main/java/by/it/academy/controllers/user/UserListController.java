package by.it.academy.controllers.user;

import by.it.academy.entities.User;
import by.it.academy.repositories.connection.ConnectionInt;
import by.it.academy.repositories.connection.MySQLConnection;
import by.it.academy.repositories.user.UserApiRepository;
import by.it.academy.repositories.user.UserRepository;
import by.it.academy.services.user.UserApiService;
import by.it.academy.services.user.UserService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/userList")
public class UserListController extends HttpServlet {

    private final ConnectionInt connection = new MySQLConnection();
    private final UserRepository<User> repository = new UserApiRepository(connection);
    private final UserService<User> service = new UserApiService(repository);

    private final static Logger log = Logger.getLogger(UserListController.class);

    private final static String USER_LIST_PAGE = "/pages/user/userList.jsp";

    public UserListController() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList = null;

        try {
            userList = service.getAllUsers();
        } catch (SQLException | ClassNotFoundException e) {
            log.info(e.getMessage());
        }

        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(USER_LIST_PAGE);
        req.setAttribute("userList", userList);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
