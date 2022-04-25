package by.it.academy.controllers.product;

import by.it.academy.entities.Product;
import by.it.academy.repositories.connection.DBConnection;
import by.it.academy.repositories.connection.MySQLConnection;
import by.it.academy.repositories.product.ProductApiRepository;
import by.it.academy.repositories.product.ProductRepository;
import by.it.academy.services.product.ProductApiService;
import by.it.academy.services.product.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(urlPatterns = "/product/buy")
public class BuyProductController extends HttpServlet {
    private final DBConnection connection = new MySQLConnection();
    private final ProductRepository<Product> repository = new ProductApiRepository(connection);
    private final ProductService<Product> service = new ProductApiService(repository);

    private final static String BUY_PRODUCT_PAGE = "/pages/product/buyProductPage.jsp";
    private final static String SUCCESS_PURCHASE_PAGE = "/pages/purchase/successPurchasePage.jsp";
    private final static String FAIL_PURCHASE_PAGE = "/pages/purchase/failPurchasePage.jsp";

    public BuyProductController() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(BUY_PRODUCT_PAGE);
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = service.getProduct(id);
        req.setAttribute("product", product);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final int id = Integer.parseInt(req.getParameter("id"));
        final String name = req.getParameter("name");
        final BigDecimal price = BigDecimal.valueOf(Double.parseDouble(req.getParameter("price")));
        final int number = Integer.parseInt(req.getParameter("number"));
        final int quantity = Integer.parseInt(req.getParameter("quantity"));
        final String description = req.getParameter("description");
        final Product product = new Product(name, price, number - quantity, description);
        product.setId(id);
        req.setAttribute("quantity", quantity);
        req.setAttribute("product", product);
        if (number >= quantity) {
            service.buy(product);
            final RequestDispatcher requestDispatcher = req.getRequestDispatcher(SUCCESS_PURCHASE_PAGE);
            requestDispatcher.forward(req, resp);
        } else {
            final RequestDispatcher requestDispatcher = req.getRequestDispatcher(FAIL_PURCHASE_PAGE);
            requestDispatcher.forward(req, resp);
        }
    }
}
