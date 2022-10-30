package org.capybara.capybarabackend.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    //<editor-fold desc="Public methods">

    @GetMapping
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("OK");
    }

    //</editor-fold>

}
