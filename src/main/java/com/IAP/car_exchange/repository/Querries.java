package com.IAP.car_exchange.repository;

import com.IAP.car_exchange.Controller.DataHolders.CarData;
import com.IAP.car_exchange.Controller.DataHolders.OfficeData;
import com.IAP.car_exchange.Controller.DataHolders.Response;
import com.IAP.car_exchange.Controller.DataHolders.RoleData;
import com.IAP.car_exchange.Controller.DataHolders.UserData;
import com.IAP.car_exchange.Model.Car;
import com.IAP.car_exchange.Model.Office;
import com.IAP.car_exchange.Model.Request;
import com.IAP.car_exchange.Model.Role;
import com.IAP.car_exchange.Model.User;
import com.google.common.collect.Iterables;

import lombok.Data;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

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
    final RoleRepository roleRepository;
    final RequestRepository requestRepository;

    public Querries(UserRepository userRepository, OfficeRepository officeRepository, CarRepository carRepository,RoleRepository roleRepository,RequestRepository requestRepository){
        this.userRepository = userRepository;
        this.officeRepository = officeRepository;
        this.carRepository = carRepository;
        this.roleRepository = roleRepository;
        this.requestRepository = requestRepository;
    }
/////////////////////////////////////////USERS//////////////////////////////////////////////////////////////////////////
    public User getUserById(long id) throws IllegalArgumentException {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new IllegalArgumentException("There are no users with id=" + id + "!"));
    }
    

    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User addUser(Long id,String firstName, String middleName, String surName, String pesel, char gender, Date birthDate, Long roleId, Long officeId){
       Office office = officeRepository.findById(officeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid office Id: " + officeId));
       Role role = roleRepository.findById(roleId)
               .orElseThrow(() -> new IllegalArgumentException("Invalid role Id: " + roleId));

       User user = User.builder()
    		   	.id(id)
    		   	.firstName(firstName)
                .middleName(middleName)
                .surName(surName)
                .pesel(pesel)
                .gender(gender)
                .birthDate(birthDate)
                .roleId(role)
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
        Role role = roleRepository.findById(userData.getRoleId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid role Id: " + userData.getRoleId()));
        
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-mm-dd").parse(userData.getBirthDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setFirstName(userData.getFirstName());
        user.setMiddleName(userData.getMiddleName());
        user.setSurName(userData.getSurname());
        user.setPesel(userData.getPesel());
        user.setGender(userData.getGender());
        user.setBirthDate(date);
        user.setRoleId(role);
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
        
    
    public Car addCar(String plateNumber, String licenseNumber, String model, Long workerId,String type, String vinNumber,Boolean assigned){
        User user = userRepository.findById(workerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid worker Id: " + workerId));
        Car car = Car.builder()
        		.workerId(user)
                .plateNumber(plateNumber)
                .licenseNumber(licenseNumber)
                .model(model)
                .type(type)
                .vinNumber(vinNumber)
                .assigned(assigned)
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
        User user = userRepository.findById(carData.getWorkerId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + carData.getWorkerId()));
        car.setLicenseNumber(carData.getLicenseNumber());
        car.setModel(carData.getModel());
        car.setWorkerId(user);
        car.setType(carData.getType());
        car.setVinNumber(carData.getVinNumber());
        carRepository.save(car);
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
    public Office addOffice(Long id,String city, String type){
        Office office = Office.builder()
        		.id(id)
                .city(city)
                .type(type)
                .build();
        officeRepository.save(office);
        return office;
    }
    public void deleteOffice(Long id){
    	//User user = userRepository.fi
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
    
    //////////////////////////////////////////////////////ROLES///////////////////////////////////////////////////////
    
    public Role addUserRole(Long id,String title,String description,String previlege) {
    	Role role = Role.builder()
    			.id(id)
    			.title(title)
    			.description(description)
    			.previlege(previlege)
    			.build();
    	roleRepository.save(role);
    	return role;
    }
    
    public Role getRoleById(Long id){
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("There are no roles for a user with id=" + id + "!"));
        return role;
    }
    
    public Iterable<Role> getAllRoles(){
        return roleRepository.findAll();
    }
    
    public void deleteRole(Long id){
    	//User user = userRepository.fi
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("There are no role with id=" + id + "!"));
        roleRepository.delete(role);
    }
    
    public Role updateRole(Long id, RoleData roleData){
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("There are no role with id=" + id + "!"));
        role.setTitle(roleData.getTitle());
        role.setDescription(roleData.getDescription());
        role.setPrevilege(roleData.getPrevilege());
        roleRepository.save(role);
        return role;
    }
    
    //////////////////////////////////////////////////////REQUESTS///////////////////////////////////////////////////////
    
    public Request addRequest(Long requestorId,Long branchId,String carModel,String vehiclePreffered,Date requestDate, Long branchRequestId) {
        User user = userRepository.findById(requestorId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid requestor Id: " + requestorId));
        Office office = officeRepository.findById(branchId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid branch Id: " + branchId));
    	Request request = Request.builder()
    			.requestorId(user)
    			.branchId(branchId)
    			.carModel(carModel)
    			.vehiclePreffered(vehiclePreffered)
    			.requestDate(requestDate)
    			.branchRequestId(branchRequestId)
    			.build();
    	requestRepository.save(request);
    	return request;

    }
    
    public Iterable<Request> getAllRequests(){
    	return requestRepository.findAll();
    }
    
    public Request getRequestById(Long id){
        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("There is no request with id=" + id + "!"));
        return request;
    }
    
    public void deleteRequest(Long id){
    	//User user = userRepository.fi
        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("There is no request with id=" + id + "!"));
        requestRepository.delete(request);
    }
    
    
    /////////////////////////ASSIGN CAR/////////////////////////////////////////////////////////////////////
    // Find Car By (model,type,assigned?,super_admin_id)
    public Iterable<Car> getCarByModelType(String model, String type) {	
    	return carRepository.findByModelType(model, type);
    }
    
    //TODO Find unassigned requests by searching searchByStatus, return the request ID for the admin to select from
    //get unassigned requests
    public Iterable<Request> getPendingRequests(){
    	return requestRepository.pendingRequests();
    }
    //TODO Give approval status as assigned/rejected
    public Response assign(Long requestId) {
    	Response response = new Response();
		Request request = requestRepository.findById(requestId)
				.orElseThrow(() -> new IllegalArgumentException("There is no request with id=" + requestId + "!"));
		
		Iterable<Car> cars = carRepository.findByModelType(request.getCarModel(),request.getVehiclePreffered());
		
    	//System.out.println("passed here = "+Iterables.size(cars));
    	
    	if (Iterables.isEmpty(cars) != true) {
    		Car car = Iterables.get(cars, 0);
    		car.setAssigned(true);
    		carRepository.save(car);
    		
    		// Change the request status
    		request.setApprovedDate(new Date());
    		request.setRequestStatus("approved");
    		requestRepository.save(request);
    		
    		// Pack the Response/car detail
    		response.setWorkerId(request.getRequestorId().getId());
    		response.setPlateNumber(car.getPlateNumber());
    		response.setLicenseNumber(car.getLicenseNumber());
    		response.setModel(car.getModel());
    		response.setType(car.getType());
    		response.setVinNumber(car.getVinNumber());
    		
    	}
    	
    	else {
    		request.setApprovedDate(new Date());
    		request.setRequestStatus("rejected");
    		requestRepository.save(request);
    	}
    	
    	//// Pack the Response message and send back to the requestor
    	
		response.setRequestId(request.getBranchRequestId());
		response.setRequestStatus(request.getRequestStatus());
		response.setApprovedDate(request.getApprovedDate());
		
		//Forward this response to the respective branch
		//RestTemplate rest = new RestTemplate();
		
    	return response;
    	
    }
    
    //TODO Send the car detail to the requestor --> write the car table
    
}
