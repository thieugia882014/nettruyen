
package com.example.nettruyen.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Category implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("create_at")
    @Expose
    private Object createAt;
    @SerializedName("update_at")
    @Expose
    private Object updateAt;
    @SerializedName("image")
    @Expose
    private Object image;
    @SerializedName("movies")
    @Expose
    private Object movies;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Object createAt) {
        this.createAt = createAt;
    }

    public Object getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Object updateAt) {
        this.updateAt = updateAt;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public Object getMovies() {
        return movies;
    }

    public void setMovies(Object movies) {
        this.movies = movies;
    }

}
