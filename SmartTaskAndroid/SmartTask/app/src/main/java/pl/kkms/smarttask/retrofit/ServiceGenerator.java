package pl.kkms.smarttask.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Damian on 2017-04-20.
 */

public class ServiceGenerator {

    private static final String BASE_URL = "http://192.168.43.66:8183/";

    private static OkHttpClient.Builder getClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(loggingInterceptor);
        return httpClient;
    }

    private static ServiceGenerator instance;

    private static Retrofit retrofit;

    private ServiceGenerator() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getClient().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static SmartTaskService getSmartTaskService(){
        if (instance == null) {
            instance = new ServiceGenerator();
        }
        return instance.retrofit.create(SmartTaskService.class);
    }
}
