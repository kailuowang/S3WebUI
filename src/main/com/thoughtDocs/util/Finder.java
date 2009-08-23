package com.thoughtDocs.util;

import com.thoughtDocs.model.Item;

import java.util.Collection;

/**
 *
 */
public class Finder {
    public static Item find(Collection items , String key ){
        for(Object item : items){
            if(((Item)item).getKey().equals(key))
                return (Item)item;
        }
        return null;
    }
}
