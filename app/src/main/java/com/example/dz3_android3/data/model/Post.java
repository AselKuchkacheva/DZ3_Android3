package com.example.dz3_android3.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Post implements Serializable {

    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("group")
    @Expose
    private Integer group;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("user")
    @Expose
    private Integer user;

    public Post(String content, Integer group, String title, Integer user) {
        this.content = content;
        this.group = group;
        this.title = title;
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "content='" + content + '\'' +
                ", group=" + group +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", user=" + user +
                '}';
    }
}
