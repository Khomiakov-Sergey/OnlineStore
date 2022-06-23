package by.it.academy.controllers.user;

import by.it.academy.entities.User;
import lombok.extern.log4j.Log4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.it.academy.utils.Constants.LOGIN_PAGE;
import static by.it.academy.utils.Constants.USER_LOGOUT_PATH;

@Log4j
@WebServlet(urlPatterns = USER_LOGOUT_PATH)
public class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User loginedUser = (User) session.getAttribute("loginedUser");
        log.info(loginedUser.getLogin() + " log out");
        session.invalidate();
        String errorString = "You are successfully logged out";
        req.setAttribute("errorString", errorString);
        req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
