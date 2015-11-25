package models;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * @author kawasima
 */
@Entity
@Data
@RequiredArgsConstructor
public class Role {
    @Id
    @GeneratedValue
    private Long roleId;

    @NonNull
    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "ROLE_PERMISSION",
            joinColumns = {@JoinColumn(name = "ROLE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "PERMISSION_ID")}
    )
    private List<Permission> permissions;
}
