package hu.indicium.battle.management.application.service;

import hu.indicium.battle.management.application.commands.CreateParticipantCommand;
import hu.indicium.battle.management.domain.participant.ParticipantId;

public interface ParticipantService {
    ParticipantId createParticipant(CreateParticipantCommand createParticipantCommand);
}
