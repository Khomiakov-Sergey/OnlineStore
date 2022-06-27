package by.it.academy.controllers.product;

import by.it.academy.entities.Product;
import by.it.academy.repositories.connection.DataSource;
import by.it.academy.repositories.product.ProductApiRepository;
import by.it.academy.repositories.product.ProductRepository;
import by.it.academy.services.product.ProductApiService;
import by.it.academy.services.product.ProductService;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.it.academy.utils.Constants.PRODUCT_LIST_PAGE;
import static by.it.academy.utils.Constants.PRODUCT_LIST_PATH;

/**
 * This controller class is responsible for display a list of products.
 * It is an intermediate layer between view and service.
 */
@Log4j
@WebServlet(urlPatterns = PRODUCT_LIST_PATH)
public class ProductListController extends HttpServlet {
    private final Session hibernateSession = DataSource.getInstance().getSession();

    private final ProductRepository<Product> repository = new ProductApiRepository(hibernateSession);
    private final ProductService<Product> service = new ProductApiService(repository, hibernateSession);

    /**
     * This method gets the product list using by service layer and sends it to the product list page with add
     * list to the request.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("We are trying to get productList from controller");

        List<Product> productList = service.getAllProducts();
        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(PRODUCT_LIST_PAGE);
        req.setAttribute("productList", productList);
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
