package hu.indicium.battle.management.application.query;

import hu.indicium.battle.management.domain.association.Association;
import hu.indicium.battle.management.domain.association.AssociationRepository;
import hu.indicium.battle.management.domain.participant.Participant;
import hu.indicium.battle.management.domain.participant.ParticipantRepository;
import hu.indicium.battle.management.domain.team.Team;
import hu.indicium.battle.management.domain.team.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StatisticsQueryServiceImpl implements StatisticsQueryService {

    private final ParticipantRepository participantRepository;

    private final TeamRepository teamRepository;

    private final AssociationRepository associationRepository;

    @Override
    @PreAuthorize("hasPermission('manage-association')")
    public Map<String, Long> getRegistrationsByDate() {
        Collection<Participant> participants = participantRepository.getAllParticipants();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        return participants.stream()
                .collect(Collectors.groupingBy(x -> format.format(x.getCreatedAt()), Collectors.counting()));
    }

    @Override
    @PreAuthorize("hasPermission('manage-association')")
    public Map<String, Integer> getRegistrationsByAssociation() {
        Collection<Association> associations = associationRepository.getAllAssociations();
        Map<String, Integer> result = new HashMap<>();
        for (Association association : associations) {
            result.put(association.getName(), association.getSize());
        }
        return result;
    }
}
