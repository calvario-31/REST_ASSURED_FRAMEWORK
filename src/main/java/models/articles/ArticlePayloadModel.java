package models.articles;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.javafaker.Faker;
import models.Model;

@JsonTypeName("article")
@JsonTypeInfo(include= JsonTypeInfo.As.WRAPPER_OBJECT,use= JsonTypeInfo.Id.NAME)
public class ArticlePayloadModel extends Model {
    private String title;
    private String description;
    private String body;

    public ArticlePayloadModel() {
        Faker faker = new Faker();
        title = faker.lorem().sentence(2);
        description = faker.lorem().sentence(5);
        body = faker.lorem().paragraph();
    }

    public ArticlePayloadModel(String title, String description, String body) {
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
