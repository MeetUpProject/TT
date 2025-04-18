package com.meetup.meetup.login;

import lombok.Data;
import lombok.Getter;


@Data //getter, setter 포함
public class RequestDto {
    private String id;
    private String password;
}
