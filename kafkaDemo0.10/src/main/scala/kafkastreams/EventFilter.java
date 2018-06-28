//package kafkastreams;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.Serdes;
//import org.apache.kafka.streams.kstream.KStream;
//import org.apache.kafka.streams.kstream.KStreamBuilder;
//import org.apache.kafka.streams.KafkaStreams;
//import org.apache.kafka.streams.StreamsConfig;
//import org.apache.kafka.streams.kstream.Predicate;
//import java.util.Properties;
///**
// * @author zhanglingjun@17paipai.cn 861724927
// * 2018/6/13 17:20
// */
//public class EventFilter {
//    public static void main(String[] args) throws Exception {
//        Properties props = new Properties();
//        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "test-filter");
//        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "10.4.232.70:9091,10.4.232.77:2181");
//        props.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//        props.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//// setting offset reset to earliest so that we can re-run the demo code with the same pre-loaded data
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//        KStreamBuilder builder = new KStreamBuilder();
//        KStream source = builder.stream("test");
//
//        source.filter(new Predicate() {
//            @Override
//            public boolean test(String key, String value) {
//                return (value.split(",")[3]).equals("food");
//            }
//        }).to("food");
//        KafkaStreams streams = new KafkaStreams(builder, props);
//        streams.start();
//// usually the stream application would be running forever,
//// in this example we just let it run for some time and stop since the input data is finite.
//        Thread.sleep(5000L);
//        streams.close();
//    }
//}
