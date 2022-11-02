package org.capybara.capybarabackend.runs.repository.jpa;

import org.capybara.capybarabackend.runs.domain.jpa.RunEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RunRepository extends JpaRepository<RunEntity, String> {

    List<RunEntity> findTop5ByStatusOrderByScheduledTimeAsc(String status);

}
