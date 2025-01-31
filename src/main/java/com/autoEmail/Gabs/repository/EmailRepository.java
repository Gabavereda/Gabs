package com.autoEmail.Gabs.repository;

import com.autoEmail.Gabs.model.EmailIdentidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailIdentidade, Long> {

}
