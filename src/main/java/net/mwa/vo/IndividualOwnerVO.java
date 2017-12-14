package net.mwa.vo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DiscriminatorOptions;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@Entity
@Table(name = "INDIVIDUAL_OWNER_DETAILS")
@DiscriminatorValue("INDEPENDENT")
@DiscriminatorOptions(force=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IndividualOwnerVO extends MemberDetailsVO{

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "APARTMENT_ID", nullable = false)
	private ApartmentVO apartmentVO;
		
}
