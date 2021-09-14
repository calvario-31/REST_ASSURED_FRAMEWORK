package models.users;

import com.github.javafaker.Faker;

public class UpdateUserModel {
    private String username;
    private String email;
    private String password;
    private String bio;
    private String image;

    public UpdateUserModel() {
        Faker faker = new Faker();
        username = faker.name().username();
        email = faker.internet().emailAddress();
        password = faker.internet().password(8, 12);
        bio = faker.lorem().paragraph();
        image = faker.internet().image();
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
