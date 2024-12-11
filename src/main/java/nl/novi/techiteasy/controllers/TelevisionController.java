package nl.novi.techiteasy.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TelevisionController {

    @GetMapping("/televisions")
    public ResponseEntity <String> getAllTelevisions() {
        return ResponseEntity.ok("televisions");
    }

    @GetMapping("/televisions/{id}")
    public ResponseEntity <String> getTelevision(@PathVariable ("id") int id) {
        return ResponseEntity.ok("television with id: " + id);
    }

    @PostMapping("televisions")
    public ResponseEntity <String> addTelevision(@RequestBody String television) {
        return ResponseEntity.created(null).body("television");
    }

    @PutMapping("/televisions/{id}")
    public ResponseEntity <Void> updateTelevision(@PathVariable int id, @RequestBody String television) {
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/televisions/{id}")
    public ResponseEntity <Void> deleteTelevision(@PathVariable int id) {
        return ResponseEntity.noContent().build();
    }
}



