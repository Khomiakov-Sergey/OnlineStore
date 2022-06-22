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

@Log4j
@WebServlet(urlPatterns = "/product/delete")
public class DeleteProductController extends HttpServlet {
    private final Session hibernateSession = DataSource.getInstance().getSession();

    private final ProductRepository<Product> repository = new ProductApiRepository(hibernateSession);
    private final ProductService<Product> service = new ProductApiService(repository, hibernateSession);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final int id = Integer.parseInt(req.getParameter("id"));
        log.info("We are trying to delete product with id" + id + " from controller");
        service.delete(id);
        resp.sendRedirect(req.getContextPath() + "/product/productList");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}
