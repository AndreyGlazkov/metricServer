package home.hlazkov.metric_server.service.mqtt;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.crt.CRT;
import software.amazon.awssdk.crt.mqtt5.*;

@Slf4j
public class MqttLifecycleEvents implements Mqtt5ClientOptions.LifecycleEvents {
    @Override
    public void onAttemptingConnect(Mqtt5Client client, OnAttemptingConnectReturn onAttemptingConnectReturn) {
        log.info("Lifecycle Connection Attempt. Connecting to endpoint: {} with client ID {}",
                client.getClientOptions().getHostName(),
                client.getClientOptions().getConnectOptions().getClientId());
    }

    @Override
    public void onConnectionSuccess(Mqtt5Client client, OnConnectionSuccessReturn onConnectionSuccessReturn) {
        log.info("Lifecycle Connection Success with reason code: {}",
                onConnectionSuccessReturn.getConnAckPacket().getReasonCode());
    }

    @Override
    public void onConnectionFailure(Mqtt5Client client, OnConnectionFailureReturn onConnectionFailureReturn) {
        log.info("Lifecycle Connection Failure with error code: {} : {} : {}\n",
                onConnectionFailureReturn.getErrorCode(),
                CRT.awsErrorName(onConnectionFailureReturn.getErrorCode()),
                CRT.awsErrorString(onConnectionFailureReturn.getErrorCode()));
    }

    @Override
    public void onDisconnection(Mqtt5Client client, OnDisconnectionReturn onDisconnectionReturn) {
        log.info("Lifecycle Stopped;");
    }

    @Override
    public void onStopped(Mqtt5Client client, OnStoppedReturn onStoppedReturn) {
        log.info("Lifecycle Stopped");
    }
}
