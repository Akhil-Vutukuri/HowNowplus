package playwrightsessions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

public class Users_Checkbox {

	public static void main(String[] args) throws InterruptedException {
		Playwright playwright = Playwright.create(); 
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		
		Page page = browser.newPage();
		page.navigate("https://hownowindia.com/users/sign_in");
	      page.getByPlaceholder("Your-Workspace-URL").click();
	      page.getByPlaceholder("Your-Workspace-URL").fill("team");
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Get Started")).click();
	      page.getByPlaceholder("Email Address").click();
	      page.getByPlaceholder("Email Address").fill("akhil.vutukuri@gethownow.com");
	      page.getByPlaceholder("Password").click();
	      page.getByPlaceholder("Password").fill("Hownow123");
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
		
	      Thread.sleep(3000);

	      
	      page.navigate("https://team.hownowindia.com/dashboard/users");
	      
	      Thread.sleep(6000);
	      
	      //Here in the below code we are going to click on the check box using the X-path. 
	      page.locator("//tr[@data-email='pavithra.basavnthraya+4@gethownow.com']/child::td[@class=' select-checkbox' and @data-dt-row='0']").click();
	      
	      //Here we are locating the entire data table, And then we are using the Locator option to find the unique text to select the row
	      //and then we are going to click on the sibling element using the playwright way
	      Locator row = page.locator("//div[@class='dataTables_scrollBody']//tr");
	         row.locator(":scope", new Locator.LocatorOptions().
	    		  setHasText("Akhil admin")).
	      					locator("//td[@class=' select-checkbox']").
	      							click();
	      
	       
	      
	      //below code is to generally print the entire table using java lambda
	      Locator row2 = page.locator("//div[@class='dataTables_scrollBody']//tr");
	      row.locator(":scope").allInnerTexts().forEach(e -> System.out.println(e));  //Using the lambda operator to print.
	   
	}
}
