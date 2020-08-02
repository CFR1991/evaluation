package com.evaluation.service.db.model;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ip_blacklist")
public class IpBlacklist {
	@Id
	@Column(name = "ip", nullable = false)
	private BigInteger ip;

	public BigInteger getIp() {
		return ip;
	}

	public void setIp(BigInteger ip) {
		this.ip = ip;
	}

}
