//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.apache.kafka.common.TopicPartition;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.*;
//import java.util.function.Predicate;
//import java.util.stream.StreamSupport;
//
///***
// *
// *
// */
//public class OffsetFinder {
//    private static final Logger logger = LoggerFactory.getLogger(OffsetFinder.class);
//    private KafkaConsumer<Integer, String> consumer;
//
//    private long timestamp;
//    private long starttimestamp;
//    private long stoptimestamp;
//
//    private ArrayList<ConsumerRecords<Integer, String>> list = new ArrayList<>();
//
//    public OffsetFinder(KafkaConsumer<Integer, String> consumer, long timestamp) {
//        this.consumer = consumer;
//        this.timestamp = timestamp;
//    }
//    public OffsetFinder(KafkaConsumer<Integer, String> consumer, long starttimestamp, long stoptimestamp) {
//        this.consumer = consumer;
//        this.starttimestamp = starttimestamp;
//        this.stoptimestamp = stoptimestamp;
//    }
//
//
//    public void Get() {
//        consumer.poll(0);
//        Set<TopicPartition> assignments = consumer.assignment();
//        System.out.println(assignments);
//        int retrycount = 0;
//        //获取不到分区数据会重试3次
//        while (assignments.size() == 0 && retrycount++ < 3) { ;
//            assignments = consumer.assignment();
//        }
//        if (assignments.size() == 0)
//            throw new RuntimeException("assignments is 0!");
////        consumer.pause(assignments);
//
//        for (TopicPartition topicPartition : assignments) {
//            list.clear();
//            consumer.resume(Arrays.asList(topicPartition));
//            consumer.seekToBeginning(Arrays.asList(topicPartition));
//            getRecordsBySegments(topicPartition);
//        }
//        consumer.resume(assignments);
//
//    }
//
//    private void getRecordsBySegments(TopicPartition topicPartition) {
//        int num=0;//标记段里面最大时间和最小时间小于标准时间的次数
//        ConsumerRecords<Integer, String> record = OffsetUtils.RetryGetConsumerRecords(consumer, topicPartition);
//        OffsetTimeStamp offsetTimeStampstr = null;
//        offsetTimeStampstr=SeekRangeInitBegin(topicPartition,record);
//        OffsetTimeStamp offsetTimeStampstp = null;
//        offsetTimeStampstp=SeekRangeInitEnd(topicPartition,record);
//        Long dif_seconds=0L;
//        dif_seconds=(offsetTimeStampstp.getTimestamp()-offsetTimeStampstr.getTimestamp())/1000;
//        Long dif_standard=60*3L;
//        if(dif_seconds>dif_standard) num++;
//        while (record.count()>0&&num<=10){
//             list.add(record);
//             record = OffsetUtils.RetryGetConsumerRecords(consumer, topicPartition);
//             offsetTimeStampstr = SeekRangeInitBegin(topicPartition,record);
//             offsetTimeStampstp = SeekRangeInitEnd(topicPartition,record);
//             if(offsetTimeStampstp.getTimestamp()==offsetTimeStampstr.getTimestamp()&&offsetTimeStampstr.getTimestamp()==(-1)) {continue;};
//             //segment内的最大和最小的时间差
//             dif_seconds=(offsetTimeStampstp.getTimestamp()-offsetTimeStampstr.getTimestamp())/1000;
//             if(dif_seconds>dif_standard) num++;
//        }
//        try {
//          for( ConsumerRecords<Integer, String> records : list){
//            OffsetTimeStamp offsetTimeStampstart = SeekRangeInitBegin(topicPartition,records);
//            OffsetTimeStamp offsetTimeStampstop = SeekRangeInitEnd(topicPartition,records);
//            String starttimestr=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(offsetTimeStampstart.getTimestamp()));
//            String stoptimestr=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(offsetTimeStampstop.getTimestamp()));
//            System.out.println(topicPartition+":"+starttimestr+"(offset:"+offsetTimeStampstart.getOffset()+")->"+stoptimestr+"(offset:"+offsetTimeStampstop.getOffset()+")");
//            if(!(starttimestamp>offsetTimeStampstop.getTimestamp() || stoptimestamp<offsetTimeStampstart.getTimestamp())){
//                FindMiddle(starttimestamp, stoptimestamp,records);
//            }
//        }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            consumer.pause(Arrays.asList(topicPartition));
//        }
//    }
//
//    /**
//     * 查找中间部分
//     *
//     * @param
//     * @param
//     * @return
//     */
//    private void FindMiddle( long starttimestamp, long stoptimestamp,ConsumerRecords<Integer, String> records) {
////        if (begin.getOffset() > end.getOffset()) {
////            throw new RuntimeException("end > begin");
////        }
////        long offset = end.getOffset();
////        consumer.seek(begin.getTopicPartition(), 0);
//
//        StreamSupport.stream(records.spliterator(), true)
//        .filter(s->s.timestamp()>=starttimestamp && s.timestamp()<=stoptimestamp)
//        .forEach(s->System.out.println(s.toString()));
//
//    }
//
//
//    private OffsetTimeStamp SeekRangeInitBegin(TopicPartition topicPartition,ConsumerRecords<Integer, String> records) {
//        consumer.seekToBeginning(Arrays.asList(topicPartition));
//        if (records.count() == 0) {
//            System.out.println(topicPartition + " SeekRangeInitBegin Record is 0!");
//            return new OffsetTimeStamp();
//        }
//        ConsumerRecord<Integer, String> stringStringConsumerRecord = StreamSupport.stream(records.spliterator(), false).findFirst().get();
//        OffsetTimeStamp end = new OffsetTimeStamp();
//        end.setOffset(stringStringConsumerRecord.offset());
//        end.setTimestamp(stringStringConsumerRecord.timestamp());
//        end.setTopicPartition(topicPartition);
//        end.setPayload(stringStringConsumerRecord.value());
//        return end;
//    }
//
//
//    private OffsetTimeStamp SeekRangeInitEnd(TopicPartition topicPartition,ConsumerRecords<Integer, String> records) {
//
//        consumer.seekToEnd(Arrays.asList(topicPartition));
//
//        long position = consumer.position(topicPartition);
//
//        consumer.seek(topicPartition, position - 1);
//        if (records.count() == 0) {
//            throw new RuntimeException(topicPartition + " SeekRangeInitEnd Record is 0!");
//        }
//        ConsumerRecord<Integer, String> max = StreamSupport.stream(records.spliterator(), false).max(Comparator.comparingLong(ConsumerRecord::timestamp)).get();
//        OffsetTimeStamp end = new OffsetTimeStamp();
//        end.setOffset(max.offset());
//        end.setTimestamp(max.timestamp());
//        end.setTopicPartition(topicPartition);
//        end.setPayload(max.value());
//        return end;
//    }
//
//}
