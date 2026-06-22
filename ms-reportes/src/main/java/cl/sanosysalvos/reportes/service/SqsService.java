package cl.sanosysalvos.reportes.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Service
public class SqsService {

    private final SqsClient sqsClient;
    private final String queueUrl;

    public SqsService(
            @Value("${aws.region:us-east-1}") String region,
            @Value("${aws.sqs.queue-url:}") String queueUrl) {
        this.sqsClient = SqsClient.builder()
                .region(Region.of(region))
                .build();
        this.queueUrl = queueUrl;
    }

    public void enviarMensaje(String mensaje) {
        if (queueUrl == null || queueUrl.isBlank()) {
            System.out.println("[SQS] Queue URL no configurada, se omite el envio.");
            return;
        }
        sqsClient.sendMessage(SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(mensaje)
                .build());
        System.out.println("[SQS] Mensaje enviado a la cola: " + mensaje);
    }
}
