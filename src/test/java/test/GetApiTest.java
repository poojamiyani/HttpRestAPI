package test;


import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

import base.TestBase;
import client.RestClient;
import util.TestUtils;


public class GetApiTest extends TestBase{
     
	TestBase testBase ;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closebaleHttpResponse;

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {
	   
	   testBase = new TestBase();
	   serviceUrl = prop.getProperty("URL");
	   apiUrl = prop.getProperty("serviceURL");
		
	   url = serviceUrl + apiUrl;
	  
	   System.out.println("======="+url);
	}
	
	@Test(priority = 1)
	public void getTest() throws ClientProtocolException, IOException {
		restClient =new RestClient();
		 closebaleHttpResponse = restClient.get(url);
		 
		 restClient.get(url);
		 
		 int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		 System.out.println("status code---->"+statusCode);
		 
		 Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");
		 
		 String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		
		 JSONObject responseJson =new JSONObject(responseString);
		 System.out.println("response json from api ------>"+ responseJson);
		  
		 // get particualre value 
		String page = TestUtils.getValueByJPath(responseJson,"per_page");
		Assert.assertEquals(page,"6");
				 
		String total = TestUtils.getValueByJPath(responseJson,"total");
		Assert.assertEquals(total,"12");
		    
		 //get value by json array 
		String street_address = TestUtils.getValueByJPath(responseJson,"data[0]/email");
		String country = TestUtils.getValueByJPath(responseJson,"data[0]/first_name");
		String cityGet = TestUtils.getValueByJPath(responseJson,"data[0]/last_name");
	
		System.out.println(street_address +" ..................email.");
		System.out.println(cityGet +" ...................fname");
		System.out.println(cityGet +" ...................lname");
		
		 Header[] headersArray = closebaleHttpResponse.getAllHeaders();
		 HashMap<String ,String > allHeaders =new HashMap<String ,String >();
		    
		  for(Header header : headersArray) {
		    	allHeaders.put(header.getName(), header.getValue());
		    }
		    
		  System.out.println("Header array --->"+ allHeaders);
	}
	
	
	@Test(priority = 2)
	public void getApiTestWithHeader() throws ClientProtocolException, IOException {
		restClient =new RestClient();
		HashMap<String,String> headeHashMap = new HashMap<String,String>();
	    headeHashMap.put("Postman-Token", "<calculated when request is sent>");
		headeHashMap.put("Host", "<calculated when request is sent>");
		headeHashMap.put("Connection", "keep-alive");
		
		closebaleHttpResponse = restClient.get(url);
		 
		 restClient.get(url);
		 
		 int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		 System.out.println("status code---->"+statusCode);
		 
		 Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");
		 
		 String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		
		 JSONObject responseJson =new JSONObject(responseString);
		 System.out.println("response json from api ------>"+ responseJson);
		  
		 Header[] headersArray = closebaleHttpResponse.getAllHeaders();
		 HashMap<String ,String > allHeaders =new HashMap<String ,String >();
		    
		  for(Header header : headersArray) {
		    	allHeaders.put(header.getName(), header.getValue());
		    }
		    
		  System.out.println("Header array --->"+ allHeaders);
	}
	
}
