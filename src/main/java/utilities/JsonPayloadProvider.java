package utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Model;
import models.articles.ArticleModel;
import models.articles.comments.CommentModel;
import models.users.CredentialsUserModel;

public class JsonPayloadProvider {
    public String getUserJson() {
        return getJson(new CredentialsUserModel(), "user").toString();
    }

    public String getArticleJson() {
        return getJson(new ArticleModel(), "article").toString();
    }

    public String getCommentJson() {
        return getJson(new CommentModel(), "comment").toString();
    }

    private ObjectNode getJson(Model model, String father) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.putPOJO(father, model);
        return rootNode;
    }
}
