package test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import base.TestBase;
import client.RestClient;
import data.Users;

public class PostApiTest extends TestBase {

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
	
	@Test
	public void postApiTest() throws JsonGenerationException, JsonMappingException, IOException {
			restClient =new RestClient();
			HashMap<String,String> headerMap = new HashMap<String,String>();
			headerMap.put("Content-Type", "application/json");
	  			  		
	  		//jkson API
	  		ObjectMapper mapper = new ObjectMapper();
	  		Users users =new Users("morpheus","leader");
	  		
	  		//object mapper file 
	  		mapper.writeValue(new File("C:\\QA\\Selenium_Workspace\\RestApi\\src\\main\\java\\data\\users.json"), users);
	  		
	  		//object to jeson string
	    	String userJsonString= mapper.writeValueAsString(users);
	  	    System.out.println(userJsonString+"===========");
	  	    
	  	   closebaleHttpResponse = restClient.post(url, userJsonString, headerMap);
	  	   
	  	   //get status code 
	  	   int statusCode =  closebaleHttpResponse.getStatusLine().getStatusCode();
	  	   Assert.assertEquals(statusCode, TestBase.RESPONSE_STATUS_CODE_201);
	  	   System.out.println(statusCode+"statuscodeeeeeeeeeeeeeeeeeeeeee");
	  	   
	  	   //jsonstring 
	  	   String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(),"UTF-8");
	  	   JSONObject responseJson =new JSONObject(responseString);
	  	   System.out.println("-----------------<the response from post api" +responseJson);
	  	   
	  	   //json to java 
	  	   Users userResponse = mapper.readValue(responseString, Users.class);
	  	   System.out.println(userResponse+"userresponseeeeeeeeeeeee");
	  	   
	  	   System.out.println(userResponse.getId());
	  	   
	  	   Assert.assertTrue(users.getName().equals(userResponse.getName()));
	  	   Assert.assertTrue(users.getJob().equals(userResponse.getJob()));
	  	  

			System.out.println(userResponse.getId());
			System.out.println(userResponse.getName());
	}
	
	
}
