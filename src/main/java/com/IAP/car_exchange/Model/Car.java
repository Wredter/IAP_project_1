package com.IAP.car_exchange.Model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "cars")
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    //@Cascade( { org.hibernate.annotations.CascadeType.ALL } )
    @JoinColumn(name = "worker_id", nullable = false, referencedColumnName = "id")
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
    
    @Getter
    @Setter
    @Column(name = "assigned")
    private Boolean assigned;

}
