package sk.tuke.gamestudio.server.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegisterUserDto {
    @NotBlank(message = "USERNAME SIGNAL IS REQUIRED")
    @Size(min = 3, max = 30, message = "USERNAME MUST BE 3-30 SYMBOLS")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "USERNAME ACCEPTS ONLY LETTERS, NUMBERS AND _")
    private String username;

    @NotBlank(message = "EMAIL CHANNEL IS REQUIRED")
    @Email(message = "EMAIL CHANNEL IS NOT VALID")
    @Size(max = 120, message = "EMAIL CHANNEL IS TOO LONG")
    private String email;

    @NotBlank(message = "ACCESS KEY IS REQUIRED")
    @Size(min = 8, max = 72, message = "ACCESS KEY MUST BE 8-72 SYMBOLS")
    private String password;

    @NotBlank(message = "ACCESS KEY CONFIRMATION IS REQUIRED")
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim().toLowerCase();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
