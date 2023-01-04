package com.project.blog.Entities;

import java.sql.Timestamp;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Getter
@Setter
public class Comment {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email")
	private String email;
	
	@Column(name="comment")
	private String comment;
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="postid")
	private Post post;
	
	@Override
	public String toString() {
		return "Comment [id=" + id + ", name=" + name + ", email=" + email + ", comment=" + comment + ", post=" + post
				+ ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
	}
	@CreationTimestamp
	@Column(name="createdat", updatable = false)
	private Timestamp created_at;
	
	@UpdateTimestamp
	@Column(name="updatedat")
	private Timestamp updated_at;


}
