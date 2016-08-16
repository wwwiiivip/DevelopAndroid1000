package com.example.liwujingling;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Zhengqian on 2016/8/8.
 */
public class DownloadData {

    public static Bitmap getDatasFromInt(String urlStr){
        Bitmap bm = null;
        String jsonStr = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream inputStream = urlConnection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int len = 0;
                byte [] buf = new byte[1024];
                while(true){
                    len = inputStream.read(buf);
                    if(len == -1){
                        break;
                    }
                    baos.write(buf,0,len);
                }
                baos.flush();
                close(inputStream);
                close(baos);
                byte [] bytes = baos.toByteArray();
                bm = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bm;

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
