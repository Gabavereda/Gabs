package com.autoEmail.Gabs.controller;

import com.autoEmail.Gabs.model.EmailIdentidade;
import com.autoEmail.Gabs.repository.EmailRepository;
import com.autoEmail.Gabs.service.EmailService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Irei testar com Postman
@RestController
@RequestMapping("/emails")
public class EmailController {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private EmailService emailService;

    // cadastrar um email 
    @PostMapping("/cadastrar")
    public EmailIdentidade cadastrarEmail(@RequestBody EmailIdentidade email) {
        return emailRepository.save(email);
    }

    // listar os emails
    @GetMapping("/listar")
    public List<EmailIdentidade> listarEmails() {
        return emailRepository.findAll();
    }

    @PostMapping("/enviar/{id}")
    public String enviarEmail(@PathVariable Long id) throws IOException {
        EmailIdentidade email = emailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Email não encontrado"));
        emailService.enviarEmail(email.getDestinatario(), email.getAssunto(), email.getMessage());
        return "Email enviado Com sucesso";
    }

}
