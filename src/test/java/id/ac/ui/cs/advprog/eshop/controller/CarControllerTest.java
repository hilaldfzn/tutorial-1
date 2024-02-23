package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {
    MockMvc mockMvc;

    @Mock
    CarService carService;

    @InjectMocks
    CarController carController;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(carController).build();
    }

    @Test
    void testCreateCarPage() throws Exception {
        mockMvc.perform(get("/car/create"))
               .andExpect(status().isOk())
               .andExpect(view().name("CreateCar"))
               .andExpect(model().attributeExists("car"));
    }

    @Test
    void testCreateCarPost() throws Exception {
        Car car = new Car();
        doReturn(car).when(carService).create(any(Car.class));

        mockMvc.perform(post("/car/create")
                .param("carName", "Test Car")
                .param("carColor", "Red")
                .param("carQuantity", "10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        verify(carService, times(1)).create(any(Car.class));
    }

    @Test
    void testCarListPage() throws Exception {
        List<Car> cars = Arrays.asList(new Car(), new Car());
        when(carService.findAll()).thenReturn(cars);

        mockMvc.perform(get("/car/list"))
               .andExpect(status().isOk())
               .andExpect(view().name("CarList"))
               .andExpect(model().attribute("cars", cars));
    }

    @Test
    void testEditCarPage() throws Exception {
        Car car = new Car();
        when(carService.findById(anyString())).thenReturn(car);

        mockMvc.perform(get("/car/edit/{carId}", "d39b484c-4699-4680-8437-50959451daa1"))
               .andExpect(status().isOk())
               .andExpect(view().name("EditCar"))
               .andExpect(model().attributeExists("car"));
    }

    @Test
    void testEditCarPost() throws Exception {
        mockMvc.perform(post("/car/edit")
                .param("carId", "d39b484c-4699-4680-8437-50959451daa1")
                .param("carName", "Updated Car")
                .param("carColor", "Blue")
                .param("carQuantity", "20"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        verify(carService, times(1)).update(anyString(), any(Car.class));
    }

    @Test
    void testDeleteCarPost() throws Exception {
        String carId = "fe937460-d896-4c5c-9d9b-90e142f39af8";

        mockMvc.perform(post("/car/delete")
                .param("carId", carId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        verify(carService, times(1)).deleteCarById(carId);
    }
}