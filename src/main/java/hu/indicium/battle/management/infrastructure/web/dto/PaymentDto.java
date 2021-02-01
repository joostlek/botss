package hu.indicium.battle.management.infrastructure.web.dto;

import hu.indicium.battle.management.domain.participant.payment.Payment;
import hu.indicium.battle.management.domain.participant.payment.PaymentId;
import hu.indicium.battle.management.domain.participant.payment.PaymentStatus;
import lombok.Data;

import javax.persistence.EmbeddedId;
import java.util.Date;
import java.util.UUID;

@Data
public class PaymentDto {
    private UUID id;

    private Double amount;

    private String description;

    private Date createdAt;

    private Date updatedAt;

    private PaymentStatus status;

    private String externalId;

    private String paymentProvider;

    private Date expiresAt;

    private String checkoutUrl;

    private String webhookUrl;

    private String redirectUrl;

    public PaymentDto(Payment payment) {
        this.id = payment.getPaymentId().getId();
        this.amount = payment.getAmount();
        this.description = payment.getDescription();
        this.createdAt = payment.getCreatedAt();
        this.updatedAt = payment.getUpdatedAt();
        this.status = payment.getStatus();
        this.externalId = payment.getMollieDetails().getExternalId();
        this.paymentProvider = payment.getMollieDetails().getPaymentProvider();
        this.expiresAt = payment.getMollieDetails().getExpiresAt();
        this.checkoutUrl = payment.getMollieDetails().getCheckoutUrl();
        this.webhookUrl = payment.getMollieDetails().getWebhookUrl();
        this.redirectUrl = payment.getMollieDetails().getRedirectUrl();
    }
}
