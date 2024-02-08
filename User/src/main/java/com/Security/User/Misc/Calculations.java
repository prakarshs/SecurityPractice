package com.Security.User.Misc;

import com.Security.User.Constants.UserConstants;
import lombok.Data;

import java.time.Instant;
import java.util.Date;
@Data
public class Calculations {


    public static Date calculateExpiryTime(Instant instant){
        return Date.from(instant.plusSeconds(UserConstants.SECONDS_TO_ADD));
    }
}
