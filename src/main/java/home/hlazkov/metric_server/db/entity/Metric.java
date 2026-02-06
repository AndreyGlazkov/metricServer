package home.hlazkov.metric_server.db.entity;

import home.hlazkov.metric_server.enums.MetricType;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Data
public class Metric {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private MetricType type;
    private Float value;
    @CreatedDate
    private LocalDateTime createDate = LocalDateTime.now();
}
