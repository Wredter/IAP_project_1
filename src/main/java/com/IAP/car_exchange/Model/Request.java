package com.IAP.car_exchange.Model;

import lombok.*;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "requests")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Request {
	
	@Getter
	@Setter
	@Id
	@NotNull
	@Column(name="request_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long requestId;	

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @Cascade( { org.hibernate.annotations.CascadeType.ALL } )
    @JoinColumn(name = "requestor_id", nullable = false, referencedColumnName = "id")
    private User requestorId;
	
	@Getter
	@Setter
	@NotNull
	@Column(name="branch_id")
	private Long branchId;
	
	@Getter
	@Setter
	@NotNull
	@Column(name="car_model")
	private String carModel;
	
	@Getter
	@Setter
	@NotNull
	@Column(name="vehicle_preffered")
	private String vehiclePreffered;
	
	@Getter
	@Setter
	@Temporal(TemporalType.DATE)
	@NotNull
	@Column(name="request_date")
	private Date requestDate;
	
	@Getter
	@Setter
	@Null
	@Column(name="request_status")
	private String requestStatus;
	
	@Getter
	@Setter
	@Null
	@Column(name="approved_by")
	private String approvedBy;
	
	@Getter
	@Setter
	@Temporal(TemporalType.DATE)
	@Null
	@Column(name="approved_date")
	private Date approvedDate;
	

}
