package com.example.login.Api;

import android.os.AsyncTask;
import android.util.Log;

import com.example.login.Boundaris.UserBoundary;

import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class UserApi  extends AsyncTask<String, Void, Object> {


    private RestTemplate restTemplate;


    public UserApi() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

    }


    @Override
    protected Object doInBackground(String... params) {


        /*
         * params[0] - function name
         *
         * params[1] - userEmail
         * params[2] - password

         * params[3] - urlVariables DOMAIN
         * params[4] - urlVariables stringEmail
         * params[5] - urlVariables selectedRole
         * params[6] - urlVariables userName
         * params[7] - urlVariables avatar
         */

        try {
            switch (params[0]) {
                case "login":
                {

                    final String uri = "10.0.0.12:2345/user/login/acount/{userEmail}/{password}";
                    Map<String, String> pathParams = new HashMap<String, String>();

                    pathParams.put("userEmail", params[1]);
                    pathParams.put("password", params[2]);


//                    RestTemplate restTemplate = new RestTemplate();
                    UserBoundary result = restTemplate.getForObject(uri, UserBoundary.class, pathParams);

                    System.err.println(result);
                    Log.d("USER-LOGIN", result.toString());

                    return result;
                }

                case "reg":
//                    return restTemplate.postForObject(params[2], request, UserBoundary.class, urlVariables);
                    return null;

            }
        }  catch (Exception e) {
            Log.e("ExceptionUserTasks", e.getMessage());
            return e.getMessage();
        }
        return null;
























//        String[] urlVariables = new String[params.length - 3];
//        for (int i = 0; i < urlVariables.length; i++) {
//            urlVariables[i] = params[i + 3];
//        }
//
//        Object request = null;
//        switch (params[0]) {
//            case "signUp":
//                if (params[5].matches("PLAYER"))
//                    request = new NewUserForm(params[4], UserRole.PLAYER, params[6], params[7]);
//                else
//                    request = new NewUserForm(params[4], UserRole.MANAGER, params[6], params[7]);
//                break;
//            case "update":
//                if (params[5].matches("PLAYER"))
//                    request = new UserBoundary(null, UserRole.PLAYER, params[6], params[7]);
//                else
//                    request = new UserBoundary(null, UserRole.MANAGER, params[6], params[7]);
//                break;
//        }
//
//        try {
//            switch (params[1]) {
//                case "get":
//                    return restTemplate.getForObject(params[2], UserBoundary.class, urlVariables);
//                case "post":
//                    return restTemplate.postForObject(params[2], request, UserBoundary.class, urlVariables);
//                case "put":
//                    restTemplate.put(params[2], request, urlVariables);
//                    return "put result succeeded";
//            }
//        }  catch (Exception e) {
//            Log.e("ExceptionUserTasks", e.getMessage());
//            return e.getMessage();
//        }
//        return null;


    }





}

