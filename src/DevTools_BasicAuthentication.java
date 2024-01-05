import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.chrome.ChromeDriver;
import java.net.URI;
import java.util.function.Predicate;

public class DevTools_BasicAuthentication {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\philip_parker\\Desktop\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		//there is class "uri" in java that help us to take the URL of the what we ahve hit in the browser and it will help us to pass it.
		//we can use uri.getHost() > Let's way we have a complete URL and from that URL we can easily identify the host. for example: https://httpbin.org/basic-auth/foo/bar
		//here httpbin.org is domain/host where our server is hosted, basic-auth is resource and foo/bar is the parameter.
		//From java 8, there is concept called predicate (->) that will help you to create 1 filter condition for your data. For example we have a array of 100 numbers and we want to filter out even numbers.
		//with the help of predicates, we can do so. Predicate takes the input, filter it out and give the output back to you.
		Predicate<URI> uriPredicate = uri -> uri.getHost().contains("httpbin.org");
		//now selenium is asking if you face any basic authentication pop up, then register it.
		//For registration, if we can cast our driver to HashAuthentication, that is a class in selenium that says that the driver we are using is having the support to handle basic authentication.
		//now after cast, driver has all knowledge about each and every hit made to the browser and it will have to checks to see if there is any error.
		//now we have to register username and password details to our driver so that driver knows what to enter ahen basic auth pop up comes.
		// register method expects 2 parameters (1. predicate logic > for what url we have to apply this authentication. when condition is mathced where url contais httpbin.org, then enter username and password as second parameter)
		//there is a class called 'UserNameAndPassword' which help us to enter username and password.
		((HasAuthentication)driver).register(uriPredicate, UsernameAndPassword.of("foo", "bar"));
		driver.get("https://httpbin.org/basic-auth/foo/bar");
		}
}
