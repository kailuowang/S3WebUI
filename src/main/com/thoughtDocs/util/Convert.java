package com.thoughtDocs.util;

import com.thoughtDocs.exception.NotImplementedException;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Aug 2, 2009
 * Time: 6:36:44 PM
 */
public class Convert {

    public static <T> T valOf(Class<T> argType,  String str){
        if(argType.equals(String.class))
            return (T) str;
        else if (argType.equals(Integer.class))
            return (T) Integer.valueOf(str);
        else if (argType.equals(Boolean.class))
            return (T) Boolean.valueOf(str);
        else
            throw new NotImplementedException();

    }
}
