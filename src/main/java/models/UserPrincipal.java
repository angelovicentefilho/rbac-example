package models;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.security.Principal;
import java.util.Set;

/**
 * @author kawasima
 */
@Data
@RequiredArgsConstructor
public class UserPrincipal implements Principal {
    @NonNull
    private String name;
    @NonNull
    private Set<Permission> permissions;
}
