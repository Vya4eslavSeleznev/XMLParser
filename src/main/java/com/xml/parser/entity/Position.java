package com.xml.parser.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Position {

    public Position(String customPositionId, String name) {
        this.customPositionId = customPositionId;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, name = "custom_position_id")
    private String customPositionId;

    @Column(nullable = false)
    private String name;
}
