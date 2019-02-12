package ru.gekov.to;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PasswordTo {

    private String oldPassword;

    @NotBlank
    @Size(min = 5, max = 32)
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
