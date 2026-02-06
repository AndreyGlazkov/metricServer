package home.hlazkov.metric_server.service.mqtt;

import lombok.Data;

@Data
public class MetricMqttData {
    private Float temperature;
    private Float humidity;
}
