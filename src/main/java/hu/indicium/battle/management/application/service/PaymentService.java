package hu.indicium.battle.management.application.service;

import hu.indicium.battle.management.application.commands.CreatePaymentCommand;
import hu.indicium.battle.management.domain.participant.payment.PaymentId;

public interface PaymentService {
    PaymentId createPayment(CreatePaymentCommand createPaymentCommand);
}
