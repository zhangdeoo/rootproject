//package kafkav10;
//
//import java.util.List;
//import java.util.ArrayList;
//import java.util.Properties;
//import java.util.concurrent.TimeUnit;
//
//import org.apache.kafka.clients.producer.KafkaProducer;
//import org.apache.kafka.clients.producer.Producer;
//import org.apache.kafka.clients.producer.ProducerRecord;
//import org.apache.kafka.common.PartitionInfo;
///**
// * @author zhanglingjun@17paipai.cn 861724927
// * 2018/6/14 15:09
// */
//public class TestProducer {
//    public static void main(String[] args) {
//
//        Properties props = new Properties();
//        props.put("bootstrap.servers", "192.168.140.130:9092");
//        props.put("acks", "all");
//        props.put("retries",5);
//        props.put("batch.size", 16384);
//        props.put("linger.ms", 1);
//        props.put("buffer.memory", 33554432);
//        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        //生产者发送消息
//        String topic = "test5";
//        Producer<String, String> procuder = new KafkaProducer<String,String>(props);
//        for (int i = 1; i <= 10; i++) {
//        System.out.println("send message over.");
//            String value = "value_" + i;
//            ProducerRecord<String, String> msg = new ProducerRecord<String, String>(topic, value);
//            procuder.send(msg);
//        }
//        //列出topic的相关信息
//        List<PartitionInfo> partitions = new ArrayList<PartitionInfo>() ;
//        partitions = procuder.partitionsFor(topic);
//        for(PartitionInfo p:partitions)
//        {
//            System.out.println(p);
//        }
//
//        procuder.close(100,TimeUnit.MILLISECONDS);
//    }
//}
