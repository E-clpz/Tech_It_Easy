package nl.novi.techiteasy.controllers;

import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.repositories.TelevisionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class TelevisionController {
    private final TelevisionRepository televisionRepository;

    public TelevisionController(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }

    @GetMapping("/televisions")
    public ResponseEntity <List<Television>> getAllTelevisions(@RequestParam(value = "brand", required = false) String brand) {
        List<Television> televisions;
        if (brand == null){
            televisions = televisionRepository.findAll();
        } else {
            televisions = televisionRepository.findTelevisionsByBrand(brand);
        }
        return ResponseEntity.ok().body(televisions);
    }

    @GetMapping("/televisions/{id}")
    public ResponseEntity<Television> getTelevision(@PathVariable("id") Long id) {

        Optional <Television> television = televisionRepository.findById(id);
        if (television.isEmpty()){
            throw new RecordNotFoundException("No television found with id: " + id );
        } else {
            Television television1 = television.get();
            return ResponseEntity.ok().body(television1);
        }
    }

    @PostMapping("/televisions")
    public ResponseEntity <Television> addTelevision(@RequestBody Television television) {
        Television returnTelevision = televisionRepository.save(television);
        return ResponseEntity.created(null).body(returnTelevision);
    }

    @PutMapping("/televisions/{id}")
    public ResponseEntity <Television> updateTelevision(@PathVariable Long id, @RequestBody Television newTelevision) {

        Optional <Television> television = televisionRepository.findById(id);
        if (television.isEmpty()){
            throw new RecordNotFoundException("No television found with id: " + id);
        }else {
            Television television1 = television.get();
            television1.setType(newTelevision.getType());
            television1.setBrand(newTelevision.getBrand());
            television1.setName(newTelevision.getName());
            television1.setPrice(newTelevision.getPrice());
            television1.setAvailableSize(newTelevision.getAvailableSize());
            television1.setRefreshRate(newTelevision.getRefreshRate());
            television1.setScreenType(newTelevision.getScreenType());
            television1.setScreenQuality(newTelevision.getScreenQuality());
            television1.setSmartTv(newTelevision.getSmartTv());
            television1.setWifi(newTelevision.getWifi());
            television1.setVoiceControl(newTelevision.getVoiceControl());
            television1.setHdr(newTelevision.getHdr());
            television1.setBluetooth(newTelevision.getBluetooth());
            television1.setAmbiLight(newTelevision.getAmbiLight());
            television1.setOriginalStock(newTelevision.getOriginalStock());
            television1.setSold(newTelevision.getSold());

            Television returnTelevision = televisionRepository.save(television1);
            return ResponseEntity.ok().body(returnTelevision);
        }
    }

    @DeleteMapping("/televisions/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable("id") Long id) {
        televisionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}



