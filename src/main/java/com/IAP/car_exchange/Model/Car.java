package com.IAP.car_exchange.Model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

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
    @JoinColumn(name = "worker_id", nullable = false, referencedColumnName = "id")
    private User worker_id;

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

}
