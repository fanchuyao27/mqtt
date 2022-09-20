package sand.service.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import sand.constant.MqttCache;
import sand.depot.business.mqtt.subscribe.AddDeviceRespHandler;
import sand.model.mqtt.AddDeviceRespMdl;
import sand.model.mqtt.ClientResponseMdl;
import sand.model.mqtt.ClientValueMdl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author fcy
 * @version 1.0.0
 * @description
 * @createTime 2022/9/15 14:26
 **/

public class Test {
    private static Logger logger = Logger.getLogger(Test.class);

    public static void main(String args[]){
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HashMap<String,String> param = new HashMap<>() ;
        // 登录
        param.put("username","fae1");
        param.put("password","123456");
        String responseStr = doPost(httpClient, "http://172.16.1.14:6009/loginCheck", param);
        System.out.println(responseStr);
        // 认证
        param.clear();
        param.put("gatewaySN","QZ-Test-Gateway");
        param.put("algId","0");
        param.put("checkID","QZ-Test-Gateway");
        param.put("devInfo","{\"name\": \"Gateway\",\n" +
                "\"description\": \"details\",\n" +
                "\"manufacturerId\": \"NARI\",\n" +
                "\"model\": \"TTU-5000\"\n" +
                "}\n");
        responseStr = doPost(httpClient, "http://172.16.1.14:6009/iot/devices/auth/gateway", param);

//        responseStr = "{\n" +
//                "    \"errMsg\": \"Success!\", \n" +
//                "    \"value\": {\n" +
//                "        \"channelList\": [\n" +
//                "            {\n" +
//                "                \"protocol\": \"mqtt\", \n" +
//                "                \"addr\": \"10.144.17.230:1885\", \n" +
//                "                \"channelId\": \"CHANNEL_MQTT_MAIN\"\n" +
//                "            }, \n" +
//                "            {\n" +
//                "                \"protocol\": \"https\", \n" +
//                "                \"addr\": \"http://10.144.24.90:23004/iot/files/4216495151125626892/up\", \n" +
//                "                \"channelId\": \"CHANNEL_HTTPS_FILE\"\n" +
//                "            }\n" +
//                "        ], \n" +
//                "        \"profile\": {\n" +
//                "            \"size\": \"46201\", \n" +
//                "            \"name\": \"Nari_PDZ833(2)(1).json\", \n" +
//                "            \"addr\": \"http://10.144.17.230:23004/iot/files/dfs/profile/2960D203-F2CE4C97-A311-6BB6111D2183.json\", \n" +
//                "            \"md5\": \"f0b67a60b000cf2553fcc41e3941c1e0\"\n" +
//                "        }, \n" +
//                "        \"tenancy\": 2160096, \n" +
//                "        \"gatewayId\": \"4217058101079048464\", \n" +
//                "        \"topicList\": [\n" +
//                "            {\n" +
//                "                \"topicId\": \"DEVICE_ADD\", \n" +
//                "                \"qos\": 0, \n" +
//                "                \"topic\": \"/v1/devices/4217058101079048464/topo/add\", \n" +
//                "                \"direction\": \"up\"\n" +
//                "            }, \n" +
//                "            {\n" +
//                "                \"topicId\": \"DEVICE_ADD_RESP\", \n" +
//                "                \"qos\": 0, \n" +
//                "                \"topic\": \"/v1/devices/4217058101079048464/topo/addResponse\", \n" +
//                "                \"direction\": \"down\"\n" +
//                "            }, \n" +
//                "            {\n" +
//                "                \"topicId\": \"DEVICE_DELETE\", \n" +
//                "                \"qos\": 0, \n" +
//                "                \"topic\": \"/v1/devices/4217058101079048464/topo/delete\", \n" +
//                "                \"direction\": \"up\"\n" +
//                "            }, \n" +
//                "            {\n" +
//                "                \"topicId\": \"DEVICE_DELETE_RESP\", \n" +
//                "                \"qos\": 0, \n" +
//                "                \"topic\": \"/v1/devices/4217058101079048464/topo/deleteResponse\", \n" +
//                "                \"direction\": \"down\"\n" +
//                "            }, \n" +
//                "            {\n" +
//                "                \"topicId\": \"DEVICE_UPDATE\", \n" +
//                "                \"qos\": 0, \n" +
//                "                \"topic\": \"/v1/devices/4217058101079048464/topo/update\", \n" +
//                "                \"direction\": \"up\"\n" +
//                "            }, \n" +
//                "            {\n" +
//                "                \"topicId\": \"DEVICE_UPDATE_RESP\", \n" +
//                "                \"qos\": 0, \n" +
//                "                \"topic\": \"/v1/devices/4217058101079048464/topo/updateResponse\", \n" +
//                "                \"direction\": \"down\"\n" +
//                "            }, \n" +
//                "            {\n" +
//                "                \"topicId\": \"CMD_BASE\", \n" +
//                "                \"qos\": 0, \n" +
//                "                \"topic\": \"/v1/devices/4217058101079048464/command\", \n" +
//                "                \"direction\": \"down\"\n" +
//                "            }, \n" +
//                "            {\n" +
//                "                \"topicId\": \"CMD_BASE_RESP\", \n" +
//                "                \"qos\": 0, \n" +
//                "                \"topic\": \"/v1/devices/4217058101079048464/commandResponse\", \n" +
//                "                \"direction\": \"up\"\n" +
//                "            }, \n" +
//                "            {\n" +
//                "                \"topicId\": \"DATA_BASE\", \n" +
//                "                \"qos\": 0, \n" +
//                "                \"topic\": \"/v1/devices/4217058101079048464/datas\", \n" +
//                "                \"direction\": \"up\"\n" +
//                "            }\n" +
//                "        ]\n" +
//                "    }, \n" +
//                "    \"code\": 2000\n" +
//                "}";
//        responseStr = responseStr.replaceAll("\n", "");

        Gson gson = new Gson();
        ClientResponseMdl crespMdl;
        try {
            crespMdl = gson.fromJson(responseStr, ClientResponseMdl.class);
        } catch (JsonSyntaxException e) {
            logger.error(String.format("传输失败！", ClientResponseMdl.class.getName(), responseStr));
            throw e;
        }
        ClientValueMdl vrespMdl = crespMdl.getValue();
        String gatewayId = vrespMdl.getGatewayId();
//        System.out.println(gatewayId);
        MqttCache.DIC.put(MqttCache.KEY_GATEWAY_ID,gatewayId);
        System.out.println("执行完成");
    }

    public static String doPost (CloseableHttpClient httpClient, String url, HashMap < String, String > param){
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建参数列表
            if (param != null) {
                List<BasicNameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }
}

