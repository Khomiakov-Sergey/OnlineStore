package by.it.academy.controllers.product;

import by.it.academy.entities.CategoryType;
import by.it.academy.entities.Order;
import by.it.academy.entities.Product;
import by.it.academy.entities.User;
import by.it.academy.repositories.connection.DataSource;
import by.it.academy.repositories.order.OrderApiRepository;
import by.it.academy.repositories.order.OrderRepository;
import by.it.academy.repositories.product.ProductApiRepository;
import by.it.academy.repositories.product.ProductRepository;
import by.it.academy.services.order.OrderApiService;
import by.it.academy.services.order.OrderService;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

import static by.it.academy.utils.Constants.*;

@Log4j
@WebServlet(urlPatterns = PRODUCT_BUY_PATH)

public class BuyProductController extends HttpServlet {

    private final Session hibernateSession = DataSource.getInstance().getSession();

    private final ProductRepository<Product> productRepository = new ProductApiRepository(hibernateSession);
    private final ProductService<Product> productService = new ProductApiService(productRepository, hibernateSession);

    private final OrderRepository<Order> orderRepository = new OrderApiRepository(hibernateSession);
    private final OrderService<Order> orderService = new OrderApiService(orderRepository, hibernateSession);


    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(PRODUCT_BUY_PAGE);
        long id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.getProduct(id);
        req.setAttribute("product", product);
        dispatcher.forward(req, resp);
    }

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final long id = Long.parseLong(req.getParameter("id"));
        final CategoryType categoryType = CategoryType.valueOf(req.getParameter("categoryType"));
        final String name = req.getParameter("name");
        final BigDecimal price = BigDecimal.valueOf(Double.parseDouble(req.getParameter("price")));
        final int number = Integer.parseInt(req.getParameter("number"));
        final int quantity = Integer.parseInt(req.getParameter("quantity"));
        final String description = req.getParameter("description");
        final Product product = new Product(categoryType, name, price, number - quantity, description);
        product.setId(id);

        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("loginedUser");
        Order order = new Order(product, user, quantity, price.multiply(BigDecimal.valueOf(quantity)));

        req.setAttribute("quantity", quantity);
        req.setAttribute("product", product);
        if (number >= quantity) {
            log.info("We are trying to buy product from controller" + product);
            productService.buy(product);
            orderService.create(order);
            final RequestDispatcher requestDispatcher = req.getRequestDispatcher(ORDER_SUCCESS_PAGE);
            requestDispatcher.forward(req, resp);
        } else {
            log.info("Quantity in stocks less than user wants to buy: " + number + " < " + quantity);
            final RequestDispatcher requestDispatcher = req.getRequestDispatcher(ORDER_FAIL_PAGE);
            requestDispatcher.forward(req, resp);
        }


    }


}
