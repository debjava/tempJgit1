package com.ddlab.rnd;

import org.apache.http.HttpEntity;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Test3 {

  public static SSLConnectionSocketFactory getSelfSignedCertificateConnection()
      throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
    SSLContext sslContext =
        SSLContextBuilder.create().loadTrustMaterial(new TrustSelfSignedStrategy()).build();
    HostnameVerifier allowAllHosts = new NoopHostnameVerifier();
    SSLConnectionSocketFactory connectionFactory =
        new SSLConnectionSocketFactory(sslContext, allowAllHosts);
    return connectionFactory;
  }

  public static CloseableHttpClient getClosableHttpClient(
      SSLConnectionSocketFactory connectionFactory, CredentialsProvider provider) {
    return HttpClients.custom()
        .setSSLSocketFactory(connectionFactory)
        .setDefaultCredentialsProvider(provider)
        .build();
  }

  public static void main(String[] args) throws Exception {
    String uri = "https://api.github.com/user";
    SSLConnectionSocketFactory connectionFactory = getSelfSignedCertificateConnection();
    CloseableHttpClient httpclient = getClosableHttpClient(connectionFactory, null);
//    HttpPost httppost = new HttpPost(uri);
    
    HttpGet httpGet = new HttpGet(uri);

    String encoding =
        Base64.getEncoder().encodeToString("sambittechy@gmail.com:abcd@1234".getBytes());
    
    
    httpGet.setHeader("Authorization", "Basic " + encoding);
    
    
    
//    httppost.setHeader("Authorization", "Basic " + encoding);

    CloseableHttpResponse response = null;
    try {
//      response = httpclient.execute(httppost);
      
      response = httpclient.execute(httpGet);
      
      
      int statusCode = response.getStatusLine().getStatusCode();
      System.out.println("Status Code : " + statusCode);
      HttpEntity entity = response.getEntity();
      if (entity != null) {
        System.out.println(EntityUtils.toString(entity));
      }

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        response.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
