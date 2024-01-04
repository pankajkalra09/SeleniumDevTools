import org.openqa.selenium.chrome.ChromeDriver;
import java.net.URI;

public class DevTools_BasicAuthentication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\philip_parker\\Desktop\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		//there is class "uri" in java that help us to take the URL of the what we ahve hit in the browser and it will help us to pass it.
		//we can use uri.getHost() > Let's way we have a complete URL and from that URL we can easily identify the host. for example: https://httpbin.org/basic-auth/foo/bar
		//here httpbin.org is domain/host where our server is hosted, basic-auth is resource and foo/bar is the parameter.
		//From java 8, there is concept called predicate (->) that will help you to create 1 filter condition for your data. For example we have a array of 100 numbers and we want to filter out even numbers.
		//with the help of predicates, we can do so. Predicate takes the input, filter it out and give the output back to you.
		//
		uri -> uri.getHost().contains("http://httpbin.org");
		
		
		
	}

}
