package myproject

import java.util
import java.util.Properties

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.slf4j.{Logger, LoggerFactory}

import scala.collection.JavaConverters._

/**
  *
  *
  * @author zhanglingjun@17paipai.cn 861724927
  *         2018/6/12 14:32
  */
object ConsumerExample {
  val logger: Logger =LoggerFactory.getLogger(this.getClass)
  def main(args: Array[String]): Unit = {
    val TOPIC = "testroll"
    val props = new Properties()
    props.put("bootstrap.servers", "192.168.40.130:9092")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("group.id", "something")
    val consumer = new KafkaConsumer[String, String](props)
    consumer.subscribe(util.Collections.singletonList(TOPIC))
    while(true) {
      val records = consumer.poll(100)
      for (record <- records.asScala) {
        println(record.value())
//        logger.info(record.value())
      }
    }
  }
}
