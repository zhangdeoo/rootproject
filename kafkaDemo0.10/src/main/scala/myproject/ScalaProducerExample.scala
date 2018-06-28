//package myproject
//
//import java.util.Properties
//
//import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
//
///**
//  * @author zhanglingjun@17paipai.cn 861724927
//  */
//object ScalaProducerExample {
//  def main(args: Array[String]): Unit = {
//    val topic = "app"
//    val brokers = "192.168.40.130:9092"
//    val props = new Properties()
//    props.put("bootstrap", brokers)
//    props.put("client.id", "ScalaProducerExample")
//    props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer")
//    props.put("value.serializer", "org.apache.kafka.common.serialization.StringDeserializer")
//
//    val producer = new KafkaProducer[String, String](props)
//    val t = System.currentTimeMillis()
//    val msg = "hello, I'm test message!"
//    val record = new ProducerRecord[String, String](topic, "key", msg)
//    producer.send(record)
//    producer.close()
//  }
//}
