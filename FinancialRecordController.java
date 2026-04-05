package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.FinancialRecord;
import com.example.demo.service.FinancialRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

    @RestController
    @RequestMapping("/records")
    public class FinancialRecordController {
        @Autowired
        private final FinancialRecordService service;

        public FinancialRecordController(FinancialRecordService service) {
            this.service = service;
        }

        // CREATE
        @PostMapping
        public ResponseEntity<FinancialRecord> create(@RequestBody FinancialRecord record) {
            return new ResponseEntity<>(service.create(record), HttpStatus.CREATED);
        }

        // READ ALL
        @GetMapping
        public ResponseEntity<List<FinancialRecord>> getAll() {
            return ResponseEntity.ok(service.getAll());
        }

        // READ BY ID
        @GetMapping("/{id}")
        public ResponseEntity<FinancialRecord> getById(@PathVariable Long id) {
            return ResponseEntity.ok(service.getById(id));
        }

        // UPDATE
        @PutMapping("/{id}")
        public ResponseEntity<FinancialRecord> update(
                @PathVariable Long id,
                @RequestBody FinancialRecord record) {
            return ResponseEntity.ok(service.update(id, record));
        }

        // DELETE
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }

        // FILTERS
        @GetMapping("/filter/date")
        public ResponseEntity<List<FinancialRecord>> filterByDate(
                @RequestParam LocalDate start,
                @RequestParam LocalDate end) {
            return ResponseEntity.ok(service.filterByDate(start, end));
        }

        @GetMapping("/filter/category")
        public ResponseEntity<List<FinancialRecord>> filterByCategory(
                @RequestParam String category) {
            return ResponseEntity.ok(service.filterByCategory(category));
        }

        @GetMapping("/filter/type")
        public ResponseEntity<List<FinancialRecord>> filterByType(
                @RequestParam String type) {
            return ResponseEntity.ok(service.filterByType(type));
        }

        // DASHBOARD
        @GetMapping("/summary/total")
        public ResponseEntity<Double> getTotal() {
            return ResponseEntity.ok(service.getTotalAmount());
        }
    }

