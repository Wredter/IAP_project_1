package com.IAP.car_exchange.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.IAP.car_exchange.Controller.DataHolders.RoleData;
import com.IAP.car_exchange.Model.Role;
import com.IAP.car_exchange.repository.Querries;

@RestController
public class RoleController {
	@Autowired
	Querries DataAccess;
	
	@PostMapping("role")
    public @ResponseBody
    ResponseEntity<String> addUserRole(@RequestBody RoleData dataHolder){
        Role role = DataAccess.addUserRole(dataHolder.getId(), dataHolder.getTitle(),dataHolder.getDescription(),dataHolder.getPrevilege());
        return ResponseEntity.ok(role.toString());
    }
	
	@GetMapping("role/{id}")
	public Role getRole(@PathVariable Long id) {
		return DataAccess.getRoleById(id);
	}
	
	@GetMapping("roles")
	public @ResponseBody
    Iterable<Role> getRoles(){
        return DataAccess.getAllRoles();
    }
	
	// it doesn't work as expected, due to relationship, to be modified
    @DeleteMapping("_role/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable("id") Long id){
        DataAccess.deleteRole(id);
        return ResponseEntity.ok("Removed");
    }
    
    //@PostMapping("editrole/{id}")
    @PutMapping("role/{id}")
    public ResponseEntity<String> editRole(@PathVariable("id") Long id, @RequestBody RoleData dataHolder){
        Role role= DataAccess.updateRole(id, dataHolder);
        return ResponseEntity.ok(role.toString());
    }

}
