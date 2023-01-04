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
public class Post {
	
	@Id
	@Column(name="id",updatable = false,insertable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="excerpt")
	private String excerpt;
	
	@Column(name="content", columnDefinition="text")
	private String content;
	
	@Column(name="author")
	private String author;
	
	@Column(name="published_at")
	@CreationTimestamp
	private Timestamp published_at;
	
	@Column(name="is_published")
	private boolean is_published;
	
	@Column(name="created_at" ,updatable = false)
	@CreationTimestamp
	private Timestamp created_at;
	
	@Column(name="updated_at")
	@UpdateTimestamp
	private Timestamp updated_at;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy ="post", fetch = FetchType.EAGER)
	private List<Comment> comments= new ArrayList<>();
	public List<Comment> getComments() {
		return comments;
	}

	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(
			name="post_tags",
			joinColumns=@JoinColumn(name="post_id"),
			inverseJoinColumns=@JoinColumn(name="tag_id")
			)
	private List<Tag> tags= new ArrayList<>();

	@Override
	public String toString() {
		return "Post{" +
				"id=" + id +
				", title='" + title + '\'' +
				", excerpt='" + excerpt + '\'' +
				", content='" + content + '\'' +
				", author='" + author + '\'' +
				", published_at=" + published_at +
				", is_published=" + is_published +
				", created_at=" + created_at +
				", updated_at=" + updated_at +
				", comments=" + comments +
				", tags=" + tags +
				'}';
	}
}
