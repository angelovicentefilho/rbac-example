package models;

import javax.persistence.*;

/**
 * @author kawasima
 */
@Entity
public class Issue {
    @Id
    @GeneratedValue
    private Long issueId;

    private String subject;
    private String description;

    @ManyToOne
    private User postedBy;
}
