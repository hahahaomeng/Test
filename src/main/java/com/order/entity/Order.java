package com.order.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "orders", catalog = "ordermanager")
public class Order implements java.io.Serializable {
	private Long orderId;
	private User user;
	private Product product;
	private Date paydate;
	private Integer price;
	private String notice;
	private String state;
	private Date godate;
	private Integer gonumber;
	public Order(){
		
	}
	
	public Order(User user, Product product, Date paydate, Integer price,
			String notice, String state, Date godate, Integer gonumber) {
		this.user = user;
		this.product = product;
		this.paydate = paydate;
		this.price = price;
		this.notice = notice;
		this.state = state;
		this.godate = godate;
		this.gonumber = gonumber;
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "orderid", unique = true, nullable = false)
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "productid")
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Column(name = "paydate", nullable = false)
	public Date getPaydate() {
		return paydate;
	}
	public void setPaydate(Date paydate) {
		this.paydate = paydate;
	}
	@Column(name = "price", nullable = false)
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	@Column(name="notice",nullable=false,length=45)
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	@Column(name="state",nullable=false,length=2)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Column(name="godate",nullable=false)
	public Date getGodate() {
		return godate;
	}
	public void setGodate(Date godate) {
		this.godate = godate;
	}
	@Column(name="gonumber",nullable=false)
	public Integer getGonumber() {
		return gonumber;
	}
	public void setGonumber(Integer gonumber) {
		this.gonumber = gonumber;
	}
	
}