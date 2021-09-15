package models.articles;

import models.Model;

public class GetCommentModel extends Model {
    private CommentResponseModel comment;

    public GetCommentModel() {
    }

    public GetCommentModel(CommentResponseModel comment) {
        this.comment = comment;
    }

    public CommentResponseModel getComment() {
        return comment;
    }

    public void setComment(CommentResponseModel comment) {
        this.comment = comment;
    }
}
