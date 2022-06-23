package by.it.academy.controllers.product;

import by.it.academy.entities.CategoryType;
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
import java.math.BigDecimal;

import static by.it.academy.utils.Constants.*;

@Log4j
@WebServlet(urlPatterns = PRODUCT_EDIT_PATH)
public class EditProductController extends HttpServlet {
    private final Session hibernateSession = DataSource.getInstance().getSession();

    private final ProductRepository<Product> repository = new ProductApiRepository(hibernateSession);
    private final ProductService<Product> service = new ProductApiService(repository, hibernateSession);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(PRODUCT_EDIT_PAGE);
        final Long id = Long.parseLong(req.getParameter("id"));
        log.info("We are trying to get product with id" + id + " from controller");
        Product product = service.getProduct(id);
        req.setAttribute("product", product);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Product product = getProduct(req);
        log.info("We are trying to update product " + product + " from controller");
        service.update(product);
        req.setAttribute("product", product);
        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(PRODUCT_LIST_PATH);
        requestDispatcher.forward(req, resp);
    }

    private Product getProduct(HttpServletRequest req) {
        final long id = Long.parseLong(req.getParameter("id"));
        final CategoryType categoryType = CategoryType.valueOf(req.getParameter("categoryType"));
        final String name = req.getParameter("name");
        final BigDecimal price = BigDecimal.valueOf(Double.parseDouble(req.getParameter("price")));
        final int number = Integer.parseInt(req.getParameter("number"));
        final String description = req.getParameter("description");
        final Product product = new Product(categoryType, name, price, number, description);
        product.setId(id);
        return product;
    }
}
