package com.example.liwujingling.httpUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ZhengQian on 2016/8/16.
 */
public class HttpUtils {
    public static String getJsonStr(String urlStr,String postStr){
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            if(postStr!=null){
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);
                OutputStream outputStream = urlConnection.getOutputStream();
                outputStream.write(postStr.getBytes());
                outputStream.flush();
            }
            urlConnection.connect();
            if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream inputStream = urlConnection.getInputStream();
                int len = 0;
                byte [] buf = new byte[1024];
                while(true){
                    len = inputStream.read(buf);
                    if(len == -1){
                        break;
                    }
                    sb.append(new String(buf,0,len));
                }
                close(inputStream);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();

    }

    private static void close(Closeable closeable){
        if(closeable != null){
            try {
                closeable.close();
                closeable = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
