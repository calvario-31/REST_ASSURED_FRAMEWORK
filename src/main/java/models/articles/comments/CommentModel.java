package models.articles.comments;

import com.github.javafaker.Faker;
import models.Model;

public class CommentModel extends Model {
    private String body;

    public CommentModel() {
        Faker faker = new Faker();
        body = faker.lorem().paragraph();
    }

    public CommentModel(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
