package com.finalproject.EvaluationManagementSystem.service;

import com.finalproject.EvaluationManagementSystem.entity.TaskCreateEntity;
import com.finalproject.EvaluationManagementSystem.model.TaskCreateModel;
import com.finalproject.EvaluationManagementSystem.model.TaskCreateRequestModel;
import com.finalproject.EvaluationManagementSystem.model.TaskSearchModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskCreateService {

    ResponseEntity<Object> create(TaskCreateRequestModel taskCreateModel);

    ResponseEntity<Object> update(TaskCreateModel updatedTask);

    ResponseEntity<Object> getTask(Long taskID);

    ResponseEntity<List<TaskCreateEntity>> taskList(TaskSearchModel taskCreateModel);

    ResponseEntity<Object> delete(TaskCreateModel taskCreateModel);

    ResponseEntity<List<TaskCreateEntity>> allTaskList();
}
