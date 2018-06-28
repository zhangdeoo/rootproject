//package myproject
//
//import java.util.Properties
//
//import org.apache.kafka.clients.producer._
//
///**
//  *
//  *
//  * @author zhanglingjun@17paipai.cn 861724927
//  *         2018/6/12 14:31
//  */
//object ProduceExample {
//  def main(args: Array[String]): Unit = {
//    val props = new Properties()
//    val TOPIC = "yangxin_test1"
//    props.put("bootstrap.servers", "192.168.40.130:9092")
//    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
//    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
//    props.put("metadata.broker.list", "192.168.40.130:9092")
//    props.put("group.id", "something")
//    val producer = new KafkaProducer[String, String](props)
//    for ( i <- 1 to 50){
//      val record = new ProducerRecord(TOPIC, "key", s"hello $i")
//      println(record)
//      producer.send(record)
//    }
//    val record = new ProducerRecord(TOPIC, "key", "the end " + new java.util.Date)
//    producer.send(record)
//    println(record)
//    producer.close()
//  }
//}
