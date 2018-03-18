package com.varchar.whatwatch.ws;

import android.app.VoiceInteractor;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.varchar.whatwatch.model.Genre;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author reneg
 */

public class WebServiceHandler {

    private static String ALL_MOVIES_ENDPOINT = "https://c20xw6hcc4.execute-api.us-east-1.amazonaws.com/prod/getAllMovies";
    private static String ALL_SERIES_ENDPOINT = "https://c20xw6hcc4.execute-api.us-east-1.amazonaws.com/prod/getAllTvSeries";
    private static String SEARCH_ENDPOINT = "https://c20xw6hcc4.execute-api.us-east-1.amazonaws.com/prod/getCoincidences?text=";

    private static WebServiceHandler instance;
    private static RequestQueue requestQueue;

    private WebServiceHandler(Context context){
        requestQueue = Volley.newRequestQueue(context);
    };

    public static  WebServiceHandler getInstance(Context context){
        return instance == null ? new WebServiceHandler(context): instance;
    }

    private static void addToQueue(JsonRequest jsonObjectRequest) {
        requestQueue.add(jsonObjectRequest);
    }

    private static void requestVideoMedia(String url , Response.Listener<JSONObject> onSuccess, Response.ErrorListener   onError){
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest (
                        Request.Method.GET,
                        url,
                        null,
                        onSuccess,
                        onError);
        addToQueue(jsonObjectRequest);
    }

    public static void requestMovies(Response.Listener<JSONObject> onSuccess, Response.ErrorListener   onError){
        requestVideoMedia(ALL_MOVIES_ENDPOINT,onSuccess,onError);
    }

    public static void requestSeries(Response.Listener<JSONObject> onSuccess, Response.ErrorListener   onError){
        requestVideoMedia(ALL_SERIES_ENDPOINT,onSuccess,onError);

    }

    public static Request<JSONArray> searchVideoMedia(String queryString, Response.Listener<JSONArray> onSuccess, Response.ErrorListener   onError){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                SEARCH_ENDPOINT + queryString,
                null,
                onSuccess,
                onError
                );
        addToQueue(jsonArrayRequest);
        return jsonArrayRequest;
    }



    ///////////////////////////////////////////////////////////////



}
