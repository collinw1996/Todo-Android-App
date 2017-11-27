package edu.towson.cosc431.collinwoodruff.todosapp;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Collin on 11/27/2017.
 */

public class JsonDownloader {

    static String API = "https://jsonplaceholder.typicode.com";

    public static String downloadJson() {
        URL url = null;
        try {
            url = new URL(API + "/todos");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            InputStream stream = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
            char[] bytes = new char[1024];
            StringBuilder builder = new StringBuilder();
            int byteCount = reader.read(bytes);
            while(byteCount > 0) {
                builder.append(bytes, 0, byteCount);
                byteCount = reader.read(bytes);
            }
            return builder.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
