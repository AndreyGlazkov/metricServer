package home.hlazkov.metric_server.api;

import home.hlazkov.metric_server.api.model.PeriodRequest;
import home.hlazkov.metric_server.db.dto.MetricDto;
import home.hlazkov.metric_server.enums.MetricType;
import home.hlazkov.metric_server.service.metrics.MetricService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Mono;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ClientWebController {

    private final MetricService metricService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("period", new PeriodRequest());
        return "index";
    }

    @PostMapping("/metrics")
    public String getMetrics(final Model model, @ModelAttribute("period") PeriodRequest period) {
        var metrics = metricService.findByDateTime(period.getFromDate(), period.getToDate());
        model.addAttribute("metrics", metrics);
        model.addAttribute("chartData", convertToChartTemp(metrics));
        return "metrics";
    }

    private List<?> convertToChartTemp(List<MetricDto> metrics) {
        var result = new ArrayList<>();
        var header = List.of("Date", "Temp", "Humidity");
        result.add(header);
        var tempList = metrics.stream()
                .collect(Collectors.groupingBy(metric ->
                        metric.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm")))
                ).entrySet().stream()
                .sorted((o1, o2) -> o1.getKey().compareTo(o2.getKey()))
                .map(metricMap -> List.of(
                        metricMap.getKey(),
                        metricMap.getValue().stream()
                                .filter(dto -> dto.getType().equals(MetricType.TEMPERATURE))
                                .map(MetricDto::getValue).findFirst().orElse(0f),
                        metricMap.getValue().stream()
                                .filter(dto -> dto.getType().equals(MetricType.HUMIDITY))
                                .map(MetricDto::getValue).findFirst().orElse(0f)
                ))
                .toList();
        result.addAll(tempList);
        return result;
    }
}
