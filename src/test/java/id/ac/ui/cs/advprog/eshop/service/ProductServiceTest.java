package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productServiceImpl;

    @BeforeEach
    void setup() {}

    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setProductId("0f88eb4f-c225-4736-a194-b34112c1258b");
        product.setProductName("iPhone 13 Pro");
        product.setProductQuantity(100);

        when(productRepository.create(product)).thenReturn(product);

        Product savedProduct = productServiceImpl.create(product);
        assertEquals(product.getProductId(), savedProduct.getProductId());
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAllProduct() {
        List<Product> productList = new ArrayList<>();

        Product product = new Product();
        product.setProductId("0f88eb4f-c225-4736-a194-b34112c1258b");
        product.setProductName("iPhone 13 Pro");
        product.setProductQuantity(100);
        productList.add(product);

        Product product2 = new Product();
        product.setProductId("d39b484c-4699-4680-8437-50959451daa1");
        product.setProductName("Samsung Galaxy S24 Ultra");
        product.setProductQuantity(68);
        productList.add(product2);

        Iterator<Product> iterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> result = productServiceImpl.findAll();

        assertEquals(productList.size(), result.size());
        for (int i = 0; i < productList.size(); i++) {
            assertEquals(productList.get(i), result.get(i));
        }

        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindProductById() {
        Product product = new Product();
        product.setProductId("0f88eb4f-c225-4736-a194-b34112c1258b");
        product.setProductName("iPhone 13 Pro");
        product.setProductQuantity(100);
        when(productRepository.findById(product.getProductId())).thenReturn(product);

        Product foundProduct = productServiceImpl.findById(product.getProductId());

        assertEquals(product, foundProduct);
        verify(productRepository, times(1)).findById(product.getProductId());
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setProductId("0f88eb4f-c225-4736-a194-b34112c1258b");
        product.setProductName("iPhone 13 Pro");
        product.setProductQuantity(100);

        Product editedProduct = new Product();
        editedProduct.setProductId("0f88eb4f-c225-4736-a194-b34112c1258b");
        editedProduct.setProductName("iPhone 15 Pro Max");
        editedProduct.setProductQuantity(50);

        when(productRepository.findById(editedProduct.getProductId())).thenReturn(product);
        when(productRepository.edit(product, editedProduct)).thenReturn(editedProduct);

        Product resultProduct = productServiceImpl.edit(editedProduct);
        assertEquals(editedProduct.getProductName(), resultProduct.getProductName());
        verify(productRepository, times(1)).edit(product, editedProduct);
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductId("0f88eb4f-c225-4736-a194-b34112c1258b");
        product.setProductName("iPhone 13 Pro");
        product.setProductQuantity(100);

        when(productRepository.delete(product)).thenReturn(product);

        Product deletedProduct = productServiceImpl.delete(product);
        assertEquals(product, deletedProduct);
        verify(productRepository, times(1)).delete(product);
    }
}