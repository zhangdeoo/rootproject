///**
// * @author zhanglingjun@17paipai.cn 861724927
// * 2018/6/13 19:23
// */
//import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.apache.kafka.common.TopicPartition;
//
//import java.util.Collection;
//import java.util.Collections;
//import java.util.Properties;
//import java.util.concurrent.TimeUnit;
//public class RecordsByTimeStamp {
//    public static void main(String[] args) {
//        String boot_servers=args[0];
//        String topic=args[1];
//        String starttimestamp=args[2];
//        String stoptimestamp=args[3];
////        String starttimestamp="1528957260000";
////        String stoptimestamp="1528957320000";
//        Properties props = new Properties();
////        props.put("bootstrap.servers", "192.168.40.130:9092,192.168.40.131:9092,192.168.40.132:9092");
//        props.put("bootstrap.servers", boot_servers);
//        props.put("group.id", "getvaluebytimestamp");
//        props.put("enable.auto.commit", "true");
//        props.put("auto.commit.interval.ms", "1000");
//        props.put("session.timeout.ms", "30000");
////        props.put("auto.offset.reset", "latest");
//        props.put("key.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer");
//        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        KafkaConsumer<Integer, String> consumer = new KafkaConsumer(props);
////        consumer.subscribe(Collections.singletonList("testtimepartion3"), new ConsumerRebalanceListener() {
//        consumer.subscribe(Collections.singletonList(topic), new ConsumerRebalanceListener() {
//            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
//            }
//            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
//            }
//        });
//        OffsetFinder of= new OffsetFinder(consumer,new java.lang.Long(starttimestamp),new java.lang.Long(stoptimestamp));
//        of.Get();
//
//
//
////        while (true) {
////            ConsumerRecords<Integer, String> records = consumer.poll(100);
////            for (ConsumerRecord<Integer, String> record : records) {
////                System.out.printf("offset = %d, timestamp = %s, value = %s", record.offset(), record.timestamp(), record.value());
////            }
////
////            try {
////                TimeUnit.MICROSECONDS.sleep(100);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
////        }
//    }
//}
