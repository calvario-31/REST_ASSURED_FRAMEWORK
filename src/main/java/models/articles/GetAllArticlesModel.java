package models.articles;

import java.util.List;

public class GetAllArticlesModel {
    private List<ArticleResponseModel> articles;
    public int articlesCount;

    public GetAllArticlesModel() {
    }

    public GetAllArticlesModel(List<ArticleResponseModel> articles, int articlesCount) {
        this.articles = articles;
        this.articlesCount = articlesCount;
    }

    public List<ArticleResponseModel> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleResponseModel> articles) {
        this.articles = articles;
    }

    public int getArticlesCount() {
        return articlesCount;
    }

    public void setArticlesCount(int articlesCount) {
        this.articlesCount = articlesCount;
    }
}
