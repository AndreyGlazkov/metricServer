package home.hlazkov.metric_server.service.mqtt;

import home.hlazkov.metric_server.service.metrics.MetricService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.crt.mqtt5.Mqtt5Client;
import software.amazon.awssdk.crt.mqtt5.Mqtt5ClientOptions;
import software.amazon.awssdk.crt.mqtt5.PublishReturn;
import software.amazon.awssdk.crt.mqtt5.packets.PublishPacket;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@RequiredArgsConstructor
public class MetricPublishEvents implements Mqtt5ClientOptions.PublishEvents {

    private final MetricMqttMapper mapper;
    private final MetricService metricService;

    @Override
    public void onMessageReceived(Mqtt5Client client, PublishReturn publishReturn) {
        PublishPacket publish = publishReturn.getPublishPacket();
        String payload = publish.getPayload() == null
                ? ""
                : new String(publish.getPayload(), StandardCharsets.UTF_8);

        try {
            var metricData = mapper.map(payload);
            log.info("Received: {}", metricData);
            metricService.saveMetricData(metricData);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
