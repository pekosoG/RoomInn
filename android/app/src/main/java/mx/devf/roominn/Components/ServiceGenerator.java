package mx.devf.roominn.Components;

import android.text.TextUtils;

import okhttp3.*;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Luis Rios on 08/11/2017.
 */

public class ServiceGenerator {

    public static final String API_BASE_URL = "https://thawing-sea-93287.herokuapp.com";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();


    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    public static <S> S createService(
            Class<S> serviceClass, final String authToken) {
        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor(authToken);

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);

                builder.client(httpClient.build());
                retrofit = builder.build();
            }
        }

        return retrofit.create(serviceClass);
    }
}