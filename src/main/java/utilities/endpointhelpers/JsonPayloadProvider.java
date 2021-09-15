package utilities.endpointhelpers;

import models.articles.ArticlePayloadModel;
import models.articles.CommentPayloadModel;
import models.users.CredentialsUserModel;

public class JsonPayloadProvider {
    public CredentialsUserModel getCredentialsUserJson() {
        return new CredentialsUserModel();
    }

    public ArticlePayloadModel getArticleJson() {
        return new ArticlePayloadModel();
    }

    public CommentPayloadModel getCommentJson() {
        return new CommentPayloadModel();
    }
}
