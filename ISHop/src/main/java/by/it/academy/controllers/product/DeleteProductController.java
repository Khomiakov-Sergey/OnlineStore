package by.it.academy.controllers.product;

import by.it.academy.entities.Product;
import by.it.academy.repositories.connection.DataSource;
import by.it.academy.repositories.product.ProductApiRepository;
import by.it.academy.repositories.product.ProductRepository;
import by.it.academy.services.product.ProductApiService;
import by.it.academy.services.product.ProductService;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.it.academy.utils.Constants.PRODUCT_DELETE_PATH;

/**
 * This controller class is responsible for delete the product (only for role ADMIN).
 * It is an intermediate layer between view and service.
 */
@Log4j
@WebServlet(urlPatterns = PRODUCT_DELETE_PATH)
public class DeleteProductController extends HttpServlet {
    private final Session hibernateSession = DataSource.getInstance().getSession();

    private final ProductRepository<Product> repository = new ProductApiRepository(hibernateSession);
    private final ProductService<Product> service = new ProductApiService(repository, hibernateSession);

    /**
     * This method gets the product id from the request and sends it to the service layer for deleting the product.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final long id = Long.parseLong(req.getParameter("id"));
        log.info("We are trying to delete product with id" + id + " from controller");
        service.delete(id);
        resp.sendRedirect(req.getContextPath() + "/product/productList");
    }

    /**
     * This method redirect user to the doGet method.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}
