package com.xml.parser.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Author {

    public Author(String customAuthorId, String secondName, String firstName, String lastName, Position position) {
        this.customAuthorId = customAuthorId;
        this.secondName = secondName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String customAuthorId;

    @Column(nullable = false)
    private String secondName;

    @Column(nullable = false)
    private String firstName;

    @Column
    private String lastName;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "custom_position_id")
    private Position position;
}
