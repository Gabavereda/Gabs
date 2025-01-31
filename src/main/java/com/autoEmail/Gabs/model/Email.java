package com.autoEmail.Gabs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "emailsCadastrados")
public class Email {

    private Long id;
    private String destinatario;
    private String assunto;
    private String message;

}
