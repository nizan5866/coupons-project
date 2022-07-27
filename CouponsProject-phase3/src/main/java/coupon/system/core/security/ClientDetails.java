package coupon.system.core.security;

import lombok.Getter;

@Getter
public class ClientDetails {

    private int id;
    private String email;
    private ClientType clientType;

    public ClientDetails(int id, String email, ClientType clientType) {
        this.id = id;
        this.email = email;
        this.clientType = clientType;
    }
}
