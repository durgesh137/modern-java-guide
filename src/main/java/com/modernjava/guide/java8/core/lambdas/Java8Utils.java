package com.modernjava.guide.java8.core.lambdas;

import org.apache.commons.lang3.StringUtils;

public class Java8Utils {
    public static int parseNumber(String input){
        //here null check and 0 length check is done
        if(StringUtils.isEmpty(input)){
            return 0;
        }
        try{
            input = input.trim();// "123 " results in 0
            return Integer.parseInt(input);
        }catch (NumberFormatException e){
            return 0;
        }
    }

}
