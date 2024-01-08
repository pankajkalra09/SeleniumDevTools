import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowPopUp_Auth {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\philip_parker\\Desktop\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		//For window authentication pop up we can make use of selenium as well as AutoIT. For selenium, 
		//Instead of passing direct URL in driver.get(), we can send username and password as well in the URL so that selenium keeps the URL and password in the memory of the browser. 
		//So, whenever a pop up comes asking for username and password selenium sends the same in the pop up.
		//Driver.get(http://username:password@url).
		// upon clicking this Basic Auth link shown below, pop up for username and password comes.
		//so instead of sending url "https://the-internet.herokuapp.com/" if we pass username and password in the url, then we can handle this with selenium.

		driver.get("http://admin:admin@the-internet.herokuapp.com/");
		driver.findElement(By.linkText("Basic Auth")).click();
		
		
	}

}
