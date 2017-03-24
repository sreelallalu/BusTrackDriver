package com.nyesteveturetech.nvtglobaljobs.googlemapdemo.WebService;

import android.util.Log;


/**
 * Created by lalu on 12/21/2016.
 */

public class RestBuilderPro {
    private static LoginApi service;
    public static   LoginApi getService(){
        service=ServiceGeneratorpro.createService(LoginApi.class);
        return service;
    }


}
