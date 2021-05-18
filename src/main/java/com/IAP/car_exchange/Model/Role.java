package com.IAP.car_exchange.Model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
	@Getter
	@Setter
	@Id
	@NotNull
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
    //@OneToOne(cascade = CascadeType.ALL,orphanRemoval=true)
    //@JoinColumn(name = "id", referencedColumnName = "id")
    //private User id;
	
	
	@Getter
	@Setter
	@NotNull
	@Column(name="title")
	public String title;
	
	@Getter
	@Setter
	@NotNull
	@Column(name="description")
	public String description;
	
	@Getter
	@Setter
	@NotNull
	@Column(name="previleges")
	public String previlege;

}
