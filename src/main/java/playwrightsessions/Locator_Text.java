package playwrightsessions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Locator_Text {
/*
1. Text selector 
2. CSS selectorl
3. Selecting visible elements
4. Selecting elements that contain other elements
5. Selecting elements matching one of the conditions
6. Selecting elements in Shadow DOM
7. Selecting elements based on layout
8. XPath selectors
9. N-th element selector
10. React selectors
11. Vue selectors
12. id, data—testid, data—test—id, data—test selectors
13. Pick n-th match from the query result
14. Chaining selectors
 */
	
	
	public static void main(String[] args) {
		Playwright playwright = Playwright.create(); 
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
	//	BrowserContext context = browser.newContext(); //This is not necessary as we are not going to use multiple browsers over here.
		
		//text locator: This is playwright specific so there is a different option to validadate the same.
		
		//case 1
		Page page = browser.newPage();
		page.navigate("https://www.orangehrm.com/en/30-day-free-trial/");
		Locator links = page.locator("text=Privacy Policy");  //this we generally use when we have to click on a specific unique element.
		
		//case 2
		for(int i =0; i<links.count();i++) { String text =
		links.nth(i).textContent(); if(text.contains("Service Privacy Policy")) {  //using the for loop to click on nth index
		links.nth(i).click(); break; } }
		 
		
		//case 3
		page.navigate("https://demo.opencart.com/index.php?route=account/login");
		System.out.println(page.locator("div.card-body h2:has-text('New Customer')").textContent()); //using the parent tag and class name and then child attribute using has-text("text")
		
		page.locator("form button:has-text('Login')").click(); //We generally use this when we have to traverse from the parent element.		
		
	}

}
