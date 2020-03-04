package com.skepseis.demo.helper;

import com.skepseis.service.exception.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AuthorizationHelper {

    //Bearer sithivilisbbebt
    private static final String bearerToken = "sithivilisbbebt";

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationHelper.class);

    public void authorizeHeader(Map<String, String> headers){
        StringBuilder sb = new StringBuilder();
        try{
            headers.forEach((key, value) -> sb.append("  ").append(key).append(" = ").append(value).append("\n") );
            logger.info("\n  Headers\n  -------\n{}", sb.toString());
            String validate = headers.get("bearer").substring(7);

            if(!validate.equals(bearerToken)){
                throw new UnauthorizedException("Invalid Bearer Token");
            }
        }catch (Exception e){
            logger.error("Exception occurred",e);
            throw new UnauthorizedException("Invalid Bearer Token");
        }

    }

}
