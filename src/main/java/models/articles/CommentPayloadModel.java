package models.articles;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.javafaker.Faker;
import models.Model;

@JsonTypeName("comment")
@JsonTypeInfo(include= JsonTypeInfo.As.WRAPPER_OBJECT,use= JsonTypeInfo.Id.NAME)
public class CommentPayloadModel extends Model {
    private String body;

    public CommentPayloadModel() {
        Faker faker = new Faker();
        body = faker.lorem().paragraph();
    }

    public CommentPayloadModel(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
