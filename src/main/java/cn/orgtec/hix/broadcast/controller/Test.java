package cn.orgtec.hix.broadcast.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Test {

    @JsonProperty("na_me")
    private String name;

    private String age;
}