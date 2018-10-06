package com.example.rohan.machinetest.api;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.rohan.machinetest.models.ErrorModel;
import com.example.rohan.machinetest.models.response.NotificationResponseModel;
import com.example.rohan.machinetest.util.Constants;

import io.reactivex.Observable;
import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;


public class ApiClient {
    private ApiService mApiService;

    public ApiClient() {

        RequestInterceptor requestInterceptor = request -> {
            request.addHeader("Content-Type", "application/x-www-form-urlencoded");
            request.addHeader("token", "CnPyRhel");
            request.addHeader("device_id","c809481e8a140b0b");
            request.addHeader("username", "7447804900");
            request.addHeader("accept", "application/json");
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.BASE_URL_API)
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setErrorHandler(new CustomErrorHandler())  // use error handler..
                .build();

        mApiService = restAdapter.create(ApiService.class);
    }

    public ApiService getApi() {
        return mApiService;
    }

    public interface ApiService {

        @FormUrlEncoded
        @POST("/get_notifications")
        Observable<NotificationResponseModel>
        getNotification(@Field("params") String request);
    }

    private static class CustomErrorHandler implements ErrorHandler {

        @Override
        public Throwable handleError(RetrofitError cause) {
            String errorDescription;

            if (cause.getKind() == RetrofitError.Kind.NETWORK) {
                errorDescription = "Network Error.";
                Log.e("Response", "code : " + cause.getResponse().getStatus());
            } else if (cause.getKind() == RetrofitError.Kind.HTTP) {
                Log.e("Response", "code : " + cause.getResponse().getStatus());
                if (cause.getResponse().getStatus() == 502) {
//                    LocalBroadcastManager.getInstance(AndroidApplication.getInstance()).sendBroadcast(new Intent("LOG_OUT"));
                }
                errorDescription = "Network Error.";
            } else {
                if (cause.getResponse() == null) {
                    errorDescription = "No Response";
                } else {

                    // Error message handling - return a simple error to Retrofit handlers..
                    try {
                        ErrorModel errorResponse = (ErrorModel) cause.getBodyAs(ErrorModel.class);
                        errorDescription = errorResponse.results.outcome;
                    } catch (Exception ex) {
                        try {
                            errorDescription = "" + cause.getResponse().getStatus();
                        } catch (Exception ex2) {
                            Log.e("ApiClient", "handleError: " + ex2.getLocalizedMessage());
                            errorDescription = "Something went wrong.";
                        }
                    }
                }
            }

            return new Exception(errorDescription);
        }
    }
}
