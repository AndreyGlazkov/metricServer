package home.hlazkov.metric_server.service.metrics;

import home.hlazkov.metric_server.db.dto.MetricDto;
import home.hlazkov.metric_server.service.mqtt.MetricMqttData;

import java.time.LocalDateTime;
import java.util.List;

public interface MetricService {

    MetricDto addMetric(MetricDto metric);
    List<MetricDto> addMetricList(List<MetricDto> metricList);

    List<MetricDto> findByDateTime(LocalDateTime fromTime, LocalDateTime toTime);
    List<MetricDto> findLast(Integer count);

    void saveMetricData(MetricMqttData data);
}
