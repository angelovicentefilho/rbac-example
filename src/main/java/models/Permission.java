package models;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author kawasima
 */
@Entity
@Data
@RequiredArgsConstructor
public class Permission {
    @Id
    @GeneratedValue
    private long permissionId;

    @NonNull
    private String name;
}
