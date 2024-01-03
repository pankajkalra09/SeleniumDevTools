import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v118.network.Network;
import org.openqa.selenium.devtools.v119.emulation.Emulation;
import org.openqa.selenium.devtools.v119.network.model.Request;
import org.openqa.selenium.devtools.v119.network.model.Response;

public class DevTools_Network {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\philip_parker\\Desktop\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		// Creating the object of chrome devtools with getDevTools method. This method helps us in initilizing devtools.
		// Now we have created a method for DevTools and now we have to create a
		// session. This session will instantilize between your selenium code and browser.

		org.openqa.selenium.devtools.DevTools devTools = driver.getDevTools();
		devTools.createSessionIfThereIsNotOne();
		// Step 1: call Network.enable method > Enables network tracking, network events
		// will now be delivered to the client (selenium).
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		// Now our client (selenium) can listen the network.
		// We can make use of Events to know the request and response actions. Let's first see the request event.
		// we can make use of 1 event "Network.requestWillBeSent" to know before request is being sent to server. This will help us mock the request.
		// This will request us a request object that we can consume using lamda expression to know further details.

		devTools.addListener(Network.requestWillBeSent(), request -> {
			org.openqa.selenium.devtools.v118.network.model.Request req = request.getRequest();
			System.out.println("Request URL is: " + req.getUrl()); // this will tell us the request url we sent
		});

		// Now to get the response back from network tab we use Events. Events are some activities which will get triggered after an action.
		// FOr example, when data come from server to browser, at that time Network.responseReceived method gets triggered and then only we can get the
		// data as a response from Network.
		// Now selenium will catch this event and fetch the data from it using addListerner and get a response object and using lamda opetations we will
		// fetch further details.
		// addlisterners accepts 2 arguments, 1st is event which means we will keep on listening to that event and selenium will be notified.
		// 2nd is consumer that handles the data we got from the event trigerred. we
		// call it response object in javascript and on this object we have to act upon using Lamda expression.

		devTools.addListener(Network.responseReceived(), response -> {
			// inside this we can write whatever we want from the event.
			// first we will got the response in 1 object of response class.
			org.openqa.selenium.devtools.v118.network.model.Response res = response.getResponse();
			// System.out.println("Response Code is: "+res.getStatus());
			// we want to capture the failed requests only in the logs where status code started with 4. i.e. 400, 404 etc
			if (res.getStatus().toString().startsWith("4")) {
				System.out.println(res.getUrl() + " is failing with status code as " + res.getStatus());
			}
			System.out.println("Response Time is: " + res.getResponseTime());
		});
		driver.get("https://unifyedpreprod.unifyed.com");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//div[contains(@class, 'profile')]//button[@class='btn btn-primary']")).click();
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("philip_parker@unifyedqa.edu");
		driver.findElement(By.id("password")).sendKeys("Admin@2008");
		driver.findElement(By.id("submitButton")).click();
		driver.close();
	}

}
