package sand.model.mqtt;

import lombok.Data;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

/**
 * @author fcy
 * @version 1.0.0
 * @description
 * @createTime 2022/9/16 18:04
 **/

@Data
public class ClientValueMdl {
    private JsonArray channelList;
    private JsonObject profile;
    private String tenancy;
    private String gatewayId;
    private JsonArray topicList;

}
