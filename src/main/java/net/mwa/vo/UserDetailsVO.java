package net.mwa.vo;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.DiscriminatorOptions;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Table(name = "user_details")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="TYPE", discriminatorType=DiscriminatorType.STRING, length=20)
@DiscriminatorOptions(force=true)
@Data
@JsonIgnoreProperties(value = {"createdDate", "lastUpdate"}, allowGetters = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@EntityListeners(AuditingEntityListener.class)
public abstract class UserDetailsVO {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
//	@TableGenerator(name = "table", allocationSize = 10000)
	private Long id;
	
	
	@Column(name="FIRST_NAME")
	@NotBlank
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;

	@Column(name="MIDDLE_NAME")
	private String middleName;
	
	
	@Column(name="PLOT_NO")
	@NotBlank
	private String plotNo;
	
	@Column(name="ADDRESS1")
	private String address1;
	
	@Column(name="EMAIL_ID")
	private String emailID;
	
	@Column(name="MOBILE_NO")
	private String mobileNo;
	
	@Column(name="NO_OF_FAMILIES")
	private int noOfFamilies = 1;
	
	@Column(name="ROAD_NO")
	private String roadNo;
	
	@ApiModelProperty(name="password",example="*********",position=8)
	@Column(name="PASSWORD")
	@Transient
	private String password;
	
	@JsonIgnore
	@Column(name="IS_ACTIVE")
	private boolean active=false;
			
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleVO> roles;
	
	@OneToOne
	private CategoryVO category;
	
	@JsonIgnore
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdDate;
	
	@JsonIgnore
	@Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date lastUpdate;
	
}
