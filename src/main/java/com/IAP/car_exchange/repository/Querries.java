package com.IAP.car_exchange.repository;

import com.IAP.car_exchange.Controller.DataHolders.CarData;
import com.IAP.car_exchange.Controller.DataHolders.OfficeData;
import com.IAP.car_exchange.Controller.DataHolders.UserData;
import com.IAP.car_exchange.Model.Car;
import com.IAP.car_exchange.Model.Office;
import com.IAP.car_exchange.Model.User;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Repository
@Data
public class Querries {
    final UserRepository userRepository;
    final OfficeRepository officeRepository;
    final CarRepository carRepository;

    public Querries(UserRepository userRepository, OfficeRepository officeRepository, CarRepository carRepository){
        this.userRepository = userRepository;
        this.officeRepository = officeRepository;
        this.carRepository = carRepository;

    }
/////////////////////////////////////////USERS//////////////////////////////////////////////////////////////////////////
    public User getUserById(long id) throws IllegalArgumentException {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new IllegalArgumentException("There are no users with id=" + id + "!"));
    }

    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User addUser(String firstName, String middleName, String sureName, String pesel, char gender, Date birthDate, String role, Long officeId){
       Office office = officeRepository.findById(officeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid office Id: " + officeId));
       User user = User.builder().firstName(firstName)
                .middleName(middleName)
                .sureName(sureName)
                .pesel(pesel)
                .gender(gender)
                .birthDate(birthDate)
                .role(role)
                .officeId(office)
                .build();
       userRepository.save(user);
       return user;
    }
    public User updateUser(Long userId, UserData userData){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + userId));
        Office office = officeRepository.findById(userData.getOfficeId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid office Id: " + userData.getOfficeId()));
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-mm-dd").parse(userData.getBirthDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setFirstName(userData.getFirstName());
        user.setMiddleName(userData.getMiddleName());
        user.setSureName(userData.getSurname());
        user.setPesel(userData.getPesel());
        user.setGender(userData.getGender());
        user.setBirthDate(date);
        user.setRole(userData.getRole());
        user.setOfficeId(office);
        userRepository.save(user);
        return user;
    }
    public void deleteUser(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + userId));
        userRepository.deleteById(user.getId());
    }
///////////////////////////////////////////////////////////CARS/////////////////////////////////////////////////////////
    public Car getCarByPlate(String plateNumber){
        Car car = carRepository.findById(plateNumber)
                .orElseThrow(() -> new IllegalArgumentException("There are no sars with plate=" + plateNumber + "!"));
        return car;
    }
    public Iterable<Car> getAllCars(){
        return carRepository.findAll();
    }
    public Car addCar(String plateNumber, String licenseNumber, String model, Long workerId){
        User user = userRepository.findById(workerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid worker Id: " + workerId));
        Car car = Car.builder()
                .plateNumber(plateNumber)
                .licenseNumber(licenseNumber)
                .model(model)
                .worker_id(user)
                .build();
        carRepository.save(car);
        return car;
    }
    public void deleteCar(String plateNumber){
        Car car = carRepository.findById(plateNumber)
                .orElseThrow(() -> new IllegalArgumentException("There are no sars with plate=" + plateNumber + "!"));
        carRepository.deleteById(car.getPlateNumber());
    }
    public Car updateCar(String plateNumber, CarData carData){
        Car car = carRepository.findById(plateNumber)
                .orElseThrow(() -> new IllegalArgumentException("There are no sars with plate=" + plateNumber + "!"));
        User user = userRepository.findById(carData.getWorker_id())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + carData.getWorker_id()));
        car.setLicenseNumber(carData.getLicenseNumber());
        car.setModel(carData.getModel());
        car.setWorker_id(user);
        return car;
    }
    //////////////////////////////////////////////////////OFFICES///////////////////////////////////////////////////////
    public Office getOfficeById(Long id){
        Office office = officeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("There are no offices with id=" + id + "!"));
        return office;
    }
    public Iterable<Office> getAllOffices(){
        return officeRepository.findAll();
    }
    public Office addOffice(String city, String type){
        Office office = Office.builder()
                .city(city)
                .type(type)
                .build();
        officeRepository.save(office);
        return office;
    }
    public void deleteOffice(Long id){
        Office office = officeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("There are no offices with id=" + id + "!"));
        officeRepository.deleteById(office.getId());
    }
    public Office updateOffice(Long id, OfficeData officeData){
        Office office = officeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("There are no offices with id=" + id + "!"));
        office.setCity(officeData.getCity());
        office.setType(officeData.getType());
        officeRepository.save(office);
        return office;
    }
}
