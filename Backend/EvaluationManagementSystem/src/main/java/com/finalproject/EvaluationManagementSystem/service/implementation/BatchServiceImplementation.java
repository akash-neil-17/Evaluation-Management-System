package com.finalproject.EvaluationManagementSystem.service.implementation;

import com.finalproject.EvaluationManagementSystem.entity.BatchEntity;
import com.finalproject.EvaluationManagementSystem.model.BatchModel;
import com.finalproject.EvaluationManagementSystem.repository.BatchRepository;
import com.finalproject.EvaluationManagementSystem.service.BatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BatchServiceImplementation implements BatchService {
    private final BatchRepository batchRepository;
    @Override
    public ResponseEntity<Object> create(BatchModel batchModel) {
        Optional<BatchEntity> batch = batchRepository.findByBatchName(batchModel.getBatchName());
        if (batch.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            BatchEntity batchEntity = BatchEntity.builder()
                    .batchName(batchModel.getBatchName())
                    .build();
            BatchEntity savedBatchEntity = batchRepository.save(batchEntity);
            return new ResponseEntity<>(savedBatchEntity, HttpStatus.CREATED);
        }

    }

    @Override
    public ResponseEntity<Object> update(BatchModel batchModel, Long batchID) {
        Optional<BatchEntity> batchEntity = batchRepository.findById(batchID);
        if (batchEntity.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            BatchEntity existingBatchEntity = batchEntity.get();
            existingBatchEntity.setBatchName(batchModel.getBatchName());
            BatchEntity updatedBatchEntity = batchRepository.save(existingBatchEntity);
            return new ResponseEntity<>(updatedBatchEntity, HttpStatus.ACCEPTED);
        }
    }

    @Override
    public ResponseEntity<Object> delete(BatchModel batchModel) {
        Optional<BatchEntity> batchEntity = batchRepository.findByBatchName(batchModel.getBatchName());
        if(batchEntity.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            BatchEntity existingBatch = batchEntity.get();
            batchRepository.delete(existingBatch);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
    }

    @Override
    public ResponseEntity<List<BatchEntity>> batchList() {
        List<BatchEntity> list = batchRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<Object> getBatchByID(Long batchID) {
        Optional<BatchEntity> batchEntity = batchRepository.findById(batchID);
        if (batchEntity.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            BatchEntity existingBatch = batchEntity.get();
            BatchModel batchModel = new BatchModel();
            batchModel.setBatchName(existingBatch.getBatchName());
            return new ResponseEntity<>(batchModel, HttpStatus.FOUND);
        }
    }
}
