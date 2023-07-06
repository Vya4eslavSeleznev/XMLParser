package com.xml.parser.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OutputMessageModel {

    private String description;
    private String theme;
    private String code;
    private String date;
    private String id;
    private List<EmployeeModel> authors;
    private List<String> messages;
    private String phone;
    private String address;
    private String currentDate;
}
