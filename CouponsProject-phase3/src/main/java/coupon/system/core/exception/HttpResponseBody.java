package coupon.system.core.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HttpResponseBody {

    int status;
    String message;
    long timeStamp;
}
