package cn.orgtec.hix.broadcast.controller;

import cn.orgtec.hix.broadcast.service.impl.CreateUserHbase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author Yibo Zhang
 * @date 2019/05/08
 */
@RestController
@RequestMapping(value = "/hadoop")
public class HbaseController {

    @Autowired
    private CreateUserHbase createUserHbase;

    @GetMapping("/hbaseTable")
    public String createUserHbase() throws IOException {
        createUserHbase.initialize();
        return "success";
    }
}
