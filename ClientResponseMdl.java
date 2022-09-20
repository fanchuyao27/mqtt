package sand.model.mqtt;

import lombok.Data;
import com.google.gson.JsonObject;

/**
 * @author fcy
 * @version 1.0.0
 * @description
 * @createTime 2022/9/16 18:01
 **/

@Data
public class ClientResponseMdl {
    private String errMsg;
    private ClientValueMdl value;
    private Integer code;

}