package hu.indicium.battle.management.application.query;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

public interface StatisticsQueryService {
    Map<String, Long> getRegistrationsByDate();
}
