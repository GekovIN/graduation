package ru.gekov.model;

import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "USERS", uniqueConstraints = {@UniqueConstraint(columnNames = {"EMAIL"}, name = "users_unique_email_idx")})
public class User extends AbstractNamedEntity {

    @Id
    @SequenceGenerator(name = "USERS_SEQ", sequenceName = "USERS_SEQ",
            allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ")
    private Integer id;

    @Column(name = "EMAIL", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    @NotBlank
    @Size(min = 5, max = 100)
    private String password;

    @Column(name = "REGISTERED", columnDefinition = "timestamp default now()")
    @NotNull
    private Date registered = new Date();

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "USER_ROLES", joinColumns = @JoinColumn(name = "USER_ID"))
    @Column(name = "ROLE")
    @ElementCollection(fetch = FetchType.LAZY)
    private Set<Role> roles;


    public User() {
    }

    public User(String name, String email, String password, Role role, Role... roles) {
        super(name);
        this.email = email;
        this.password = password;
        this.registered = new Date();
        setRoles(EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String password, Role role, Role... roles) {
        this(id, name, email, password, new Date(), EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String password, Date registered, Collection<Role> roles) {
        super(name);
        this.id = id;
        this.email = email;
        this.password = password;
        this.registered = registered;
        setRoles(roles);
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

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? Collections.emptySet() : EnumSet.copyOf(roles);

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
