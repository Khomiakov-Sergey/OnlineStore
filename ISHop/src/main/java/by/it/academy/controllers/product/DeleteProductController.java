package by.it.academy.controllers.product;

import by.it.academy.entities.Product;
import by.it.academy.repositories.connection.ConnectionInt;
import by.it.academy.repositories.connection.MySQLConnection;
import by.it.academy.repositories.product.ProductApiRepository;
import by.it.academy.repositories.product.ProductRepository;
import by.it.academy.services.product.ProductApiService;
import by.it.academy.services.product.ProductService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/product/delete")
public class DeleteProductController extends HttpServlet {
    private final ConnectionInt connection = new MySQLConnection();
    private final ProductRepository<Product> productDAO = new ProductApiRepository(connection);
    private final ProductService<Product> service = new ProductApiService(productDAO);

    private final static String DELETE_PRODUCT_ERROR_PAGE = "/pages/errors/deleteProductErrorPage.jsp";
    private final static Logger log = Logger.getLogger(DeleteProductController.class);

    public DeleteProductController() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String errorLine = null;
        try {
            service.delete(id);
        } catch (SQLException | ClassNotFoundException e) {
            errorLine = e.getMessage();
            log.info(errorLine);
        }
        if (errorLine != null) {
            req.setAttribute("errorLine", errorLine);
            RequestDispatcher dispatcher = req.getRequestDispatcher(DELETE_PRODUCT_ERROR_PAGE);
            dispatcher.forward(req, resp);
        } else resp.sendRedirect(req.getContextPath() + "/productList");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
