package com.IAP.car_exchange.Model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "cars")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "worker_id", nullable = true, referencedColumnName = "id")
    private User workerId;

    @Getter
    @Setter
    @Id
    @NotEmpty
    @Column(name = "plate_number")
    private String plateNumber;

    @Getter
    @Setter
    @Column(name = "license_number")
    private String licenseNumber;

    @Getter
    @Setter
    @Column(name = "model")
    private String model;
    
    @Getter
    @Setter
    @Column(name = "type")
    private String type;
    
    @Getter
    @Setter
    @Column(name = "vin_number")
    private String vinNumber;

}
