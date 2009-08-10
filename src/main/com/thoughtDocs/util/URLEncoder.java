package com.thoughtDocs.util;

import java.io.UnsupportedEncodingException;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Aug 10, 2009
 * Time: 5:03:20 PM
 */
public class URLEncoder {
    private static final String CHAR_SET = "UTF-8";

    public static String encode(String str){
        String returnVal = str;
        try {
            returnVal = java.net.URLEncoder.encode(str, CHAR_SET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return returnVal;
    }


  


}
