package com.project.blog.Entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@Setter
public class Tag {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@CreationTimestamp
	@Column(name="created_at", updatable = false)
	private Timestamp created_at;
	
	@UpdateTimestamp
	@Column(name="updated_at")
	private Timestamp updated_at;

	@ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(
			name="post_tags",
			joinColumns=@JoinColumn(name="tag_id"),
			inverseJoinColumns=@JoinColumn(name="post_id")
			)
	private List<Post> posts = new ArrayList<>();

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}


}
