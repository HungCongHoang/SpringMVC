package com.javaweb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
//@MappedSuperclass trong Spring MVC/JPA đánh dấu một class cha để các class con kế thừa các thuộc tính nhưng không phải là một entity riêng trong cơ sở dữ liệu.
//Class cha không được ánh xạ thành bảng trong cơ sở dữ liệu.
//Các class con sẽ kế thừa các trường và ánh xạ chúng vào bảng tương ứng.
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
	
	@Column(name = "id", columnDefinition = "BIGINT")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "createddate")
	@CreatedDate
	private Date createdDate;
	
	@Column(name = "modifieddate")
	@LastModifiedDate
	private Date modifiedDate;
	
	@Column(name = "createdby", columnDefinition = "NVARCHAR(255)")
	@CreatedBy
	private String createdBy;
	
	@Column(name = "modifiedby", columnDefinition = "NVARCHAR(255)")
	@LastModifiedBy
	private String modifiedBy;

	public Long getId() {
		return id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}
	
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}
}
