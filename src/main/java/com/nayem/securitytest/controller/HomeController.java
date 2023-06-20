package com.nayem.securitytest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<?> home(){
        return ResponseEntity.ok("<h1>Welcome home!!</h1>");
    }

    @GetMapping("public/api/free-access")
    public ResponseEntity<?> publicAccess(){
        return ResponseEntity.ok("<h1>This is the public api's. It can be access by all</h1>");
    }


    @GetMapping("user/valid-user")
    public ResponseEntity<?> user(){
        return ResponseEntity.ok("Only Valid User can access these api's");
    }

    @GetMapping("admin/dashboard")
    public ResponseEntity<?> admin(){
        return ResponseEntity.ok("Only Admin Can access these api's");
    }
}
