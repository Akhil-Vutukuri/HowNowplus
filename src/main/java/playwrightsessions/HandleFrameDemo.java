package playwrightsessions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class HandleFrameDemo {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create(); 
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		
		Page page = browser.newPage();
		
		page.navigate("http://londonfreelance.org/courses/frames/index.html");
		
		//Locating the frame first and accessing the element inside it.
		String header = page.frameLocator("frame[name='main']").locator("h2").textContent(); //using the css, we can use Xpath as well.
		System.out.println(header);
		
		
		//we can also user frame directly by passing the name
		System.out.println(page.frame("main").locator("h2").textContent());	
		

		//handling the iframes
		page.navigate("");
		//here we are using a contains Xpath to identify the dynamic locator and then using the locator filling in the value.
		page.frameLocator("//tagName[contains(@id='id_value')]").locator("#id_value").fill("add_value");
		
		
	}
	

}
