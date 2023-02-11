package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class TestBase {

    public static int  RESPONSE_STATUS_CODE_201 = 201;
	public int  RESPONSE_STATUS_CODE_200 = 200;
    public int  RESPONSE_STATUS_CODE_400 = 400;
    
	public static Properties prop;
	
	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("C:\\QA\\Selenium_Workspace\\RestApi\\src\\main\\java\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
