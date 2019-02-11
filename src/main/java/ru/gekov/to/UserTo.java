package ru.gekov.to;

import org.hibernate.validator.constraints.SafeHtml;
import ru.gekov.model.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

public class UserTo extends AbstractTo implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 2, max = 100)
    @SafeHtml
    private String name;

    @Email
    @NotBlank
    @Size(max = 100)
    @SafeHtml
    private String email;

    @Size(min = 5, max = 32)
    private String password;
//
    private boolean enabled;
//
    private Set<Role> roles;

    public UserTo() {
    }

    public UserTo(Integer id, String name, String email, String password) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        if (roles.isEmpty()) {
            this.roles = Set.of(Role.ROLE_USER);
        } else {
            this.roles = roles;
        }
    }

    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
