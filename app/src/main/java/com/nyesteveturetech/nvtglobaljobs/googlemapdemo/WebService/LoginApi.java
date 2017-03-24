package com.nyesteveturetech.nvtglobaljobs.googlemapdemo.WebService;





import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Itemns.LoginItems;
import com.nyesteveturetech.nvtglobaljobs.googlemapdemo.Itemns.SendServerItem;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface LoginApi{



//splash_screen
    @FormUrlEncoded
    @POST("index.php/")
    Call<LoginItems> authenticate
    (@Field("tag") String tag,
     @Field("username") String name,
     @Field("password") String pass

    );
    @FormUrlEncoded
    @POST("index.php/")
    Call<LoginItems> authenticate1
            (@Field("tag") String tag,
             @Field("lat") String lat,
             @Field("log") String log,
             @Field("distance") String dis,
             @Field("delay(minutes)") String reamtim,
             @Field("conductorId") String con,
             @Field("busId") String v,
             @Field("tripId") String vz

            );








}
