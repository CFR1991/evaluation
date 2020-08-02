package com.evaluation.service.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ua_blacklist")
public class UaBlacklist {
	@Id
	@Column(name = "ua", length = 255, nullable = false)
	private String ua;

	public String getUa() {
		return ua;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}

}
