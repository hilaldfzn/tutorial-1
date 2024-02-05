package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {}

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());

        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindByIdIfEmpty() {
        assertNull(productRepository.findById("e2d9c3f5-6a7b-4c8d-9a5e-1f7b8c9d0a2b"));
    }

    @Test
    void testFindByIdIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("88fa7a93-1aeb-40fd-aafb-8823e9ab178c");
        product1.setProductName("iPhone XR");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("6e4a7b88-9e47-4f23-ba81-9c2f23a9e2d4");
        product2.setProductName("iPhone 15 Pro Max");
        product2.setProductQuantity(70);
        productRepository.create(product2);

        assertNull(productRepository.findById("5e4a7b88-9e47-4f23-ba81-9c2f23a9e2d4"));
        assertNotNull(productRepository.findById(product1.getProductId()));
    }

    @Test
    void testDeleteAndFindAll() {
        Product product = new Product();
        product.setProductId("88fa7a93-1aeb-40fd-aafb-8823e9ab178c");
        product.setProductName("iPhone XR");
        product.setProductQuantity(100);
        productRepository.create(product);
        
        productRepository.delete(product);
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteAndFindById() {
        Product product = new Product();
        product.setProductId("88fa7a93-1aeb-40fd-aafb-8823e9ab178c");
        product.setProductName("iPhone XR");
        product.setProductQuantity(100);
        productRepository.create(product);
        
        productRepository.delete(product);
        assertNull(productRepository.findById(product.getProductId()));
    }

    @Test
    void testEditAndVerify() {
        Product product = new Product();
        product.setProductId("88fa7a93-1aeb-40fd-aafb-8823e9ab178c");
        product.setProductName("iPhone XR");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product editedProduct = new Product();
        editedProduct.setProductId("6e4a7b88-9e47-4f23-ba81-9c2f23a9e2d4");
        editedProduct.setProductName("iPhone 15 Pro Max");
        editedProduct.setProductQuantity(70);
        productRepository.edit(product, editedProduct);

        assertEquals("88fa7a93-1aeb-40fd-aafb-8823e9ab178c", product.getProductId());
        assertNotEquals("6e4a7b88-9e47-4f23-ba81-9c2f23a9e2d4", product.getProductId());

        assertEquals("iPhone 15 Pro Max", product.getProductName());
        assertNotEquals("iPhone XR", product.getProductName());

        assertEquals(70, product.getProductQuantity());
        assertNotEquals(100, product.getProductQuantity());
    }
}