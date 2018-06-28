//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.apache.kafka.common.TopicPartition;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class OffsetUtils {
//    private static final Logger logger = LoggerFactory.getLogger(OffsetUtils.class);
//
//    public static ConsumerRecords<Integer, String> RetryGetConsumerRecords(KafkaConsumer<Integer, String> consumer, TopicPartition topicPartition) {
//        ConsumerRecords<Integer, String> records = null;
//            records = consumer.poll(1000);
//
//        return records;
//    }
//
//}
