package com.IAP.car_exchange.Controller.DataHolders;

import lombok.Data;

@Data
public class UserData {
	Long id;
    String firstName;
    String middleName;
    String surname;
    String pesel;
    char gender;
    String birthDate;
    Long roleId;
    Long officeId;
}
