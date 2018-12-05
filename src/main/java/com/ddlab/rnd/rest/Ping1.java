package com.ddlab.rnd.rest;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Ping1 {

  public static HttpClient getHttpClient() {
    HttpClient httpClient = null;
    try {
      httpClient =
          HttpClients.custom()
              .setSSLContext(
                  new SSLContextBuilder()
                      .loadTrustMaterial(null, TrustAllStrategy.INSTANCE)
                      .build())
              .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
              .build();
    } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
      e.printStackTrace();
    }
    return httpClient;
  }

  public static void main(String[] args) {
    String uri = "https://api.github.com/user";
    HttpGet httpGet = new HttpGet(uri);
    String encoding =
        Base64.getEncoder().encodeToString("sambittechy@gmail.com:abcd@1234".getBytes());
    httpGet.setHeader("Authorization", "Basic " + encoding);

    HttpResponse response = null;
    try {
      HttpClient httpClient = getHttpClient();
      response = httpClient.execute(httpGet);
      int statusCode = response.getStatusLine().getStatusCode();
      System.out.println("Status Code : " + statusCode);
      HttpEntity entity = response.getEntity();
      String responseText = null;
      if (entity != null) {
        responseText = EntityUtils.toString(entity);
      }
      System.out.println(responseText);
      
      ObjectMapper mapper = new ObjectMapper();
      JsonNode actualObj = mapper.readTree(responseText);
      JsonNode jsonNode1 = actualObj.get("login");
      System.out.println(jsonNode1.asText());

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
