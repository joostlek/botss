package hu.indicium.battle.management.infrastructure.mollie;

import be.woutschoovaerts.mollie.Client;
import be.woutschoovaerts.mollie.ClientBuilder;
import be.woutschoovaerts.mollie.data.common.Amount;
import be.woutschoovaerts.mollie.data.payment.PaymentRequest;
import be.woutschoovaerts.mollie.data.payment.PaymentResponse;
import be.woutschoovaerts.mollie.exception.MollieException;
import hu.indicium.battle.management.domain.participant.payment.MollieDetails;
import hu.indicium.battle.management.domain.participant.payment.Payment;
import hu.indicium.battle.management.infrastructure.util.Util;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class MollieService {
    private final Client client;

    public MollieService() {
        this.client = new ClientBuilder()
                .withApiKey(System.getenv("MOLLIE_API_KEY"))
                .build();
    }

    public MolliePayment createPayment(Payment payment, String redirectUrl) {
        try {
            Amount amount1 = Amount.builder()
                    .value(Util.formatCurrency(payment.getAmount()))
                    .currency("EUR")
                    .build();
            Map<String, Object> metadata = new HashMap<>();
            metadata.put("transactionId", payment.getPaymentId().getId());
            PaymentRequest paymentRequest = PaymentRequest.builder()
                    .amount(amount1)
                    .redirectUrl(Optional.of(redirectUrl))
                    .description(payment.getDescription())
                    .webhookUrl(Optional.of(System.getenv("SERVICE_BASE" + "/api/v1/mollie")))
                    .metadata(metadata)
                    .build();
            PaymentResponse paymentResponse = client.payments().createPayment(paymentRequest);
            return new MolliePayment(paymentResponse);
        } catch (MollieException e) {
            e.printStackTrace();
            throw new IllegalStateException("Mollie:" + e.getMessage());
        }
    }
}
