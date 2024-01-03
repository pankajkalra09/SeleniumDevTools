import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v119.emulation.Emulation;

public class DevTools_Geolocation {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\philip_parker\\Desktop\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
		//since we want the methods exposed by chrome only and not webdriver, so we are using chromedriver class.
		ChromeDriver driver = new ChromeDriver();
		
		//https://chromedevtools.github.io/devtools-protocol/tot/Emulation/#method-clearGeolocationOverride
		//CDP methods from above url
		
		//we are trying to change the location with the help of longitude and latitude and see if based upon the location, content language is changed.
		org.openqa.selenium.devtools.DevTools devTools = driver.getDevTools();
		devTools.createSession(); 
		//ltOptions.put("geoLocation", "US");
		Map<String, Object> coordinates = new HashMap<String, Object>();
		coordinates.put("latitude",42.1408845);
		coordinates.put("longitude",-72.5033907);
		coordinates.put("accuracy",100);
		
		driver.executeCdpCommand("Emulation.setGeolocationOverride",coordinates);
		
		driver.get("https://www.google.com");
		driver.findElement(By.name("q")).sendKeys("netflix", Keys.ENTER);
		Thread.sleep(5000);
		driver.findElements(By.xpath("//h3[@class='LC20lb MBeuO DKV0Md']")).get(0).click();
		Thread.sleep(2000);
		String text = driver.findElement(By.xpath("//*[@id=\"appMountPoint\"]/div/div/div/div[2]/div[1]/div[2]/div[1]/h1")).getText();
		System.out.println(text);
		
		
	}

}
