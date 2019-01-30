package io.bobz.smartappboard.controllers;
// ()
import io.bobz.smartappboard.domain.AppTask;
import io.bobz.smartappboard.services.AppTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/board")
@CrossOrigin
public class AppTaskController {

    @Autowired
    private AppTaskService appTaskService;

    @PostMapping("")
    public ResponseEntity<?> addTaskToBoard(@Valid @RequestBody AppTask appTask, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError fieldError: result.getFieldErrors()) {
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }

            return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        }

        AppTask newAppTask = appTaskService.saveOrUpdateAppTask(appTask);

        return new ResponseEntity<AppTask>(newAppTask, HttpStatus.CREATED);
    }

    @GetMapping("/all-app-tasks")
    public Iterable<AppTask> getAllAppTasks() {
        return appTaskService.getAllAppTasks();
    }

    // ()
    @GetMapping("/app-tasks/{id}")
    public ResponseEntity<?> getAppTaskById(@PathVariable Long id) {
        AppTask appTask = appTaskService.getAppTaskById(id);

        return new ResponseEntity<AppTask>(appTask, HttpStatus.OK);
    }

    @DeleteMapping("/app-tasks/{id}")
    public ResponseEntity<?> deleteAppTaskById(@PathVariable Long id) {
        appTaskService.delete(id);

        return new ResponseEntity<String>("Project task deleted", HttpStatus.OK);
    }
}
