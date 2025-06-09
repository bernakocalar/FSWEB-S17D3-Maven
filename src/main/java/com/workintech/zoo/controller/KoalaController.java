package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
@Slf4j
class KoalaController {
    private Map<Long, Koala> koalas;

    @PostConstruct
    public void init() {
        koalas = new HashMap<>();
    }

    @GetMapping
    public ResponseEntity<List<Koala>> getAll() {
        return ResponseEntity.ok(new ArrayList<>(koalas.values()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Koala> getById(@PathVariable Long id) {
        Koala koala = koalas.get(id);
        if (koala == null) {
            throw new ZooException("Koala not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(koala);
    }

    @PostMapping
    public ResponseEntity<Koala> create(@RequestBody Koala koala) {
        koalas.put(koala.getId(), koala);
        return new ResponseEntity<>(koala, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Koala> update(@PathVariable Long id, @RequestBody Koala koala) {
        if (!koalas.containsKey(id)) {
            throw new ZooException("Koala not found", HttpStatus.NOT_FOUND);
        }
        koalas.put(id, koala);
        return ResponseEntity.ok(koala);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!koalas.containsKey(id)) {
            throw new ZooException("Koala not found", HttpStatus.NOT_FOUND);
        }
        koalas.remove(id);
        return ResponseEntity.ok().build();
    }
}
