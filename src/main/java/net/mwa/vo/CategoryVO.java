package net.mwa.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.mwa.common.MemberShipType;

@Data
@Entity
@Table(name="CATEGORY_DETAILS")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdDate", "lastUpdate"}, allowGetters = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryVO {

	@Id
	@GeneratedValue
	private Long id;
	
	@ApiModelProperty(name = "code", allowableValues = MemberShipType.Values.VALUES, value = MemberShipType.Values.INDEPENDENT)
	private String code;
	
	private String name;
	
	private String description;
	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdDate;
	
	@Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date lastUpdate;
	
}
