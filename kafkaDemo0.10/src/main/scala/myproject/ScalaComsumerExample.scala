//package myproject
//
//import java.util.{Collections, Properties}
//
//import org.apache.kafka.clients.consumer.KafkaConsumer
//
///**
//  *
//  *
//  * @author zhanglingjun@17paipai.cn 861724927
//  *         2018/6/12 14:27
//  */
//object ScalaComsumerExample {
//  def main(args: Array[String]): Unit = {
//    val topic = "app"
//    val brokers = "192.168.40.130:9092"
//    val props = new Properties()
//    props.put("bootstrap", brokers)
//    props.put("client.id", "ScalaProducerExample")
//    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
//    props.put("value.serializer", "org.apache.kafka.common.serialization.StringDeserializer")
//    val consumer = new KafkaConsumer[String, String](props)
//    consumer.subscribe(Collections.singleton(topic))
//    val records = consumer.poll(1000)
////    for (record <- records){
////      println(record)
////    }
//    consumer.close()
//  }
//}
