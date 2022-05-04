package by.it.academy.controllers.product;

import by.it.academy.entities.Product;
import by.it.academy.repositories.connection.DBConnection;
import by.it.academy.repositories.connection.MyDBConnection;
import by.it.academy.repositories.product.ProductApiRepository;
import by.it.academy.repositories.product.ProductRepository;
import by.it.academy.services.product.ProductApiService;
import by.it.academy.services.product.ProductService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/product/delete")
public class DeleteProductController extends HttpServlet {
    private final DBConnection connection = new MyDBConnection();
    private final ProductRepository<Product> productDAO = new ProductApiRepository(connection);
    private final ProductService<Product> service = new ProductApiService(productDAO);

    private final static Logger log = Logger.getLogger(DeleteProductController.class);

    public DeleteProductController() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        log.info("We are trying to delete product with id" + id + " from controller");
        service.delete(id);
        resp.sendRedirect(req.getContextPath() + "/product/productList");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}
