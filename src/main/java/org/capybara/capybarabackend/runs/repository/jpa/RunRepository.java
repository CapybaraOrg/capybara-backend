package org.capybara.capybarabackend.runs.repository.jpa;

import org.capybara.capybarabackend.runs.domain.jpa.RunEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RunRepository extends JpaRepository<RunEntity, String> {
}
