package com.IAP.car_exchange.Model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "offices")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Office {
    @Getter
    @Setter
    @Id
    //@Size(max = 32)
    @NotNull
    @Column(name = "id")
    //@GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    
    //@OneToMany(fetch = FetchType.LAZY)
    //@JoinColumn(name = "id", nullable = false, referencedColumnName = "office_id")
    //private User id;
    
    @Getter
    @Setter
    @Column(name = "city")
    public String city;

    @Getter
    @Setter
    @Column(name = "type")
    public String type;
}
