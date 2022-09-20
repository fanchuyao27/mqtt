package sand.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * MQTT缓存
 */
public interface MqttCache {

    /** MQTT缓存 */
    Map<String, Object> DIC = new HashMap<>();
    /** MQTT缓存key：连接状态 */
    String KEY_CONNECTSTATUS = "connectStatus";
    /** MQTT缓存key：用户名 */
    String KEY_USERNAME = "userName";
    /** MQTT缓存key：密码 */
    String KEY_PASSWORD = "password";
    /** MQTT缓存key：网关ID */
    String KEY_GATEWAY_ID = "gatewayId";

    /** 已订阅主题集合 */
    Map<String, String> SUBSCRIBED_TOPIC_DIC = new HashMap<>();

}
