package com.example.tindergathering.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;
import static org.apache.http.conn.ssl.SSLSocketFactory.SSL;

public class Network {
    private static final String TAG = "Registration";
    private Context context;
    public Network(Context context){
        this.context = context;
    }

    // Récupère le nom du réseau internet sur lequel le téléphone est connecté
    private String getNetworkName() {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        NetworkInfo networkInfoWifi = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        NetworkInfo networkInfoMobile = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (networkInfo != null && networkInfo.isConnected()) {
            return networkInfo.getTypeName();
        }

        return "";
    }

    // Affichage
    public void checkNetwork(View v) {

        String rtmp = getNetworkName();
        String reseau = rtmp == "" ? "Vous n'êtes pas connecté"
                : "Vous êtes connecté au réseau : " + rtmp;

        Toast.makeText(context.getApplicationContext(), reseau, Toast.LENGTH_SHORT)
                .show();
    }


    final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    /**
     * Trust every server - dont check for any certificate
     */
    private static void trustAllHosts() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[] {};
            }

            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }
        } };

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection
                    .setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Requête HTTPS Get vers le serveur via l'url fourni en parametre
    // Récupère les données de la requête dans un String
    public String getRequest(String myurl) throws IOException {

        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;
        boolean connected = false;

        try {

            URL url = new URL(myurl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");

            // Add AuthToken un param
            SharedPreferences sharedPref = context.getSharedPreferences("com.example.tindergathering", Context.MODE_PRIVATE);
            String authToken = sharedPref.getString("AuthToken", null);
            if(authToken != null)
                conn.setRequestProperty("Authorization", "Bearer "+authToken);
            conn.setDoInput(true);
            // Starts the query
            conn.connect();

            connected = true;
            int responseCode = conn.getResponseCode();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                is = conn.getInputStream();

                int response = conn.getResponseCode();
                Log.d("HTTP response", "The response is: " + response);

                // Convert the InputStream into a string
                String contentAsString = readIt(is);
                String contentAsString2 = readIt(is, len);
                return contentAsString;
            }

            conn.disconnect();

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } catch(Exception e) {
            Log.v(TAG, e.toString());
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
        } finally {
            if (is != null) {
                is.close();
            }

            if(!connected) {
                return "{ \"code\": 500,  \"message\": \"Connection error.\" }";
            }
        }

        return null;

    }

    // Requete HTTPS Post vers le serveur via l'url fourni en parametre
    // Le deuxième parametre est un String qui correspond au Json que l'on va envoyer a l'API
    public String postRequest(String myurl, String attributes) throws IOException {

        OutputStream os = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;

        try {


            URL url = new URL(myurl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");

            // Add AuthToken un param
            SharedPreferences sharedPref = context.getSharedPreferences("com.example.tindergathering", Context.MODE_PRIVATE);
            String authToken = sharedPref.getString("AuthToken", null);
            if(authToken != null)
                conn.setRequestProperty("Authorization", "Bearer "+authToken);

            os = conn.getOutputStream();
            os.write(attributes.getBytes("UTF-8"));
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            Log.v("Post Request", "Output from Server ....");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();

        } finally {
            if (os != null) {
                os.close();
            }
        }

        return null;
    }

    // Requete HTTPS Put vers le serveur via l'url fourni en parametre
    // Le deuxième parametre est un String qui correspond au Json que l'on va envoyer a l'API
    public String putRequest(String myurl, String attributes) throws IOException {

        OutputStreamWriter osw = null;
        HttpsURLConnection conn = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;

        try {
            URL url = new URL(myurl);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("PUT");

            // Add AuthToken un param
            SharedPreferences sharedPref = context.getSharedPreferences("com.example.tindergathering", Context.MODE_PRIVATE);
            String authToken = sharedPref.getString("AuthToken", null);
            if(authToken != null)
                conn.setRequestProperty("Authorization", "Bearer "+authToken);

            osw = new OutputStreamWriter(conn.getOutputStream());
            osw.write(attributes);
            osw.flush();


        } finally {
            if (osw != null) {
                osw.close();
                System.err.println(conn.getResponseCode());
                conn.disconnect();
            }
        }

        return null;
    }

    public String readIt(InputStream stream) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(stream));
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
        }
        return out.toString();
    }

    // Reads an InputStream and converts it to a String.
    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }

}
