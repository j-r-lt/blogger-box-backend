package com.dauphine.blogger.models;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

@Entity
@Table(name="post")
public class Post {
    @Id
    @GeneratedValue
    @Column(name="id")
    private UUID id;
    @Column(name="title")
    private String title;
    @Column(name="content")
    private String content;
    @Column(name="created_date")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date createdDate;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    public Post(UUID id, String title, String content, Date createdDate, Category category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.category = category;
    }
    public Post(String title, String content, Date createdDate, Category category) {
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.category = category;
    }

    public Post() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated_date() {
        return createdDate;
    }

    public void setCreated_date(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


}
