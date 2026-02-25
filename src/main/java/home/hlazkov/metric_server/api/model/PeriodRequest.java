package home.hlazkov.metric_server.api.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PeriodRequest {
    @NotNull
    private LocalDateTime fromDate;

    @NotNull
    private LocalDateTime toDate;
}
