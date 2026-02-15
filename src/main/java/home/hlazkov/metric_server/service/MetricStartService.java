package home.hlazkov.metric_server.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.crt.mqtt5.Mqtt5Client;
import software.amazon.awssdk.crt.mqtt5.QOS;
import software.amazon.awssdk.crt.mqtt5.packets.SubscribePacket;

@Slf4j
@Component
@RequiredArgsConstructor
public class MetricStartService implements CommandLineRunner {

    private final Mqtt5Client client;

    @Override
    public void run(String... args) throws Exception {

        client.start();

        SubscribePacket packet = SubscribePacket.of("flat/meteo", QOS.AT_LEAST_ONCE);
        var ask = client.subscribe(packet).get();
        log.info("SubAck received with reason code:{}", ask.getReasonCodes());
    }
}
