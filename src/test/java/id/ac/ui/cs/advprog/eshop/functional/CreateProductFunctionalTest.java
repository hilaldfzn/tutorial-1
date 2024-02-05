package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d/product/list", testBaseUrl, serverPort);
    }

    @Test
    void createProduct_isCorrect(ChromeDriver driver) throws Exception {
        driver.get(baseUrl);

        WebElement createProductButton = driver.findElement(By.id("create"));
        createProductButton.click();

        WebElement productNameInput = driver.findElement(By.id("nameInput"));
        productNameInput.clear();
        productNameInput.sendKeys("iPhone 15 Pro Max");

        WebElement productQuantityInput = driver.findElement(By.id("quantityInput"));
        productQuantityInput.clear();
        productQuantityInput.sendKeys("100");

        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();
        
        List<WebElement> productRows = driver.findElements(By.cssSelector("table tbody tr"));
        WebElement lastProductRow = productRows.get(productRows.size() - 1);

        String actualProductName = lastProductRow.findElement(By.cssSelector("td:nth-child(1)")).getText();
        assertEquals("iPhone 15 Pro Max", actualProductName);

        String actualProductQuantity = lastProductRow.findElement(By.cssSelector("td:nth-child(2)")).getText();
        assertEquals("100", actualProductQuantity);
    }

    @Test
    void createMoreThanOneProduct_isCorrect(ChromeDriver driver) throws Exception {
        String[][] products = {
            {"iPhone 15 Pro Max", "100"},
            {"Galaxy S30 Ultra", "150"},
            {"Pixel 8", "80"}
        };

        for (String[] product : products) {
            driver.get(baseUrl);

            WebElement createProductButton = driver.findElement(By.id("create"));
            createProductButton.click();

            WebElement productNameInput = driver.findElement(By.id("nameInput"));
            productNameInput.clear();
            productNameInput.sendKeys(product[0]);

            WebElement productQuantityInput = driver.findElement(By.id("quantityInput"));
            productQuantityInput.clear();
            productQuantityInput.sendKeys(product[1]);

            WebElement submitButton = driver.findElement(By.id("submit"));
            submitButton.click();
        }

        List<WebElement> productRows = driver.findElements(By.cssSelector("table tbody tr"));
        int startIndex = productRows.size() - products.length;

        for (int i = 0; i < products.length; i++) {
            WebElement productRow = productRows.get(startIndex + i);
            
            String actualProductName = productRow.findElement(By.cssSelector("td:nth-child(1)")).getText();
            assertEquals(products[i][0], actualProductName);

            String actualProductQuantity = productRow.findElement(By.cssSelector("td:nth-child(2)")).getText();
            assertEquals(products[i][1], actualProductQuantity);
        }
    }
}