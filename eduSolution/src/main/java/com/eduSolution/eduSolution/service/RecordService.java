package com.eduSolution.eduSolution.service;

import com.eduSolution.eduSolution.entity.Cart;
import com.eduSolution.eduSolution.entity.Record;
import com.eduSolution.eduSolution.repository.CartRepository;
import com.eduSolution.eduSolution.repository.ClassGroupRepository;
import com.eduSolution.eduSolution.repository.RecordRepository;
import com.eduSolution.eduSolution.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {
    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClassGroupRepository classGroupRepository;


    public Record saveRecord (Record record){
        record.setClassGroup(classGroupRepository.findById(record.getClassGroup().getId()).orElse(null));
        record.setUser(userRepository.findById(record.getUser().getId()).orElse(null));
        return recordRepository.save(record);
    }

    public List<Record> saveRecords (List <Record> records){
        return recordRepository.saveAll(records);
    }
    public  Record getRecordById (int id){
        return recordRepository.findById(id).orElse(null);
    }

    public  List<Record> getRecords (){
        return recordRepository.findAll();
    }

    public List<Record> getRecordsByUserId(int userId) {
        return recordRepository.findByUserId(userId);
    }

    public Record updateRecord (Record record){
        Record existingRecord = recordRepository.findById(record.getId()).orElse(null);
        existingRecord.setClassGroup(record.getClassGroup());
        existingRecord.setUser(record.getUser());
        return recordRepository.save(existingRecord);
    }

    public String deleteRecord(int id){
        recordRepository.deleteById(id);
        return "Pozycja została usunięta";
    }
}
