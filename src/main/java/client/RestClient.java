package client;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;


import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class RestClient {

	//get method
	public CloseableHttpResponse get (String url) throws ClientProtocolException, IOException {
				
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(url); //http get request
			CloseableHttpResponse closebaleHttpResponse =  httpClient.execute(httpget); //hit the GET URL
			
			return closebaleHttpResponse;
		   
	}
	
	  //get method with header
	  public CloseableHttpResponse getWithHeader (String url , HashMap<String, String>headerhMap) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); //http get request
		
		for (Map.Entry<String, String>entry :headerhMap.entrySet()){
			httpget.addHeader(entry.getKey(),entry.getValue());
		}
		
		CloseableHttpResponse closebaleHttpResponse =  httpClient.execute(httpget); //hit the GET URL
		
		return closebaleHttpResponse;
	
       }
	
	
	 // post method
     public CloseableHttpResponse post(String url , String entityString , HashMap<String, String>headerhMap ) throws ClientProtocolException, IOException {
    	 
    	 CloseableHttpClient httpClient = HttpClients.createDefault();
 		 HttpPost httppost = new HttpPost(url); //http post request
 		 httppost.setEntity(new StringEntity(entityString)); // for payload
 		
 		for (Map.Entry<String, String>entry :headerhMap.entrySet()){
			httppost.addHeader(entry.getKey(),entry.getValue());
		}
		
		CloseableHttpResponse closebaleHttpResponse =  httpClient.execute(httppost); //hit the GET URL
		
		return closebaleHttpResponse;
 		
	 }
	
}
