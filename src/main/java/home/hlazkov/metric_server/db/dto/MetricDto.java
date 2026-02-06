package home.hlazkov.metric_server.db.dto;

import home.hlazkov.metric_server.enums.MetricType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MetricDto {
    private Long id;
    private MetricType type;
    private Float value;
    private LocalDateTime createDate;
}
