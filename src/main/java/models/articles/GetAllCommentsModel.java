package models.articles;

import models.Model;

import java.util.List;

public class GetAllCommentsModel extends Model {
    private List<CommentResponseModel> comments;

    public GetAllCommentsModel() {
    }

    public GetAllCommentsModel(List<CommentResponseModel> comments) {
        this.comments = comments;
    }

    public List<CommentResponseModel> getComments() {
        return comments;
    }

    public void setComments(List<CommentResponseModel> comments) {
        this.comments = comments;
    }
}
