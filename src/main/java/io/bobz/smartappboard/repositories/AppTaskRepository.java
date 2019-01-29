package io.bobz.smartappboard.repositories;

import io.bobz.smartappboard.domain.AppTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppTaskRepository extends CrudRepository<AppTask, Long> {
    AppTask getById(Long id);
}
