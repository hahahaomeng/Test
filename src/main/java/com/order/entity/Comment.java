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

@Entity
@Table(name = "comment", catalog = "ordermanager")
public class Comment implements java.io.Serializable {
	private Long commentId;
	private Order order;
	private String content;
	private String commentUrl;
	private Date commentDate;
	private String commentState;
	public Comment() {
		
	}
	public Comment(Order order, String content, String commentUrl,
			Date commentDate, String commentState) {
		super();
		this.order = order;
		this.content = content;
		this.commentUrl = commentUrl;
		this.commentDate = commentDate;
		this.commentState = commentState;
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "commentId", unique = true, nullable = false)
	public Long getCommentId() {
		return commentId;
	}
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orderid")
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	@Column(name = "content", nullable = false, length = 45)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name="commenturl",nullable=false,length=45)
	public String getCommentUrl() {
		return commentUrl;
	}
	public void setCommentUrl(String commentUrl) {
		this.commentUrl = commentUrl;
	}
	@Column(name="commentdate",nullable=false)
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	@Column(name="commentState",nullable=false,length=2)
	public String getCommentState() {
		return commentState;
	}
	public void setCommentState(String commentState) {
		this.commentState = commentState;
	}
	
	
}