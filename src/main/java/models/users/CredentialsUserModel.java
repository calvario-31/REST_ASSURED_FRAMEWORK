package models.users;

import com.github.javafaker.Faker;
import models.Model;

public class CredentialsUserModel extends Model {
    private String username;
    private String email;
    private String password;

    public CredentialsUserModel() {
        Faker faker = new Faker();
        username = faker.lorem().characters(10, 19);
        email = faker.internet().emailAddress();
        password = faker.internet().password(10, 12);
    }

    public CredentialsUserModel(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
