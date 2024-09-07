package com.example.personnel.controller;

import com.example.personnel.model.personnel;
import com.example.personnel.service.personnelService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/personnel")
public class personnelcontroller {

    private final personnelService personnelService;

    public personnelcontroller(personnelService personnelService) {
        this.personnelService = personnelService;
    }

    @GetMapping
    public ResponseEntity<List<personnel>> getAllPersonnel() {
        List<personnel> personnelList = personnelService.getAllPersonnel();
        return ResponseEntity.ok(personnelList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<personnel> getPersonnelById(@PathVariable Long id) {
        try {
            personnel personnel = personnelService.getPersonnelById(id);
            return ResponseEntity.ok(personnel);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<personnel> createPersonnel(@Valid @RequestBody personnel personnel) {
        personnel createdPersonnel = personnelService.createPersonnel(personnel);
        return ResponseEntity.ok(createdPersonnel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<personnel> updatePersonnel(
            @PathVariable Long id,
            @Valid @RequestBody personnel personnelDetails) {
        try {
            personnel updatedPersonnel = personnelService.updatePersonnel(id, personnelDetails);
            return ResponseEntity.ok(updatedPersonnel);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonnel(@PathVariable Long id) {
        try {
            personnelService.deletePersonnel(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
