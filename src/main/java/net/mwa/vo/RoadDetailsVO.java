package net.mwa.vo;

import java.io.Serializable;
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

import lombok.Data;

@Entity
@Data
@Table(name="road_details")
@JsonIgnoreProperties(value = {"createdDate", "lastUpdate"}, allowGetters = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@EntityListeners(AuditingEntityListener.class)
public class RoadDetailsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8196799724344881987L;

	@Id
	@GeneratedValue
	@Column(name="road_id")
	private Long roadId;
	
	@Column(name="category")
	private String category;
	
	@Column(name="road_no")
	private String roadNo;
	
	@Column(name="dist_in_meters")
	private double distInMeters;
	
	@Column(name="road_width_in_feets")
	private double roadWidthInFeets;
	
	@Column(name="priority")
	private int priority;
	
	@Column(name="current_road_type")
	private String currentRoadType;
	
	@Column(name="proposed_road_type")
	private String proposedRoadType;
	
	@Column(name="current_status")
	private String currentStatus;
	
	
	@Column(name="created_date",nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdDate;
	
	@Column(name="last_update",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date lastUpdate;

	@Override
	public String toString() {
		return "RoadDetailsVO [roadId=" + roadId + ", category=" + category + ", distInMeters=" + distInMeters
				+ ", roadWidthInFeets=" + roadWidthInFeets + ", priority=" + priority + "]";
	}
	
	

}
