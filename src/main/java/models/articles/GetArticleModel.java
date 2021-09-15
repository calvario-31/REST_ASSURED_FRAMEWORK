package models.articles;

import models.Model;

public class GetArticleModel extends Model {
    private ArticleResponseModel article;

    public GetArticleModel() {
    }

    public GetArticleModel(ArticleResponseModel article) {
        this.article = article;
    }

    public ArticleResponseModel getArticle() {
        return article;
    }

    public void setArticle(ArticleResponseModel article) {
        this.article = article;
    }
}
