//package kafkav10;
//import kafka.producer.Partitioner;
//import kafka.utils.VerifiableProperties;
//
///**
// *
// *
// * @Note 先 Hash 再取模，得到分区索引
// */
//public class CustomerPartitioner implements Partitioner {
//
//    public CustomerPartitioner(VerifiableProperties props) {
//    }
//
//    public int partition(Object key, int numPartitions) {
//        int partition = 0;
//        String k = (String) key;
//        partition = Math.abs(k.hashCode()) % numPartitions;
//        return 3;
//    }
//
//}