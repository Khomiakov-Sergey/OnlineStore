package by.it.academy.controllers.user;

import by.it.academy.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/userInfo")
public class UserInfoController extends HttpServlet {

    private final static String USER_PAGE = "/pages/user/userInfo.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User loginedUser = (User) session.getAttribute("loginedUser");
        if (loginedUser == null) {
            resp.sendRedirect(req.getContextPath() + "/user/login");
            return;
        }
        req.setAttribute("user", loginedUser);
        req.getRequestDispatcher(USER_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
