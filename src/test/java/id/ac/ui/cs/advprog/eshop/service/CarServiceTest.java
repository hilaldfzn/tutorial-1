package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {
    @Mock
    CarRepository carRepository;

    @InjectMocks
    CarServiceImpl carServiceImpl;

    @BeforeEach
    void setup() {}

    @Test
    void testCreateCar() {
        Car car = new Car();
        car.setCarId("0f88eb4f-c225-4736-a194-b34112c1258b");
        car.setCarName("Tesla Model S");
        car.setCarColor("Red");
        car.setCarQuantity(5);

        when(carRepository.create(car)).thenReturn(car);

        Car savedCar = carServiceImpl.create(car);
        assertEquals(car.getCarId(), savedCar.getCarId());
        verify(carRepository, times(1)).create(car);
    }

    @Test
    void testFindAllCars() {
        List<Car> carList = new ArrayList<>();

        Car car1 = new Car();
        car1.setCarId("0f88eb4f-c225-4736-a194-b34112c1258b");
        car1.setCarName("Tesla Model S");
        car1.setCarColor("Red");
        car1.setCarQuantity(5);
        carList.add(car1);

        Car car2 = new Car();
        car2.setCarId("d39b484c-4699-4680-8437-50959451daa1");
        car2.setCarName("Audi Q7");
        car2.setCarColor("Blue");
        car2.setCarQuantity(3);
        carList.add(car2);

        when(carRepository.findAll()).thenReturn(carList.iterator());

        List<Car> result = carServiceImpl.findAll();

        assertEquals(carList.size(), result.size());
        for (int i = 0; i < carList.size(); i++) {
            assertEquals(carList.get(i), result.get(i));
        }

        verify(carRepository, times(1)).findAll();
    }

    @Test
    void testFindCarById() {
        Car car = new Car();
        car.setCarId("0f88eb4f-c225-4736-a194-b34112c1258b");
        car.setCarName("Tesla Model S");
        car.setCarColor("Red");
        car.setCarQuantity(5);

        when(carRepository.findById(car.getCarId())).thenReturn(car);

        Car foundCar = carServiceImpl.findById(car.getCarId());

        assertEquals(car, foundCar);
        verify(carRepository, times(1)).findById(car.getCarId());
    }

    @Test
    void testUpdateCar() {
        Car originalCar = new Car();
        originalCar.setCarId("0f88eb4f-c225-4736-a194-b34112c1258b");
        originalCar.setCarName("Tesla Model S");
        originalCar.setCarColor("Red");
        originalCar.setCarQuantity(5);

        Car updatedCar = new Car();
        updatedCar.setCarName("Tesla Model X");
        updatedCar.setCarColor("Black");
        updatedCar.setCarQuantity(10);

        lenient().when(carRepository.findById(originalCar.getCarId())).thenReturn(originalCar);
        lenient().when(carRepository.update(originalCar.getCarId(), updatedCar)).thenReturn(updatedCar);

        carServiceImpl.update(originalCar.getCarId(), updatedCar);

        verify(carRepository, times(1)).update(originalCar.getCarId(), updatedCar);
    }

    @Test
    void testDeleteCarById() {
        String carId = "0f88eb4f-c225-4736-a194-b34112c1258b";
        doNothing().when(carRepository).delete(carId);

        carServiceImpl.deleteCarById(carId);
        verify(carRepository, times(1)).delete(carId);
    }
}