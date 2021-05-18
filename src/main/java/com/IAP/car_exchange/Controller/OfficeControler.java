package com.IAP.car_exchange.Controller;

import com.IAP.car_exchange.Controller.DataHolders.CarData;
import com.IAP.car_exchange.Controller.DataHolders.OfficeData;
import com.IAP.car_exchange.Controller.DataHolders.UserData;
import com.IAP.car_exchange.Model.Car;
import com.IAP.car_exchange.Model.Office;
import com.IAP.car_exchange.repository.Querries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OfficeControler {
    @Autowired
    Querries DataAccess;

    @GetMapping("office/{id}")
    public Office getOffice(@PathVariable Long id){
        return DataAccess.getOfficeById(id);
    }

    @GetMapping("offices")
    public @ResponseBody
    Iterable<Office> getOffices(){
        return DataAccess.getAllOffices();
    }

    @PostMapping("office")
    public @ResponseBody
    ResponseEntity<String> addOffice(@RequestBody OfficeData dataHolder){
        Office office = DataAccess.addOffice(dataHolder.getId(),dataHolder.getCity(), dataHolder.getType());
        return ResponseEntity.ok(office.toString());
    }
    @PostMapping("office/{id}")
    public ResponseEntity<String> editOffice(@PathVariable("id") Long id, @RequestBody OfficeData dataHolder){
        Office office = DataAccess.updateOffice(id, dataHolder);
        return ResponseEntity.ok(office.toString());
    }
    @DeleteMapping("_office/{id}")
    public ResponseEntity<String> deleteOffice(@PathVariable("id") Long id){
        DataAccess.deleteOffice(id);
        return ResponseEntity.ok("Removed");
    }
}
