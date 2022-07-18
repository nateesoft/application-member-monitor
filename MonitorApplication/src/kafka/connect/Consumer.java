package kafka.connect;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

/**
 *
 * @author nateesun
 */
public class Consumer {

    public static void main(String[] args) {
        final String kafkaTopic = "quickstart-events";
        final String bootstrapServers = "172.17.60.129:9092";
//        final String bootstrapServers = "192.168.100.101:9092";
        final String consumerGroupId = "test-consumer-group";

        // properties
        Properties p = new Properties();
        p.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        p.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        p.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        p.setProperty(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId);
        p.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // create consumer
        final KafkaConsumer<String, String> consumer = new KafkaConsumer<>(p);

        // subscriber
        consumer.subscribe(Arrays.asList(kafkaTopic));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord record : records) {
                System.out.println("Received new record: \n"
                        + "Key: " + record.key() + ", "
                        + "Value: " + record.value() + ", "
                        + "Topic: " + record.topic());
            }
        }
    }
}
