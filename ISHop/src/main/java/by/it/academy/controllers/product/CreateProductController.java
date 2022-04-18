package by.it.academy.controllers.product;

import by.it.academy.entities.Product;
import by.it.academy.repositories.connection.ConnectionInt;
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

@WebServlet(urlPatterns = "/product/create")
public class CreateProductController extends HttpServlet {
    private final ConnectionInt connection = new MySQLConnection();
    private final ProductRepository<Product> repository = new ProductApiRepository(connection);
    private final ProductService<Product> service = new ProductApiService(repository);

    private final static String CREATE_PRODUCT_PAGE = "/pages/product/createProductPage.jsp";
    private final static String PRODUCT_LIST_PATH = "/productList";


    public CreateProductController() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(CREATE_PRODUCT_PAGE);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String name = req.getParameter("name");
        final double price = Double.parseDouble(req.getParameter("price"));
        final int number = Integer.parseInt(req.getParameter("number"));
        final String description = req.getParameter("description");
        final Product product = new Product(name, price, number, description);
        service.create(product);
        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(PRODUCT_LIST_PATH);
        requestDispatcher.forward(req, resp);
    }
}
