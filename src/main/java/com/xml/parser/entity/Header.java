package com.xml.parser.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Header {

    public Header(String messageCode, String description, String messageTopic) {
        this.messageCode = messageCode;
        this.description = description;
        this.messageTopic = messageTopic;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String messageCode;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String messageTopic;
}
