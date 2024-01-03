import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v118.network.Network;
import org.openqa.selenium.devtools.v119.emulation.Emulation;
import org.openqa.selenium.devtools.v119.fetch.Fetch;
import org.openqa.selenium.devtools.v119.network.model.Request;
import org.openqa.selenium.devtools.v119.network.model.Response;

public class DevTools_NetworkMocking {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\philip_parker\\Desktop\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		org.openqa.selenium.devtools.DevTools devTools = driver.getDevTools();
		devTools.createSessionIfThereIsNotOne();
		//If we want to mock or intercept the existing API calls then we will use domain 'Fetch' on CDP documentation page.
		//First we have to enable the network fetch with 2 optional parameters 'patterns' and 'handleAuthRequest'
		//patterns > which kind of request we want to listen. we can narrow down our request by specifying a pattern using reg expression like .css, .jpg etc.
		devTools.send(Fetch.enable(Optional.empty(), Optional.empty()));
		// we have to manipulate our request before sending it to server that can be possible with 1 event called "Fetch.requestPaused" and returns the request.
		// This event gets triggered whenever our application is ready to send any request to server. 
		// We will fire "Fetch.requestPaused" event, manipulate our request and than manually call continueRequest method. That way we are sending the modified request.
		// we are using listeners to say listen the events until we got fetch.requestPaused() > Once that event is paused, it will give us request.
		devTools.addListener(Fetch.requestPaused(), request -> {
			if(request.getRequest().getUrl().contains("=shetty"))
			{
				//we are capturing the same url and replacing the value of shetty with BadGuy where we have 1 result and message we want to verify.
				String mockedUrl = request.getRequest().getUrl().replace("=shetty", "=BadGuy");
				System.out.println(mockedUrl);
				//now we have to resume the call.
				devTools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(mockedUrl), Optional.of(request.getRequest().getMethod()), Optional.empty(), Optional.empty(), Optional.empty()));
			}
			//if shetty is not matched still we want to continue request with the existing url as we have paused the request, so we call continueRequest method with old URL as under in else block.
			else {
				devTools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(request.getRequest().getUrl()), Optional.of(request.getRequest().getMethod()), Optional.empty(), Optional.empty(), Optional.empty()));
			}
			
		});

	
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//div[@class='jumbotron']/button")).click();
		Thread.sleep(3000);
		
	}

}
