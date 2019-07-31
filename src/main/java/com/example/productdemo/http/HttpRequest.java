package com.example.productdemo.http;

import com.alibaba.fastjson.JSONObject;
import sun.net.www.protocol.http.Handler;
import sun.plugin2.message.Message;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpRequest {

    private StringBuilder responce;

    public HttpRequest(){
        this.responce = new StringBuilder();
    }

    private void setPostRequestContent (HttpURLConnection conn, JSONObject main) throws IOException{
        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
        writer.write(main.toString());
        writer.flush();
        writer.close();
        os.close();
    }

    public void doPost(String api, JSONObject main){
        String CommonUrl = "https://sandbox-quickbooks.api.intuit.com";
        if (api == null){
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(CommonUrl + api);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setConnectTimeout(5 * 1000);
                    httpURLConnection.setReadTimeout(5 * 1000);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.setInstanceFollowRedirects(true);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                    setPostRequestContent(httpURLConnection, main);
                    httpURLConnection.connect();

                    int status = httpURLConnection.getResponseCode();
                    System.out.println("POST Response Code :  " + status);
                    System.out.println("POST Response Message : " + httpURLConnection.getResponseMessage());
                    if (status == HttpURLConnection.HTTP_CREATED) { //success
                        BufferedReader in = new BufferedReader(new InputStreamReader(
                                httpURLConnection.getInputStream()));
                        String inputLine;
                        StringBuffer response = new StringBuffer();
                        while ((inputLine = in .readLine()) != null) {
                            response.append(inputLine);
                        } in .close();
                        // print result
                        System.out.println(response.toString());
                    } else {
                        System.out.println("POST NOT WORKED");
                    }
                }
                catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
