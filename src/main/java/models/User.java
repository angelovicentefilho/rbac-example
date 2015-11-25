package models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author kawasima
 */
@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private Long userId;

    private String account;
    private String lastName;
    private String firstName;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")}
    )
    private List<Role> roles;
}
