package net.mwa.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "role_details")
public class RoleVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROLE_ID")
	private int id;
	
	@Column(name = "ROLE")
	private String role;

}
