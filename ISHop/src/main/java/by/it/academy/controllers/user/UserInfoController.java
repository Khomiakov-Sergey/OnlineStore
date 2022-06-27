package by.it.academy.controllers.user;

import by.it.academy.entities.Order;
import by.it.academy.entities.User;
import by.it.academy.repositories.connection.DataSource;
import by.it.academy.repositories.order.OrderApiRepository;
import by.it.academy.repositories.order.OrderRepository;
import by.it.academy.services.order.OrderApiService;
import by.it.academy.services.order.OrderService;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.it.academy.utils.Constants.*;

/**
 * This controller class is responsible for displaying user information and orders, which he did.
 * It is an intermediate layer between view and service.
 */
@Log4j
@WebServlet(urlPatterns = USER_INFO_PATH)
public class UserInfoController extends HttpServlet {

    private final Session hibernateSession = DataSource.getInstance().getSession();


    private final OrderRepository<Order> repository = new OrderApiRepository(hibernateSession);
    private final OrderService<Order> service = new OrderApiService(repository, hibernateSession);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User loginedUser = (User) session.getAttribute("loginedUser");
        if (loginedUser == null) {
            resp.sendRedirect(req.getContextPath() + USER_LOGIN_PATH);
            return;
        }
        req.setAttribute("user", loginedUser);
        log.info("We are trying to get orderList from controller");
        List<Order> orderList = service.getAllOrdersByUserId(loginedUser.getId());
        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(USER_INFO_PAGE);
        req.setAttribute("orderList", orderList);
        requestDispatcher.forward(req, resp);
    }

    /**
     * This method redirect user to the doGet method.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
