package com.example.demo.helper;

import com.example.demo.exception.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;


public class AuthorizationHelper {

    //Bearer sithivilisbbebt
    private static final String bearerToken = "sithivilisbbebt";

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationHelper.class);

    public void authorizeHeader(Map<String, String> headers){
        StringBuilder sb = new StringBuilder();
        try{
            headers.forEach((key, value) -> sb.append("  ").append(key).append(" = ").append(value).append("\n") );
            logger.info("\n  Headers\n  -------\n{}", sb.toString());
            String validate = headers.get("authorization").substring(7);

            if(!validate.equals(bearerToken)){
                throw new UnauthorizedException("Invalid Bearer Token");
            }
        }catch (Exception e){
            logger.error("Exception occurred",e);
            throw new UnauthorizedException("Invalid Bearer Token");
        }

    }

}
