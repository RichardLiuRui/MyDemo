package com.liurui.data;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by LiuRui on 2018/4/18.
 */

public class DefaultResponseDeserializer implements JsonDeserializer<DefaultResponse> {

    private final static String ERRORCODE = "errorCode";

    private final static String DATA = "data";

    private final static String ERRORMSG = "errorMsg";

    @Override
    public DefaultResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = (JsonObject) json;
        DefaultResponse res = new DefaultResponse();
        JsonElement codeElelment= jsonObject.get(ERRORCODE);
        if(codeElelment !=null && !(codeElelment instanceof JsonNull)){
            res.setErrorCode(codeElelment.getAsInt());
        }
        JsonElement objElement = jsonObject.get(DATA);
        if (objElement != null && !(objElement instanceof JsonNull)) {
            res.setData(objElement.toString());
        }
        JsonElement msgElement = jsonObject.get(ERRORMSG);
        if (msgElement != null && !(msgElement instanceof JsonNull)) {
            res.setErrorMsg(msgElement.toString());
        }
        return res;
    }
}
