package com.eduSolution.eduSolution.controller;

import com.eduSolution.eduSolution.entity.Cart;
import com.eduSolution.eduSolution.entity.Record;
import com.eduSolution.eduSolution.service.CartService;
import com.eduSolution.eduSolution.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/record-controller")
@RestController
public class RecordController {
    @Autowired
    private RecordService recordService;
    @PostMapping("/addRecord")
    public Record addRecord (@RequestBody Record record){
        return recordService.saveRecord(record);
    }
    @PostMapping("/addRecords")
    public List<Record> addRecords (@RequestBody List<Record> records){
        return recordService.saveRecords(records);
    }
    @GetMapping("/records")
    public List<Record> findAllRecords() {
        return recordService.getRecords();
    }
    @GetMapping ("/record/{id}")
    public Record findRecordById(@PathVariable int id) {
        return recordService.getRecordById(id);
    }
    @GetMapping (value = "/recordsByUserId/{userId}")
    public List<Record> findRecordsByUserId(@PathVariable int userId) {
        return recordService.getRecordsByUserId(userId);
    }
    @PutMapping("/updateRecord")
    public Record updateRecord (@RequestBody Record record) {
        return recordService.updateRecord(record);
    }
    @DeleteMapping("/deleteRecord/{id}")
    public String deleteRecord(@PathVariable int id) {
        return recordService.deleteRecord(id);
    }
}
