package com.ddlab.rnd;

import java.io.IOException;
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

public class T1 {

	public static void main(String[] args) throws Exception {
		
//		SSLContext sc = SSLContext.getInstance("SSL");  
//        sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom()); 
//		
//		HttpClients.custom()
//        .setSSLSocketFactory((LayeredConnectionSocketFactory) sc.getSocketFactory())
//        .setDefaultCredentialsProvider(null)
//        .build();
		
		
		HttpClient httpClient = HttpClients
	            .custom()
	            .setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build())
	            .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
	            .build();
		
		String uri = "https://api.github.com/user";
		HttpGet httpGet = new HttpGet(uri);

	    String encoding =
	        Base64.getEncoder().encodeToString("sambittechy@gmail.com:abcd@1234".getBytes());
	    
	    
	    httpGet.setHeader("Authorization", "Basic " + encoding);
	    
	    
	    HttpResponse response = null;
	    try {
//	      response = httpclient.execute(httppost);
	      
	      response = httpClient.execute(httpGet);
	      
	      
	      
	      int statusCode = response.getStatusLine().getStatusCode();
	      System.out.println("Status Code : " + statusCode);
	      HttpEntity entity = response.getEntity();
	      if (entity != null) {
	        System.out.println(EntityUtils.toString(entity));
	      }

	    } catch (IOException e) {
	      e.printStackTrace();
	    } 
	    
//	    finally {
//	      try {
//	        response.close();
//	      } catch (IOException e) {
//	        e.printStackTrace();
//	      }
	      
	      
//	    }
	    
	    
	    
		

	}

}
