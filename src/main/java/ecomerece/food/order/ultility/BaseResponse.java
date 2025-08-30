package ecomerece.food.order.ultility;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

public class BaseResponse {
    public static Map<String, Object> makeResponse(Object data, String status) throws Exception {
        return Map.of("status", status, "timeResponse", Date.from(Instant.now()), "data", data);
    }
}
