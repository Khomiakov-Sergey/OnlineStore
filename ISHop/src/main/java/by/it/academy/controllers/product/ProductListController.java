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

@Log4j
@WebServlet(urlPatterns = "/product/productList")
public class ProductListController extends HttpServlet {
    private final Session hibernateSession = DataSource.getInstance().getSession();

    private final ProductRepository<Product> repository = new ProductApiRepository(hibernateSession);
    private final ProductService<Product> service = new ProductApiService(repository, hibernateSession);

    private final static String PRODUCT_LIST_PAGE = "/pages/product/productList.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("We are trying to get productList from controller");

        List<Product> productList = service.getAllProducts();
        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(PRODUCT_LIST_PAGE);
        req.setAttribute("productList", productList);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
