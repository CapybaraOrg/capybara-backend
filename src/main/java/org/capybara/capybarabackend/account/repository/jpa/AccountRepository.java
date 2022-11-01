package org.capybara.capybarabackend.account.repository.jpa;

import org.capybara.capybarabackend.account.domain.jpa.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {

    Optional<AccountEntity> findByClientId(String clientId);

}
