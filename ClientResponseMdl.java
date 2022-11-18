package sand.model.mqtt;

import lombok.Data;
import com.google.gson.JsonObject;


@Data
public class ClientResponseMdl {
    private String errMsg;
    private ClientValueMdl value;
    private Integer code;

}
