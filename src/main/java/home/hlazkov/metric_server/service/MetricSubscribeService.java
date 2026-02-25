package home.hlazkov.metric_server.service;

import home.hlazkov.metric_server.service.mqtt.MqttProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.crt.mqtt5.Mqtt5Client;
import software.amazon.awssdk.crt.mqtt5.QOS;
import software.amazon.awssdk.crt.mqtt5.packets.SubscribePacket;

@Slf4j
@Service
@RequiredArgsConstructor
public class MetricSubscribeService {

    private final MqttProperties mqttProperties;
    private final Mqtt5Client client;

    @PostConstruct
    public void subscribe() {
        if (mqttProperties.getSubscribe().getEnable()) {
            try {
                log.info("Start mqtt client.");
                client.start();
                SubscribePacket packet = SubscribePacket.of(mqttProperties.getSubscribe().getTopic(), QOS.AT_LEAST_ONCE);
                var ask = client.subscribe(packet).get();
                log.info("SubAck received with reason code:{}", ask.getReasonCodes());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
