package com.nhnacademy.nhnmart.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class User {

    @NonNull
    private String id;
    @NonNull
    private String password;
    @NonNull
    private String name;


}
