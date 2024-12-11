package nl.novi.techiteasy.controllers;

import nl.novi.techiteasy.exceptions.NameTooLongException;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;


@RestController
public class TelevisionController {
    private final List<String> televisionDataBase = new ArrayList<>(List.of("Samsung", "LG", "Sony"));

    @GetMapping("/televisions")
    public ResponseEntity <String> getAllTelevisions() {
        return ResponseEntity.ok("televisions");
    }

    @GetMapping("/televisions/{id}")
    public ResponseEntity <String> getTelevision(@PathVariable ("id") int id) {
        if (id < 0 || id>= televisionDataBase.size()) {
            throw new RecordNotFoundException("Television with id " + id + " not found.");
        }
        return ResponseEntity.ok(televisionDataBase.get(id));
    }

    @PostMapping("/televisions")
    public ResponseEntity <String> addTelevision(@RequestBody String television) {
        if (television.length() > 20) {
            throw new NameTooLongException("Television name is too long " + television);
        }
        televisionDataBase.add(television);
        return ResponseEntity.created(null).body("television added: " + television);
    }

    @PutMapping("/televisions/{id}")
    public ResponseEntity <Void> updateTelevision(@PathVariable int id, @RequestBody String television) {
        if (id < 0 || id >= televisionDataBase.size()) {
            throw new RecordNotFoundException("Television with id "+ id + " not found.");
        }
        televisionDataBase.set(id, television);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/televisions/{id}")
    public ResponseEntity<Void> deleteTelevision(@PathVariable int id) {
        if (id < 0 || id >= televisionDataBase.size()) {
            throw new RecordNotFoundException("Television with id " + id + " not found.");
        }
        televisionDataBase.set(id, null);
        return ResponseEntity.noContent().build();
    }
}



