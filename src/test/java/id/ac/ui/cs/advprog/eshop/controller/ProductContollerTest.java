package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    MockMvc mockMvc;

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void testCreateProductPage() throws Exception {
        mockMvc.perform(get("/product/create"))
               .andExpect(status().isOk())
               .andExpect(view().name("CreateProduct"))
               .andExpect(model().attributeExists("product"));
    }

    @Test
    void testCreateProductPost() throws Exception {
        Product product = new Product();
        doReturn(product).when(productService).create(any(Product.class));

        mockMvc.perform(post("/product/create")
                .param("productName", "Test Product")
                .param("productQuantity", "10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        verify(productService, times(1)).create(any(Product.class));
    }

    @Test
    void testProductListPage() throws Exception {
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productService.findAll()).thenReturn(products);

        mockMvc.perform(get("/product/list"))
               .andExpect(status().isOk())
               .andExpect(view().name("ProductList"))
               .andExpect(model().attribute("products", products));
    }

    @Test
    void testEditProductPage() throws Exception {
        Product product = new Product();
        when(productService.findById(anyString())).thenReturn(product);

        mockMvc.perform(get("/product/edit/{productId}", "d39b484c-4699-4680-8437-50959451daa1"))
               .andExpect(status().isOk())
               .andExpect(view().name("EditProduct"))
               .andExpect(model().attributeExists("product"));
    }

    @Test
    void testEditProductPost() throws Exception {
        mockMvc.perform(post("/product/edit/{productId}", "d39b484c-4699-4680-8437-50959451daa1")
                .param("productName", "Updated Product")
                .param("productQuantity", "20"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("../list"));

        verify(productService, times(1)).edit(any(Product.class));
    }

    @Test
    void testEditProductPostWithMismatchId() throws Exception {
        String productId = "1";
        String editProductId = "2";

        Product product = new Product();
        product.setProductId(productId);
        product.setProductName("Test Product");
        product.setProductQuantity(10);

        mockMvc.perform(post("/product/edit/{productId}", editProductId)
                .param("productId", productId)
                .param("productName", product.getProductName())
                .param("productQuantity", String.valueOf(product.getProductQuantity())))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("../list"));

        verify(productService, never()).edit(product);
    }

    @Test
    void testEditNonExistentProduct() throws Exception {
        String nonExistentProductId = "non-existent-id";
        when(productService.findById(nonExistentProductId)).thenReturn(null);

        mockMvc.perform(get("/product/edit/" + nonExistentProductId))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("../list"));

        verify(productService, times(1)).findById(nonExistentProductId);
    }

    @Test
    void testDeleteProductPost() throws Exception {
        String productId = "fe937460-d896-4c5c-9d9b-90e142f39af8";
        Product product = new Product();
        product.setProductId(productId);
        when(productService.findById(productId)).thenReturn(product);

        mockMvc.perform(get("/product/delete/{productId}", productId))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("../list"));

        verify(productService, times(1)).delete(product);
    }

    @Test
    void testDeleteNonExistentProduct() throws Exception {
        String nonExistentProductId = "non-existent-id";
        when(productService.findById(nonExistentProductId)).thenReturn(null);

        mockMvc.perform(get("/product/delete/" + nonExistentProductId))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("../list"));

        verify(productService, never()).delete(any(Product.class));
    }
}