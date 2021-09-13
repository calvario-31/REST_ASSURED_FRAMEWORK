package utilities;

public class SchemaReader {
    private static final String userSchemaPath = "schemas/users/userSchema.json";

    public static String getUserSchema() {
        return userSchemaPath;
    }
}
