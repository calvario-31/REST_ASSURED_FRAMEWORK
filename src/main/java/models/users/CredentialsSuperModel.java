package models.users;

import models.Model;

public class CredentialsSuperModel extends Model {
    private CredentialsModel user;

    public CredentialsSuperModel() {
        user = new CredentialsModel();
    }

    public CredentialsSuperModel(CredentialsModel user) {
        this.user = user;
    }

    public CredentialsModel getUser() {
        return user;
    }

    public void setUser(CredentialsModel user) {
        this.user = user;
    }
}
