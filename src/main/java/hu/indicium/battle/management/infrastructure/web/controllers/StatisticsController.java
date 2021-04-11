package hu.indicium.battle.management.infrastructure.web.controllers;

import hu.indicium.battle.management.application.query.StatisticsQueryService;
import hu.indicium.battle.management.infrastructure.util.BaseUrl;
import hu.indicium.battle.management.infrastructure.util.Response;
import hu.indicium.battle.management.infrastructure.util.ResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(BaseUrl.API_V1 + "/statistics")
public class StatisticsController {
    private final StatisticsQueryService statisticsQueryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response<Map<String, Long>> getRegistrationsByDate() {
        return ResponseBuilder.ok()
                .data(statisticsQueryService.getRegistrationsByDate())
                .build();
    }

    @GetMapping("/size")
    @ResponseStatus(HttpStatus.OK)
    public Response<Map<String, Integer>> getRegistrationsByAssociation() {
        return ResponseBuilder.ok()
                .data(statisticsQueryService.getRegistrationsByAssociation())
                .build();
    }
}
