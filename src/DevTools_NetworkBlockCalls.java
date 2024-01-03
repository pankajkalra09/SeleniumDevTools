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

import com.google.common.collect.ImmutableList;

public class DevTools_NetworkBlockCalls {

	public static void main(String[] args) throws InterruptedException {

		//Blocking request calls like css, images etc.
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\philip_parker\\Desktop\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		org.openqa.selenium.devtools.DevTools devTools = driver.getDevTools();
		devTools.createSessionIfThereIsNotOne();
		//first we have to enable the network tracking and Network.setBlockedURLs is used for this activity.
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty())); //enabling network.
		devTools.send(Network.setBlockedURLs(ImmutableList.of("*.png", "*.jpeg", "*.jpg", "*.svg")));//accepts list of string url's.
		long startTime = System.currentTimeMillis();
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
		long endTime = System.currentTimeMillis();
		System.out.println(startTime - endTime);
		/*
		 * driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		 * driver.manage().window().maximize();
		 * driver.findElement(By.xpath("//div[@class='jumbotron']/button")).click();
		 * Thread.sleep(3000);
		 */
		
	}

}
