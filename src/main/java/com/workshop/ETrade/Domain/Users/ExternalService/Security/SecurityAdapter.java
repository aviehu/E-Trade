package com.workshop.ETrade.Domain.Users.ExternalService.Security;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class SecurityAdapter implements ISecurity {
    private SecurityAdaptee securityAdaptee;

    public SecurityAdapter(SecurityAdaptee securityAdaptee) {
        this.securityAdaptee = securityAdaptee;
    }

    @Override
    public String encode(String password) {
        if(securityAdaptee == null) {
            Base64.Encoder encoder  = Base64.getEncoder();
            String passEncoded = encoder.encodeToString(password.getBytes(StandardCharsets.UTF_8));
            return passEncoded;

        }
        else
            return securityAdaptee.encode(password);
    }

    @Override
    public String decode(String password) {
        if(securityAdaptee == null){
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] passDecoded = decoder.decode(password);
            String ret = new String(passDecoded);
            return ret;
        }

        else
            return securityAdaptee.decode(password);
    }
    public boolean isExist(){
        return securityAdaptee != null;
    }
}
