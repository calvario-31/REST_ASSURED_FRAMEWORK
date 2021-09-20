package utilities.endpointhelpers;

public class SchemaProvider {
    private static final String userSchemaPath = "schemas/users/userSchema.json";
    private static final String updateUserSchemaPath = "schemas/users/updateUserSchema.json";
    private static final String tagsSchemaPath = "schemas/tags/tagsSchema.json";
    private static final String articleSchemaPath = "schemas/articles/articleSchema.json";
    private static final String commentSchemaPath = "schemas/articles/comments/commentSchema.json";
    private static final String allCommentsSchemaPath = "schemas/articles/comments/allCommentsSchema.json";
    private static final String profileSchemaPath = "schemas/profile/profileSchema.json";

    public static String getUserSchemaPath() {
        return userSchemaPath;
    }

    public static String getUpdateUserSchemaPath() {
        return updateUserSchemaPath;
    }

    public static String getTagsSchemaPath() {
        return tagsSchemaPath;
    }

    public static String getArticleSchemaPath() {
        return articleSchemaPath;
    }

    public static String getCommentSchemaPath() {
        return commentSchemaPath;
    }

    public static String getAllCommentsSchemaPath() {
        return allCommentsSchemaPath;
    }

    public static String getProfileSchemaPath() {
        return profileSchemaPath;
    }
}
