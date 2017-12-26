package net.mwa.vo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DiscriminatorOptions;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@Entity
@Table(name = "INDEPENDENT_DETAILS")
@DiscriminatorValue("INDEPENDENT")
@DiscriminatorOptions(force=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IndependentHouseVO extends UserDetailsVO{

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "APARTMENT_ID", nullable = true)
	private ApartmentVO apartmentVO;
		
}
