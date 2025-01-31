package com.autoEmail.Gabs.repository;

import com.autoEmail.Gabs.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {

}
