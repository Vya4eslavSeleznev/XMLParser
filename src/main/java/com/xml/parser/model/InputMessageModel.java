package com.xml.parser.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class InputMessageModel {

    private String code;
    private String createdDate;
    private String uid;
    private List<String> authorsId;
    private List<String> text;
    private String address;
    private String phone;

}
