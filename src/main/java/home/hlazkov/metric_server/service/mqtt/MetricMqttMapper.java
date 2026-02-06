package home.hlazkov.metric_server.service.mqtt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tools.jackson.databind.json.JsonMapper;

@Component
@RequiredArgsConstructor
public class MetricMqttMapper {

    private final JsonMapper mapper;

    public MetricMqttData map(String payload) {
        return mapper.readValue(payload, MetricMqttData.class);
    }
}
