package models.articles;

import com.github.javafaker.Faker;
import models.Model;

public class ArticleModel extends Model {
    private String title;
    private String description;
    private String body;

    public ArticleModel() {
        Faker faker = new Faker();
        title = faker.lorem().sentence(2);
        description = faker.lorem().sentence(5);
        body = faker.lorem().paragraph();
    }

    public ArticleModel(String title, String description, String body) {
        this.title = title;
        this.description = description;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
