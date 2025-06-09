package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
@Slf4j
class KangarooController {
    private Map<Long, Kangaroo> kangaroos;

    @PostConstruct
    public void init() {
        kangaroos = new HashMap<>();
    }
    @GetMapping
    public ResponseEntity<List<Kangaroo>> getAll() {
        return ResponseEntity.ok(new ArrayList<>(kangaroos.values()));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Kangaroo> getById(@PathVariable Long id) {
        Kangaroo kangaroo = kangaroos.get(id);
        if (kangaroo == null) {
            throw new ZooException("Kangaroo not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(kangaroo);
    }

    @PostMapping
    public ResponseEntity<Kangaroo> create(@RequestBody Kangaroo kangaroo) {
        kangaroos.put(kangaroo.getId(), kangaroo);
        return new ResponseEntity<>(kangaroo, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kangaroo> update(@PathVariable Long id, @RequestBody Kangaroo kangaroo) {
        if (!kangaroos.containsKey(id)) {
            throw new ZooException("Kangaroo not found", HttpStatus.NOT_FOUND);
        }
        kangaroos.put(id, kangaroo);
        return ResponseEntity.ok(kangaroo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!kangaroos.containsKey(id)) {
            throw new ZooException("Kangaroo not found", HttpStatus.NOT_FOUND);
        }
        kangaroos.remove(id);
        return ResponseEntity.ok().build();
    }
}

