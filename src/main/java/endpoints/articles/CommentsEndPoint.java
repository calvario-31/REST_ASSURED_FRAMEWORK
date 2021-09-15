package endpoints.articles;

import endpoints.EndPoint;
import models.articles.CommentResponseModel;
import models.articles.GetAllCommentsModel;
import models.articles.GetCommentModel;
import utilities.Log;
import utilities.endpointhelpers.JsonPayloadProvider;

public class CommentsEndPoint extends EndPoint {
    private final String commentPath = "articles/{articleId}/comments";
    private final String commentIdPath = "articles/{articleId}/comments/{idComment}";

    public CommentsEndPoint(String token) {
        super(token);
    }

    public GetAllCommentsModel getComments(String articleId) {
        createNewRequest();
        assignArticleId(articleId);
        Log.info("Calling get comments endpoint");
        apiCallManager(commentPath, GET);
        return getAllCommentsResponseBodyAsModel();
    }

    public CommentResponseModel createComment(String articleId, String payload) {
        createNewRequest();
        assignArticleId(articleId);
        assignBodyParameter(payload);
        Log.info("Calling post comments endpoint");
        apiCallManager(commentPath, POST);
        return getCommentResponseBodyAsModel();
    }

    public void deleteComment(String articleId, int idComment) {
        createNewRequest();
        assignArticleId(articleId);
        assignIdComment(String.valueOf(idComment));
        Log.info("Calling delete comment endpoint");
        apiCallManager(commentIdPath, DELETE);
    }

    public CommentResponseModel generateComment(String articleId) {
        return createComment(articleId,  new JsonPayloadProvider().getCommentJson());
    }

    private void assignArticleId(String articleId) {
        assignPathParameter("articleId", articleId);
    }

    private void assignIdComment(String idComment) {
        assignPathParameter("idComment", idComment);
    }

    private CommentResponseModel getCommentResponseBodyAsModel() {
        return getResponseBody().as(GetCommentModel.class).getComment();
    }

    private GetAllCommentsModel getAllCommentsResponseBodyAsModel() {
        return getResponseBody().as(GetAllCommentsModel.class);
    }
}
