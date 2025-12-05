package com.seknova.api.service;

import com.seknova.api.dto.ApiResponse;
import com.seknova.api.dto.RecordRequest;
import com.seknova.api.entity.Record;
import com.seknova.api.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService {
    
    private final RecordRepository recordRepository;
    
    public ApiResponse<?> addRecord(Long userId, RecordRequest request) {
        try {
            Record record = new Record();
            record.setUserId(userId);
            record.setCount(request.getCount());
            record.setRecordTime(request.getRecordTime());
            record.setDisplayTime(request.getDisplayTime());
            record.setTemperature(request.getTemperature());
            record.setAdc1(request.getAdc1());
            record.setBattery(request.getBattery());
            record.setCheck(request.getCheck());
            record.setCalibrated(request.getCalibrated());
            
            recordRepository.save(record);
            return new ApiResponse<>("ok", "Record added successfully");
        } catch (Exception e) {
            return new ApiResponse<>("not", "Failed to add record: " + e.getMessage());
        }
    }
    
    public ApiResponse<?> getAllRecords(Long userId) {
        try {
            List<Record> records = recordRepository.findByUserId(userId);
            return new ApiResponse<>("ok", "Records retrieved successfully", records);
        } catch (Exception e) {
            return new ApiResponse<>("not", "Failed to retrieve records: " + e.getMessage());
        }
    }
    
    public ApiResponse<?> changeRecord(Long recordId, RecordRequest request) {
        try {
            Record record = recordRepository.findById(recordId)
                    .orElseThrow(() -> new Exception("Record not found"));
            
            if (request.getCount() != null) record.setCount(request.getCount());
            if (request.getRecordTime() != null) record.setRecordTime(request.getRecordTime());
            if (request.getDisplayTime() != null) record.setDisplayTime(request.getDisplayTime());
            if (request.getTemperature() != null) record.setTemperature(request.getTemperature());
            if (request.getAdc1() != null) record.setAdc1(request.getAdc1());
            if (request.getBattery() != null) record.setBattery(request.getBattery());
            if (request.getCheck() != null) record.setCheck(request.getCheck());
            if (request.getCalibrated() != null) record.setCalibrated(request.getCalibrated());
            
            recordRepository.save(record);
            return new ApiResponse<>("ok", "Record updated successfully");
        } catch (Exception e) {
            return new ApiResponse<>("not", "Failed to update record: " + e.getMessage());
        }
    }
    
    public ApiResponse<?> deleteRecord(Long recordId) {
        try {
            if (!recordRepository.existsById(recordId)) {
                return new ApiResponse<>("not", "Record not found");
            }
            
            recordRepository.deleteById(recordId);
            return new ApiResponse<>("ok", "Record deleted successfully");
        } catch (Exception e) {
            return new ApiResponse<>("not", "Failed to delete record: " + e.getMessage());
        }
    }
}
