package com.philippe.app.service.kafka;

import com.philippe.app.service.avro.AvroCodecService;
import example.avro.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * A deserializer to convert bytes[] read from a Kafka topic into their equivalent Avro object.
 *
 * To be used for instance in the Kafka Spout config inside a Storm app as per the below code:
 * private KafkaSpoutConfig getKafkaSpoutConfig(final OurCustomConfiguration conf) {
 *     return KafkaSpoutConfig.builder(conf.getKafkaSpoutBrokerList(), conf.getUserTopic())
 *             .setProp(ConsumerConfig.GROUP_ID_CONFIG, STORM_GROUP_ID)
 *             .setFirstPollOffsetStrategy(UNCOMMITTED_EARLIEST)
 *             .setProp(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "com.philippe.app.service.kafka.UserDeserializer")
 *             .setProp(SECURITY_PROTOCOL, conf.getKafkaSpoutSecurityProtocol())
 *             .setRetry(new KafkaSpoutRetryExponentialBackoff(
 *                     KafkaSpoutRetryExponentialBackoff.TimeInterval.microSeconds(conf.getKafkaSpoutRetryServiceInitialDelay()),
 *                     KafkaSpoutRetryExponentialBackoff.TimeInterval.milliSeconds(conf.getKafkaSpoutRetryServiceDelayPeriod()),
 *                     conf.getKafkaSpoutRetryServiceMaxRetries(),
 *                     KafkaSpoutRetryExponentialBackoff.TimeInterval.seconds(conf.getKafkaSpoutRetryServiceMaxDelay())))
 *             .setPollTimeoutMs(conf.getKafkaSpoutTimeout())
 *            .build();
 * }
 */
@Slf4j
@Service
public class UserDeserializer implements Deserializer<User> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDeserializer.class);

    @Autowired
    private AvroCodecService<User> avroCodecServiceForUsers;

    @Override
    public void configure(Map<String, ?> map, boolean b) {
        // Do nothing because not in use for now.
    }

    @Override
    public User deserialize(String s, byte[] bytes) {
        User result = null;

        if (bytes != null) {
            try {
                result = avroCodecServiceForUsers.decode(bytes);
            } catch (InstantiationException | IllegalAccessException | IOException e) {
                LOGGER.warn("De-serialisation failed. Value read from Kafka is not valid.", e);
            }
        }

        return result;
    }

    @Override
    public void close() {
        // Do nothing because not in use for now.
    }
}
