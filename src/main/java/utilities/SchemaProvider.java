package utilities;

public class SchemaProvider {
    private static final String userSchemaPath = "schemas/users/userSchema.json";
    private static final String tagsSchemaPath = "schemas/tags/tagsSchema.json";
    private static final String articleSchemaPath = "schemas/articles/articleSchema.json";
    private static final String allArticlesSchemaPath = "schemas/articles/allArticlesSchema.json";
    private static final String commentSchemaPath = "schemas/articles/comments/commentSchema.json";
    private static final String allCommentsSchemaPath = "schemas/articles/comments/allCommentsSchema.json";

    public static String getUserSchemaPath() {
        return userSchemaPath;
    }

    public static String getTagsSchemaPath() {
        return tagsSchemaPath;
    }

    public static String getArticleSchemaPath() {
        return articleSchemaPath;
    }

    public static String getAllArticlesSchemaPath() {
        return allArticlesSchemaPath;
    }

    public static String getCommentSchemaPath() {
        return commentSchemaPath;
    }

    public static String getAllCommentsSchemaPath() {
        return allCommentsSchemaPath;
    }
}
