package com.jctiru.lnshop.api.io.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "light_novel")
public class LightNovelEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "light_novel_id", nullable = false)
	private String lightNovelId;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "description", nullable = false, columnDefinition = "varchar(2000)")
	private String description;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinTable(name = "light_novels_genres", joinColumns = @JoinColumn(name = "light_novel_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
	private Set<GenreEntity> genres;

	@Column(name = "price", nullable = false)
	private BigDecimal price;

	@Column(name = "quantity", nullable = false)
	private long quantity;

	@Column(name = "sold", nullable = false)
	private long sold = 0;

	@Column(name = "image_url")
	private String imageUrl;

	@CreationTimestamp
	@Column(name = "create_date_time", nullable = false)
	private LocalDateTime createDateTime;

	@UpdateTimestamp
	@Column(name = "update_date_time", nullable = false)
	private LocalDateTime updateDateTime;

}
