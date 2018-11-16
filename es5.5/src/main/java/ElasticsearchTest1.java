package myproject;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * @author zhanglingjun@17paipai.cn 861724927
 * 2018/6/28 15:55
 */
public class ElasticsearchTest1 {


//    public final static String HOST = "192.168.40.130";
    public final static String HOST = "10.33.80.109";

    public final static int PORT = 9300;//http请求的端口是9200，客户端是9300
    private TransportClient client = null;
    /**
     * 测试Elasticsearch客户端连接
     * @return void
     * @throws UnknownHostException
     */
    @SuppressWarnings("resource")
    @Before
    public void getConnect() throws UnknownHostException {
        Settings settings = Settings.builder()
//                .put("cluster.name", "estestcluster").build();
                .put("cluster.name", "bigdata_group").build();
//        client = new PreBuiltTransportClient(Settings.EMPTY)
        client = new PreBuiltTransportClient(settings)
                .addTransportAddresses(new TransportAddress(InetAddress.getByName(HOST),PORT));
//                .addTransportAddresses(new InetSocketTransportAddress(InetAddress.getByName(HOST),PORT));
    }
    /**
     * 关闭连接
     * @Title: closeConnect
     * @author sunt
     * @date 2017年11月23日
     * @return void
     */
    @After
    public void closeConnect() {
        if(null != client) {
            client.close();
        }
    }
    /**
     * 创建索引库
     * 需求:创建一个索引库为：msg消息队列,类型为：tweet,id为1
     * 索引库的名称必须为小写
     * @throws
     */
    @Test
    public void addIndex1() throws IOException {
        IndexResponse response = client.prepareIndex("msg", "tweet", "1").setSource(XContentFactory.jsonBuilder()
                .startObject().field("userName", "张三")
                .field("sendDate", new Date())
                .field("msg", "你好李四")
                .endObject()).get();
        System.out.println("索引名称:" + response.getIndex() + "\n类型:" + response.getType()
                + "\n文档ID:" + response.getId() + "\n当前实例状态:" + response.status());
    }





}
