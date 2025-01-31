package com.autoEmail.Gabs.service;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class EmailService {

    //  configurar api
    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;

    public void enviarEmail(String destinatario, String assunto, String mensagem) throws IOException {

        // esse escopo pode mudar futuramente
        Email from = new Email("gabavereda@gmail.com");
        Email to = new Email(destinatario);
        Content content = new Content("text/plain", mensagem);
        Mail mail = new Mail(from, assunto, to, content);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody());
            System.out.println("Response Headers: " + response.getHeaders());

            if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
                System.out.println("✅ E-mail enviado com sucesso!");
            } else {
                System.err.println("❌ Falha ao enviar e-mail: " + response.getBody());
            }
        } catch (IOException ex) {
            throw new RuntimeException("Erro ao enviar e-mail", ex);
        }
    }
}
