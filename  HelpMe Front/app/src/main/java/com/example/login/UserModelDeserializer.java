package com.example.login;

import com.example.login.Boundaris.UserBoundary;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class UserModelDeserializer implements JsonDeserializer<UserBoundary> {

    @Override
    public UserBoundary deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }
}
