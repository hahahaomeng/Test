package com.order.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@SuppressWarnings("serial")
@Entity
@Table(name ="application", catalog = "ordermanager")
public class Application implements java.io.Serializable {
	private Long appId;
	private Order order;
	private String appState;
	private String appType;
	private Date appDate;
	private String appNotice;
	
	
	public Application() {
		
	}
	
	
	public Application(Order order, String appState, String appType,
			Date appDate, String appNotice) {
		this.order = order;
		this.appState = appState;
		this.appType = appType;
		this.appDate = appDate;
		this.appNotice = appNotice;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "appid", unique = true, nullable = false)
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orderid")
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}

	@Column(name = "appstate", nullable = false, length =2)
	public String getAppState() {
		return appState;
	}
	public void setAppState(String appState) {
		this.appState = appState;
	}
	
	@Column(name = "apptype", nullable = false, length =2)
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	@Column(name = "appdate", nullable = false)
	public Date getAppDate() {
		return appDate;
	}
	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}
	@Column(name = "appnotice", nullable = false, length =120)
	public String getAppNotice() {
		return appNotice;
	}
	public void setAppNotice(String appNotice) {
		this.appNotice = appNotice;
	}
	
}