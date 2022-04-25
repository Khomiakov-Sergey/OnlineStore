package by.it.academy.controllers.product;

import by.it.academy.entities.Product;
import by.it.academy.repositories.connection.DBConnection;
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
import java.util.List;

@WebServlet(urlPatterns = "/product/productList")
public class ProductListController extends HttpServlet {
    private final DBConnection connection = new MySQLConnection();
    private final ProductRepository<Product> productDAO = new ProductApiRepository(connection);
    private final ProductService<Product> service = new ProductApiService(productDAO);

    private final static String PRODUCT_LIST_PAGE = "/pages/product/productList.jsp";

    private final static Logger log = Logger.getLogger(ProductListController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> productList = service.getAllProducts();
        log.info("Show productList " + productList);
        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(PRODUCT_LIST_PAGE);
        req.setAttribute("productList", productList);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
