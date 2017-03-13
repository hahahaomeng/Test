package com.order.entity;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "product", catalog = "ordermanager")
public class Product implements java.io.Serializable{
	private Long productId;
	private String productName;
	private Integer price;
	private String proDescribe;
	private String imageUrl;
	private String proState;
	private String proPlace;
	private String goPlace;
	private String proDetail;
	private String hotelDetail;
	public Product(){
		
	}
	public Product(String productName, Integer price,
			String proDescribe, String imageUrl, String proState,
			String proPlace, String goPlace, String proDetail,
			String hotelDetail) {
		this.productName = productName;
		this.price = price;
		this.proDescribe = proDescribe;
		this.imageUrl = imageUrl;
		this.proState = proState;
		this.proPlace = proPlace;
		this.goPlace = goPlace;
		this.proDetail = proDetail;
		this.hotelDetail = hotelDetail;
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "productid", unique = true, nullable = false)
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	@Column(name = "productname", nullable = false, length = 45)
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	@Column(name = "price", nullable = false)
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	@Column(name = "prodescribe", nullable = false, length = 1024)
	public String getProDescribe() {
		return proDescribe;
	}
	public void setProDescribe(String proDescribe) {
		this.proDescribe = proDescribe;
	}
	
	@Column(name = "imageurl", nullable = false)
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	@Column(name="prostate",nullable=false,length=2)
	public String getProState() {
		return proState;
	}
	public void setProState(String proState) {
		this.proState = proState;
	}
	@Column(name="proplace",nullable=false,length=45)
	public String getProPlace() {
		return proPlace;
	}
	public void setProPlace(String proPlace) {
		this.proPlace = proPlace;
	}
	@Column(name="goplace",nullable=false,length=45)
	public String getGoPlace() {
		return goPlace;
	}
	public void setGoPlace(String goPlace) {
		this.goPlace = goPlace;
	}
	@Column(name="prodetail",nullable=false,length=1024)
	public String getProDetail() {
		return proDetail;
	}
	public void setProDetail(String proDetail) {
		this.proDetail = proDetail;
	}
	@Column(name="hoteldetail",nullable=false,length=45)
	public String getHotelDetail() {
		return hotelDetail;
	}
	public void setHotelDetail(String hotelDetail) {
		this.hotelDetail = hotelDetail;
	}
	
}
