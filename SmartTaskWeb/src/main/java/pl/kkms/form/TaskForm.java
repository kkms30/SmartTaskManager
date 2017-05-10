package pl.kkms.form;

import org.hibernate.validator.constraints.NotEmpty;

public class TaskForm {

    @NotEmpty
    private String userCode;
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
