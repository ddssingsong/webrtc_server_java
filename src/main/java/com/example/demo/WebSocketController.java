package com.example.demo;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {

//    @RequestMapping(value = "/operation/{vmc}/{cmd}")
//    public String remote(@PathVariable("vmc") long vmc, @PathVariable("cmd") String cmd) {
//        System.out.print("remote");
//        RemoteOperation operation = new RemoteOperation();
//        operation.setVmc_no(vmc);
//        operation.setOperation(cmd);
//        String message = new Gson().toJson(operation);
//        System.out.println("message in json is :" + message);
//        return WebSocketServer.sendMessage(message, vmc);
//    }

//    @RequestMapping(value = "/test")
//    public String test() {
//        System.out.print("test");
//        return "hello world";
//    }
}