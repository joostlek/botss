package hu.indicium.battle.management.infrastructure.mollie;

import be.woutschoovaerts.mollie.data.payment.PaymentResponse;
import hu.indicium.battle.management.domain.participant.payment.PaymentId;
import hu.indicium.battle.management.domain.participant.payment.PaymentStatus;
import lombok.Getter;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Getter
public class MolliePayment {
    private String externalId;

    private PaymentId paymentId;

    private PaymentStatus status;

    private Date expiresAt;

    private String checkoutUrl;

    private String webhookUrl;

    private String redirectUrl;

    private Date paidAt;

    public MolliePayment(PaymentResponse paymentResponse) {
        this.externalId = paymentResponse.getId();
        Map<String, Object> metadata = paymentResponse.getMetadata();
        String id = (String) metadata.get("paymentId");
        UUID uuid = UUID.fromString(id);
        this.paymentId = PaymentId.fromId(uuid);
        this.status = getTransactionStatus(paymentResponse.getStatus());
        if (this.status.equals(PaymentStatus.PENDING) || this.status.equals(PaymentStatus.OPEN)) {
            this.expiresAt = paymentResponse.getExpiresAt();
            this.checkoutUrl = paymentResponse.getLinks().getCheckout().getHref();
        } else if (this.status.equals(PaymentStatus.PAID)) {
            this.paidAt = paymentResponse.getPaidAt().orElse(new Date());
        }
        this.webhookUrl = paymentResponse.getWebhookUrl().orElse("not found");
        this.redirectUrl = paymentResponse.getRedirectUrl();
    }

    private PaymentStatus getTransactionStatus(String status) {
        switch (status) {
            case "open":
                return PaymentStatus.OPEN;
            case "pending":
                return PaymentStatus.PENDING;
            case "paid":
                return PaymentStatus.PAID;
            case "expired":
                return PaymentStatus.EXPIRED;
            case "failed":
                return PaymentStatus.FAILED;
            case "canceled":
                return PaymentStatus.CANCELED;
        }
        throw new IllegalStateException("Unknown state");
    }
}