package utilities.endpointhelpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.javafaker.Faker;

public class JsonPayloadProvider {
    public String getCredentialsUserJson() {
        ObjectNode credentialsJson = new ObjectMapper().createObjectNode();

        Faker faker = new Faker();
        String username = faker.lorem().characters(10, 19);
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(10, 12);

        credentialsJson.put("username", username);
        credentialsJson.put("email", email);
        credentialsJson.put("password", password);

        ObjectNode credentials = new ObjectMapper().createObjectNode();
        credentials.set("user", credentialsJson);
        return credentials.toString();
    }

    public String getCredentialsUpdateUserJson() {
        ObjectNode credentialsJson = new ObjectMapper().createObjectNode();

        Faker faker = new Faker();
        String username = faker.name().username();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(8, 12);
        String bio = faker.lorem().paragraph();
        String image = faker.internet().image();

        credentialsJson.put("username", username);
        credentialsJson.put("email", email);
        credentialsJson.put("password", password);
        credentialsJson.put("bio", bio);
        credentialsJson.put("image", image);

        ObjectNode credentials = new ObjectMapper().createObjectNode();
        credentials.set("user", credentialsJson);
        return credentials.toString();
    }

    public String getArticleJson() {
        ObjectNode articleJson = new ObjectMapper().createObjectNode();

        Faker faker = new Faker();
        String title = faker.lorem().sentence(2);
        String description = faker.lorem().sentence(5);
        String body = faker.lorem().paragraph();

        articleJson.put("title", title);
        articleJson.put("description", description);
        articleJson.put("body", body);

        ObjectNode article = new ObjectMapper().createObjectNode();
        article.set("article", articleJson);

        return article.toString();
    }

    public String getCommentJson() {
        ObjectNode commentJson = new ObjectMapper().createObjectNode();

        Faker faker = new Faker();
        String body = faker.lorem().paragraph();

        commentJson.put("body", body);

        ObjectNode comment = new ObjectMapper().createObjectNode();
        comment.set("comment", commentJson);
        return comment.toString();
    }
}
