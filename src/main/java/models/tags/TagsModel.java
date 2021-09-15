package models.tags;

import models.Model;

import java.util.List;

public class TagsModel extends Model {
    private List<String> tags;

    public TagsModel() {
    }

    public TagsModel(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
