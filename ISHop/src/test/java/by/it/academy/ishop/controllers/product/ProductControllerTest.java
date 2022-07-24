package by.it.academy.ishop.controllers.product;

import by.it.academy.ishop.configurations.jwt.JwtProvider;
import by.it.academy.ishop.dtos.CategoryDto;
import by.it.academy.ishop.dtos.ProductDto;
import by.it.academy.ishop.entities.product.CategoryType;
import by.it.academy.ishop.exceptions.EntityByIdNotFoundException;
import by.it.academy.ishop.exceptions.ProductCreateException;
import by.it.academy.ishop.services.product.ProductService;
import by.it.academy.ishop.services.user.JwtUserDetailsService;
import by.it.academy.ishop.services.user.UserService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtUserDetailsService jwtUserDetailsService;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtProvider jwtProvider;

    @Autowired
    private Gson gson;

    @MockBean
    private ProductService productService;

    @Test
    @WithMockUser
    @DisplayName("Controller test of finding all products, when request from user is valid and controller takes valid respond from service")
    void checkResponseFor_GetProducts_IfDbHasSomeProducts() throws Exception {
        CategoryDto categoryDto = CategoryDto.builder()
                .categoryType(CategoryType.IPHONE)
                .build();

        ProductDto iPhone12 = ProductDto.builder()
                .id(1L)
                .model("12 Pro Max")
                .category(categoryDto)
                .price(BigDecimal.valueOf(1222.99))
                .number(75L)
                .description("Phone")
                .build();

        ProductDto iPhone13 = ProductDto.builder()
                .id(2L)
                .model("13 Pro Max")
                .category(categoryDto)
                .price(BigDecimal.valueOf(1422.99))
                .number(55L)
                .description("Phone")
                .build();

        List<ProductDto> products = List.of(iPhone12, iPhone13);

        Mockito.when(productService.findProductsByCategory(categoryDto)).thenReturn(products);

        mockMvc.perform(MockMvcRequestBuilders.get("/products/category")
                        .content(gson.toJson(categoryDto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(iPhone12.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(iPhone13.getId().intValue())))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(productService, Mockito.times(1)).findProductsByCategory(categoryDto);

    }

    @Test
    @WithMockUser
    @DisplayName("Controller test of finding product by id, when request from user is valid and controller takes valid respond from service")
    void checkResponseFor_GetProduct_IfDbHasThisProduct() throws Exception {
        Long id = 1L;

        ProductDto productDto = ProductDto.builder()
                .id(id)
                .model("12 Pro Max")
                .category(CategoryDto.builder()
                        .categoryType(CategoryType.IPHONE)
                        .build())
                .price(BigDecimal.valueOf(1222.99))
                .number(75L)
                .description("Phone")
                .build();

        Mockito.when(productService.findProduct(id)).thenReturn(productDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", id)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(productService, Mockito.times(1)).findProduct(id);
    }

    @Test
    @WithMockUser
    @DisplayName("Controller test of finding product by id, when request from user is not valid")
    void checkResponseFor_GetProduct_IfDbHasNotThisProduct() throws Exception {
        Long id = 1L;


        Mockito.when(productService.findProduct(id)).thenThrow(new EntityByIdNotFoundException(id));

        mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", id)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof EntityByIdNotFoundException))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(productService, Mockito.times(1)).findProduct(id);

    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    @DisplayName("Controller test of creating product, when request from user with role ADMIN is valid")
    void checkResponseFor_CreateProduct_IfRequestValid() throws Exception {
        Long id = 1L;

        ProductDto productDto = ProductDto.builder()
                .model("12 Pro Max")
                .category(CategoryDto.builder()
                        .categoryType(CategoryType.IPHONE)
                        .build())
                .price(BigDecimal.valueOf(1222.99))
                .number(75L)
                .description("Phone")
                .build();

        Mockito.when(productService.createProduct(productDto)).thenReturn(id);

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/products")
                        .content(gson.toJson(productDto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(productService, Mockito.times(1)).createProduct(productDto);

    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    @DisplayName("Controller test of creating product, when request from user with role ADMIN is not valid")
    void checkResponseFor_CreateProduct_IfRequestIsNotValid() throws Exception {

        ProductDto productDto = ProductDto.builder()
                .model("12 Pro Max")
                .category(CategoryDto.builder()
                        .categoryType(CategoryType.IPHONE)
                        .build())
                .price(BigDecimal.valueOf(1222.99))
                .number(75L)
                .description("Phone")
                .build();

        Mockito.when(productService.createProduct(productDto)).thenThrow(new ProductCreateException());

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/products")
                        .content(gson.toJson(productDto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ProductCreateException))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(productService, Mockito.times(1)).createProduct(productDto);

    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    @DisplayName("Controller test of updating product, when request from user with role ADMIN is valid")
    void checkResponseFor_UpdateProduct_IfRequestValid() throws Exception {
        Long id = 1L;

        ProductDto productDto = ProductDto.builder()
                .model("12 Pro Max")
                .category(CategoryDto.builder()
                        .categoryType(CategoryType.IPHONE)
                        .build())
                .price(BigDecimal.valueOf(1222.99))
                .number(75L)
                .description("Phone")
                .build();

        Mockito.when(productService.updateProduct(id, productDto)).thenReturn(id);

        mockMvc.perform(MockMvcRequestBuilders.put("/admin/products/{id}", id)
                        .content(gson.toJson(productDto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(productService, Mockito.times(1)).updateProduct(id, productDto);
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    @DisplayName("Controller test of updating product, when request from user with role ADMIN is not valid")
    void checkResponseFor_UpdateProduct_IfRequestIsNotValid() throws Exception {
        Long id = -1L;

        ProductDto productDto = ProductDto.builder()
                .model("12 Pro Max")
                .category(CategoryDto.builder()
                        .categoryType(CategoryType.IPHONE)
                        .build())
                .price(BigDecimal.valueOf(1222.99))
                .number(75L)
                .description("Phone")
                .build();

        Mockito.when(productService.updateProduct(id, productDto)).thenThrow(new EntityByIdNotFoundException(id));
        ;

        mockMvc.perform(MockMvcRequestBuilders.put("/admin/products/{id}", id)
                        .content(gson.toJson(productDto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof EntityByIdNotFoundException))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(productService, Mockito.times(1)).updateProduct(id, productDto);

    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    @DisplayName("Controller test of deleting product, when request from user with role ADMIN is  valid")
    void checkResponseFor_DeleteProduct_IfRequestIsValid() throws Exception {
        Long id = 3L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/admin/products/{id}", id)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(productService, Mockito.times(1)).deleteProduct(id);

    }


}
