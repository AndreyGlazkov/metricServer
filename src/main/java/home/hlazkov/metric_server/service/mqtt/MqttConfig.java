package home.hlazkov.metric_server.service.mqtt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.crt.mqtt5.Mqtt5Client;
import software.amazon.awssdk.crt.mqtt5.Mqtt5ClientOptions;
import software.amazon.awssdk.iot.AwsIotMqtt5ClientBuilder;

@Configuration
public class MqttConfig {

    @Bean
    public Mqtt5Client mqtt5Client(MqttProperties properties, Mqtt5ClientOptions.PublishEvents events) {
        try (AwsIotMqtt5ClientBuilder builder = AwsIotMqtt5ClientBuilder.newDirectMqttBuilderWithMtlsFromPath(
                properties.getHostName(),
                properties.getCertificatePath(),
                properties.getPrivateKeyPath()
        )) {
            builder.withLifeCycleEvents(new MqttLifecycleEvents());
            builder.withClientId(properties.getClientId());
            builder.withPublishEvents(events);
            return builder.build();
        }
    }
}
