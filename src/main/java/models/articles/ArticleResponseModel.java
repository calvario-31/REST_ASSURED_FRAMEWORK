package models.articles;

import models.Model;

import java.util.Date;
import java.util.List;

public class ArticleResponseModel extends Model {
    private String title;
    private String slug;
    private String body;
    private Date createdAt;
    private Date updatedAt;
    private List<String> tagList;
    private String description;
    private AuthorModel author;
    private boolean favorited;
    private int favoritesCount;

    public ArticleResponseModel() {
    }

    public ArticleResponseModel(String title, String slug, String body, Date createdAt,
                                Date updatedAt, List<String> tagList, String description,
                                AuthorModel author, boolean favorited, int favoritesCount) {
        this.title = title;
        this.slug = slug;
        this.body = body;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.tagList = tagList;
        this.description = description;
        this.author = author;
        this.favorited = favorited;
        this.favoritesCount = favoritesCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AuthorModel getAuthor() {
        return author;
    }

    public void setAuthor(AuthorModel author) {
        this.author = author;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public int getFavoritesCount() {
        return favoritesCount;
    }

    public void setFavoritesCount(int favoritesCount) {
        this.favoritesCount = favoritesCount;
    }
}
