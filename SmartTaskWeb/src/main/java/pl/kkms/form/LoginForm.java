package pl.kkms.form;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class LoginForm {

    @Size(min = 5)
    @NotEmpty
    private String login;
    @Size(min = 5)
    @NotEmpty
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
