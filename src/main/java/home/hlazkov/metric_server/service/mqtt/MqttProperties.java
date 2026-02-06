package home.hlazkov.metric_server.service.mqtt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(value = "mqtt")
public class MqttProperties {
    private String clientId;
    private String hostName;
    private String certificatePath;
    private String privateKeyPath;
}
