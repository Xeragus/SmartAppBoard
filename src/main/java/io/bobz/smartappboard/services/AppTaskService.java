package io.bobz.smartappboard.services;

import io.bobz.smartappboard.domain.AppTask;
import io.bobz.smartappboard.repositories.AppTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppTaskService {

    @Autowired
    private AppTaskRepository appTaskRepository;

    public AppTask saveOrUpdateAppTask(AppTask appTask) {

        if (appTask.getStatus() == null || appTask.getStatus() == "") {
            appTask.setStatus("TO_DO");
        }

        return appTaskRepository.save(appTask);
    }

    public Iterable<AppTask> getAllAppTasks() {
        return appTaskRepository.findAll();
    }

    public AppTask getAppTaskById(Long id) {
        return appTaskRepository.getById(id);
    }
}
