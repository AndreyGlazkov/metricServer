package home.hlazkov.metric_server.api.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PeriodRequest {
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
}
