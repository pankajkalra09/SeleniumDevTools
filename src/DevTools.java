import java.util.Optional;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.devtools.v119.emulation.Emulation;

public class DevTools {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\philip_parker\\Desktop\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
		//since we want the methods exposed by chrome only and not webdriver, so we are using chromedriver class.
		ChromeDriver driver = new ChromeDriver();
		//Creating the object of chrome devtools with getDevTools method. This method helps us in initilizing devtools.
		//Now we have created a method for DevTools and now we have to create a session. This session will instantilize between your selenium code and browser.
		
	org.openqa.selenium.devtools.DevTools devTools = driver.getDevTools();
		devTools.createSessionIfThereIsNotOne(); 
		//since the seesion is created now we can send the commands to CDP methods. Selenium is just giving the commands to kickoff CDP methods.
		//CDP methods will invoke and get access to chrome dev tools.
		//in this test we thought of emulating our device browser to multiple devices i.e. iPad, Android, iPhone etc.
		// we can get all methos from "https://chromedevtools.github.io/devtools-protocol/"
		devTools.send(Emulation.setDeviceMetricsOverride(600, 1000, 70, true, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
		
		driver.get("https://unifyedqa.unifyed.com");
		
		Thread.sleep(3000);
		//driver.manage().window().maximize();
		//driver.close();
	}

}
