package com.nhnacademy.nhnmart.admin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Admin {

    @NonNull
    private String id;
    @NonNull
    private String password;
    @NonNull
    private String name;


}
