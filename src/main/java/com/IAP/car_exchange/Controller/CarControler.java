package com.IAP.car_exchange.Controller;

import com.IAP.car_exchange.Controller.DataHolders.CarData;
import com.IAP.car_exchange.Controller.DataHolders.UserData;
import com.IAP.car_exchange.Model.Car;
import com.IAP.car_exchange.Model.User;
import com.IAP.car_exchange.repository.Querries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class CarControler {
    @Autowired
    Querries DataAccess;

    @GetMapping("car/{plate_number}")
    public Car getCar(@PathVariable String plate_number){
        return DataAccess.getCarByPlate(plate_number);
    }

    @GetMapping("cars")
    public @ResponseBody
    Iterable<Car> getCars(){
        return DataAccess.getAllCars();
    }

    @PostMapping("car")
    public @ResponseBody
    ResponseEntity<String> addCAr(@RequestBody CarData dataHolder){
        Car car = DataAccess.addCar(
        		dataHolder.getPlateNumber(),
                dataHolder.getLicenseNumber(),
                dataHolder.getModel(),
                dataHolder.getWorkerId(),
                dataHolder.getType(),
                dataHolder.getVinNumber()
        		);
        return ResponseEntity.ok(car.toString());
    }
    @PutMapping("car/{plate_number}")
    public ResponseEntity<String> editCar(@PathVariable("plate") String plate, @RequestBody CarData dataHolder){
        Car car = DataAccess.updateCar(plate, dataHolder);
        return ResponseEntity.ok(car.toString());
    }
    @DeleteMapping("_car/{plate_number}")
    public ResponseEntity<String> deleteUser(@PathVariable("plate") String plate){
        DataAccess.deleteCar(plate);
        return ResponseEntity.ok("Removed");
    }
}
