package com.example.demo.service;

import com.example.demo.model.FinancialRecord;
import com.example.demo.repository.FinancialRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


    @Service
    public class FinancialRecordService {

        private final FinancialRecordRepository repo;

        public FinancialRecordService(FinancialRecordRepository repo) {
            this.repo = repo;
        }

        public FinancialRecord create(FinancialRecord record) {
            return repo.save(record);
        }

        public List<FinancialRecord> getAll() {
            return repo.findAll();
        }

        public FinancialRecord getById(Long id) {
            return repo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Record not found"));
        }

        public FinancialRecord update(Long id, FinancialRecord record) {
            FinancialRecord existing = getById(id);

            existing.setAmount(record.getAmount());
            existing.setCategory(record.getCategory());
            existing.setDate(record.getDate());
            existing.setType(record.getType());

            return repo.save(existing);
        }

        public void delete(Long id) {
            repo.deleteById(id);
        }

        public List<FinancialRecord> filterByDate(LocalDate start, LocalDate end) {
            return repo.findByDateBetween(start, end);
        }

        public List<FinancialRecord> filterByCategory(String category) {
            return repo.findByCategory(category);
        }

        public List<FinancialRecord> filterByType(String type) {
            return repo.findByType(type);
        }

        public Double getTotalAmount() {
            return repo.findAll()
                    .stream()
                    .mapToDouble(FinancialRecord::getAmount)
                    .sum();
        }
    }
