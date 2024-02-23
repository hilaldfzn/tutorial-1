package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarRepositoryTest {
    @InjectMocks
    CarRepository carRepository;

    @BeforeEach
    void setUp() {}

    @Test
    void testCreateAndFind() {
        Car car = new Car();
        car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setCarName("Bugatti Veyron");
        car.setCarColor("White");
        car.setCarQuantity(100);
        carRepository.create(car);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();
        assertEquals(car.getCarId(), savedCar.getCarId());
        assertEquals(car.getCarName(), savedCar.getCarName());
        assertEquals(car.getCarColor(), savedCar.getCarColor());
        assertEquals(car.getCarQuantity(), savedCar.getCarQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Car> carIterator = carRepository.findAll();
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneCar() {
        Car car1 = new Car();
        car1.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car1.setCarName("Bugatti Veyron");
        car1.setCarColor("White");
        car1.setCarQuantity(100);
        carRepository.create(car1);

        Car car2 = new Car();
        car2.setCarId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        car2.setCarName("Audi");
        car2.setCarColor("Black");
        car2.setCarQuantity(50);
        carRepository.create(car2);

        Iterator<Car> CarIterator = carRepository.findAll();
        assertTrue(CarIterator.hasNext());

        Car savedCar = CarIterator.next();
        assertEquals(car1.getCarId(), savedCar.getCarId());

        savedCar = CarIterator.next();
        assertEquals(car2.getCarId(), savedCar.getCarId());
        assertFalse(CarIterator.hasNext());
    }

    @Test
    void testFindByIdIfEmpty() {
        assertNull(carRepository.findById("e2d9c3f5-6a7b-4c8d-9a5e-1f7b8c9d0a2b"));
    }

    @Test
    void testFindByIdIfMoreThanOneCar() {
        Car car1 = new Car();
        car1.setCarId("88fa7a93-1aeb-40fd-aafb-8823e9ab178c");
        car1.setCarName("Lamborghini");
        car1.setCarColor("Red");
        car1.setCarQuantity(100);
        carRepository.create(car1);

        Car car2 = new Car();
        car2.setCarId("6e4a7b88-9e47-4f23-ba81-9c2f23a9e2d4");
        car2.setCarName("Ford Mustang");
        car2.setCarColor("Blue");
        car2.setCarQuantity(70);
        carRepository.create(car2);

        assertNull(carRepository.findById("5e4a7b88-9e47-4f23-ba81-9c2f23a9e2d4"));
        assertNotNull(carRepository.findById(car1.getCarId()));
    }

    @Test
    void testDeleteAndFindAll() {
        Car car = new Car();
        car.setCarId("88fa7a93-1aeb-40fd-aafb-8823e9ab178c");
        car.setCarName("Lamborghini");
        car.setCarColor("Red");
        car.setCarQuantity(100);
        carRepository.create(car);
        
        carRepository.delete("88fa7a93-1aeb-40fd-aafb-8823e9ab178c");
        Iterator<Car> CarIterator = carRepository.findAll();
        assertFalse(CarIterator.hasNext());
    }

    @Test
    void testDeleteAndFindById() {
        Car car = new Car();
        car.setCarId("88fa7a93-1aeb-40fd-aafb-8823e9ab178c");
        car.setCarName("Lamborghini");
        car.setCarColor("Red");
        car.setCarQuantity(100);
        carRepository.create(car);
        
        carRepository.delete("88fa7a93-1aeb-40fd-aafb-8823e9ab178c");
        assertNull(carRepository.findById(car.getCarId()));
    }

    @Test
    void testEditAndVerify() {
        Car car = new Car();
        car.setCarId("88fa7a93-1aeb-40fd-aafb-8823e9ab178c");
        car.setCarName("Lamborghini");
        car.setCarColor("Red");
        car.setCarQuantity(100);
        carRepository.create(car);

        Car editedCar = new Car();
        editedCar.setCarId("6e4a7b88-9e47-4f23-ba81-9c2f23a9e2d4");
        editedCar.setCarName("Ford Mustang");
        editedCar.setCarColor("Blue");
        editedCar.setCarQuantity(70);
        carRepository.update("88fa7a93-1aeb-40fd-aafb-8823e9ab178c", editedCar);

        assertEquals("88fa7a93-1aeb-40fd-aafb-8823e9ab178c", car.getCarId());
        assertNotEquals("6e4a7b88-9e47-4f23-ba81-9c2f23a9e2d4", car.getCarId());

        assertEquals("Ford Mustang", car.getCarName());
        assertNotEquals("Lamborghini", car.getCarName());

        assertEquals("Blue", car.getCarColor());
        assertNotEquals("Red", car.getCarColor());

        assertEquals(70, car.getCarQuantity());
        assertNotEquals(100, car.getCarQuantity());
    }

    @Test
    void testUpdateNonExistentCar() {
        Car updatedCar = new Car();
        updatedCar.setCarId("non-existent-id");
        updatedCar.setCarName("Non-existent Car");
        updatedCar.setCarColor("Black");
        updatedCar.setCarQuantity(0);

        Car result = carRepository.update("non-existent-id", updatedCar);
        assertNull(result);
    }
}