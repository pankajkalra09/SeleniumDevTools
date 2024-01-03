import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v119.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v119.network.Network;
import org.openqa.selenium.devtools.v119.emulation.Emulation;
import org.openqa.selenium.devtools.v119.fetch.Fetch;
import org.openqa.selenium.devtools.v119.network.model.ErrorReason;
import org.openqa.selenium.devtools.v119.network.model.Request;
import org.openqa.selenium.devtools.v119.network.model.Response;

public class DevTools_NetworkFailedRequest {

	public static void main(String[] args) throws InterruptedException {

		//Forcefully failing a API call using selenium CDP
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\philip_parker\\Desktop\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		org.openqa.selenium.devtools.DevTools devTools = driver.getDevTools();
		devTools.createSessionIfThereIsNotOne();
		//If we want to fail the existing API calls to test some custom error messages, then we will use domain 'Fetch' on CDP documentation page.
		//First we have to enable the network fetch with 2 optional parameters 'patterns' and 'handleAuthRequest'
		//patterns > which kind of request we want to listen. we can narrow down our request by specifying a pattern using reg expression like .css, .jpg etc.
		//Also enable method accepts Optional List of patterns as an argument so we have to convert our pattern into List. So we will be converting the list to Optional using Optional.of()
		
		//To create a pattern > RequestPattern is a class so first create a object of that class to catch the pattern. While creation of an object a parameterized constructor got called.
		//1st parameter is urlPattern. if we are listing *GetBook* means we want to capture all the calls which have GetBook in it.
		//2nd and 3rd are optional so we keep it as optional.empty
		Optional<List<RequestPattern>> pattern = Optional.of(Arrays.asList(new RequestPattern(Optional.of("*philip_parker*"),Optional.empty(),Optional.empty())));
		devTools.send(Fetch.enable(pattern, Optional.empty()));
		// we have to manipulate our request before sending it to server that can be possible with 1 event called "Fetch.requestPaused" and returns the request.
		// This event gets triggered whenever our application is ready to send any request to server. 
		// We will fire "Fetch.requestPaused" event, manipulate our request and than manually call continueRequest method. That way we are sending the modified request.
		// we are using listeners to say listen the events until we got fetch.requestPaused() > Once that event is paused, it will give us request.
		devTools.addListener(Fetch.requestPaused(), request -> {
			//since we have tracked/filtered our request based upon the pattern so need not to put if condition to match the call.
			//For failing a call we need to call Fetch.failRequest() that accepts 2 parameters (requestID, reason)
			devTools.send(Fetch.failRequest(request.getRequestId(), ErrorReason.FAILED));
			//when we failed this API call, no 
			
		});

		driver.get("https://unifyedpreprod.unifyed.com");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[contains(@class, 'profile')]//button[@class='btn btn-primary']/img")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("philip_parker@unifyedqa.edu");
		Thread.sleep(2000);
		driver.findElement(By.id("password")).sendKeys("Admin@2008");
		Thread.sleep(2000);
		driver.findElement(By.id("submitButton")).click();
		Thread.sleep(3000);
		/*
		 * driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		 * driver.manage().window().maximize();
		 * driver.findElement(By.xpath("//div[@class='jumbotron']/button")).click();
		 * Thread.sleep(3000);
		 */
		
	}

}
