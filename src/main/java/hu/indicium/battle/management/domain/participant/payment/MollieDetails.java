package hu.indicium.battle.management.domain.participant.payment;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
@Getter
@NoArgsConstructor
public class MollieDetails implements Serializable {
    @Column(name = "externalId")
    private String externalId;

    @Column(name = "paymentProvider")
    private String paymentProvider;

    @Column(name = "expiresAt")
    private Date expiresAt;

    @Column(name = "checkoutUrl")
    private String checkoutUrl;

    @Column(name = "webhookUrl")
    private String webhookUrl;

    @Column(name = "redirectUrl")
    private String redirectUrl;

    public MollieDetails(String externalId, String paymentProvider, Date expiresAt, String checkoutUrl, String webhookUrl, String redirectUrl) {
        this.setExternalId(externalId);
        this.setPaymentProvider(paymentProvider);
        this.setExpiresAt(expiresAt);
        this.setCheckoutUrl(checkoutUrl);
        this.setWebhookUrl(webhookUrl);
        this.setRedirectUrl(redirectUrl);
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public void setPaymentProvider(String paymentProvider) {
        this.paymentProvider = paymentProvider;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    public void setCheckoutUrl(String checkoutUrl) {
        this.checkoutUrl = checkoutUrl;
    }

    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
