package com.one.util;

import org.bson.*;

import java.util.Date;

public class BsonTool {
 
    /**
     * Java对象转BsonValue对象
     * @param obj
     * @return
     */
    public static BsonValue objectToBsonValue(Object obj){
        if (obj instanceof Integer){
            return new BsonInt32((Integer) obj);
        }
 
        if (obj instanceof String){
            return new BsonString((String) obj);
        }
 
        if (obj instanceof Long){
            return new BsonInt64((Long) obj);
        }
 
        if (obj instanceof Date){
            return new BsonDateTime(((Date) obj).getTime());
        }
        return new BsonNull();
    }
 
}
