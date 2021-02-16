package hu.indicium.battle.management.application.query;

import hu.indicium.battle.management.domain.participant.Participant;
import hu.indicium.battle.management.domain.participant.ParticipantRepository;
import lombok.AllArgsConstructor;
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

    @Override
    public Map<String, Long> getRegistrationsByDate() {
        Collection<Participant> participants = participantRepository.getAllParticipants();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        return participants.stream()
                .collect(Collectors.groupingBy(x -> format.format(x.getCreatedAt()), Collectors.counting()));
    }
}
