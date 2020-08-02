package com.evaluation.service.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ip_blacklist")
public class IpBlacklist {
	@Id
	@Column(name = "ip", nullable = false)
	private Long ip;

	public Long getIp() {
		return ip;
	}

	public void setIp(Long ip) {
		this.ip = ip;
	}

}
