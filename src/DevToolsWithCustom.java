import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v119.emulation.Emulation;

public class DevToolsWithCustom {
 // here we are bypassing the selenium's send method to invode cdp method and directly calling the CDP methods using executeCdpCommands method.
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\philip_parker\\Desktop\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
		//since we want the methods exposed by chrome only and not webdriver, so we are using chromedriver class.
		ChromeDriver driver = new ChromeDriver();
		
		org.openqa.selenium.devtools.DevTools devTools = driver.getDevTools();
		devTools.createSessionIfThereIsNotOne(); 
		//since the seesion is created now we can send the commands to CDP methods. Selenium is just giving the commands to kickoff CDP methods.
		//CDP methods will invoke and get access to chrome dev tools.
		//in this test we thought of emmulating our device browser to multiple devices i.e. iPad, Android, iPhone etc.
		// we can get all methos from "https://chromedevtools.github.io/devtools-protocol/"
	//	devTools.send(Emulation.setDeviceMetricsOverride(600, 1000, 60, true, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
		
		//instead of using selenium's Emulation.setDeviceMetricsOverride we will use custom method using HashMap.
		//Selenium team is also using params.build() as second argument which means they created a hashmap and using key value pair there. 
		//hashmap object they are sending as a second argument for executeCdpCommand method.
		Map deviceMetric = new HashMap();
		deviceMetric.put("width", 500);
		deviceMetric.put("height", 700);
		deviceMetric.put("deviceScaleFactor", 40);
		deviceMetric.put("mobile", true);
		driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetric);
		
		driver.get("https://chromedevtools.github.io/devtools-protocol/tot/Emulation/#method-setDeviceMetricsOverride");
		
		Thread.sleep(3000);
		//driver.manage().window().maximize();
		//driver.close();
	}

}
