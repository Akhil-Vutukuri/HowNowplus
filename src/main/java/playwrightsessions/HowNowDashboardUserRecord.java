package playwrightsessions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

public class HowNowDashboardUserRecord {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();

		LaunchOptions lp = new LaunchOptions();
		lp.setChannel("chrome");// you can also use 'msedge' 'chrome-beta' 'msedge-beta' 'msedge-dev' etc...
		lp.setHeadless(false);

		Browser browser = playwright.chromium().launch(lp);

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

		page.navigate("https://team.hownowindia.com/dashboard/users");
		
		String userName = "Akhil admin";
		
		page.waitForSelector("//i[@class='fal fa-search fill-search-icon search-icon default-icon-img']").click();
		
		page.waitForSelector("//input[@name='search_key' and @id='add-user-srch']").fill(userName);
		page.keyboard().press("Enter");
		
		
		
		page.pause();
		if(page.waitForSelector("//tr[@id='user-row-55801']").isVisible()) {
			System.out.println("User is present :" + userName);
			
			System.out.println("Fetching user details..");
			
			System.out.println("Joined date : "+page.textContent("//tr[@id='user-row-55801']/td[@class=' joined_date']"));
			System.out.println("Users email address : "+page.textContent("//tr[@id='user-row-55801']/td[@class=' email']"));
			System.out.println("Group Names : "+page.textContent("//tr[@id='user-row-55801']/td[@class=' groups group_names']"));
			System.out.println("Must do completed % : "+page.textContent("//tr[@id='user-row-55801']/td[@class=' must_do_completed']"));
			System.out.println("Skills count : "+page.textContent("//tr[@id='user-row-55801']/td[@class=' skills']"));
			System.out.println("Content created count : "+page.textContent("//tr[@id='user-row-55801']/td[@class=' content_created']"));

		}
		else {
			System.out.println("User is not found :" +userName);
		}
				
				
		
		
	}

}
