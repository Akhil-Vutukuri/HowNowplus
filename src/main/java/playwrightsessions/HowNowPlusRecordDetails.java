package playwrightsessions;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

public class HowNowPlusRecordDetails {

	public static void main(String[] args) throws InterruptedException {

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

		Thread.sleep(2000);

		page.navigate("https://team.hownowindia.com/dashboard/insights/hnp_partners/20/hnp_partner_courses");

		List<String> contentNames = new ArrayList<>();
		contentNames.add("10 Steps for Successful Appraisals");
		contentNames.add("3 Inroads for Handling a Narcissist");
		contentNames.add("Understanding Financial Information");

		for (String contentName : contentNames) {	
			page.waitForSelector("//i[@class='fal fa-search fill-search-icon search-icon default-icon-img']");
    		page.click("//i[@class='fal fa-search fill-search-icon search-icon default-icon-img']");
    		
    		Thread.sleep(1000);
    		page.waitForSelector("//input[@placeholder='Search' and @name='search_key' and @class='search-dt-hn show']");
    		page.fill("//input[@placeholder='Search' and @name='search_key' and @class='search-dt-hn show']", contentName.trim());
            page.keyboard().press("Enter");
			
			if (page.waitForSelector("//tr[@data-name='" + contentName.trim() + "']").isVisible()) {
				System.out.println("---------{" + contentName + "}---------");
				System.out.println("Language: "
						+ page.textContent("//tr[@data-name='" + contentName + "']/td[@class=' language']"));
				
				//Fetching the skills------->
				if (page.isVisible("//tr[@data-name='" + contentName.trim()
						+ "']//td[@class=' skills group_names']//li[contains(text(),'+')]")) {
					page.locator("//tr[@data-name='" + contentName.trim()
							+ "']//td[@class=' skills group_names']//li[contains(text(),'+')]").click();
					Thread.sleep(1000);
					if(page.isVisible("//div[@class='hnp-skill-sidedrawer sidedrawer']//div[@id='s2id_autogen9']")) {
					page.waitForSelector("//div[@class='hnp-skill-sidedrawer sidedrawer']//div[@id='s2id_autogen9']").click();
					page.waitForSelector("//div[text()='100']").click();
					Thread.sleep(2000);
					}
					page.waitForSelector("//div[@class='hnp-skill-sidedrawer sidedrawer']//td[@class='name_column sorting_1']//span[@class='info']/span");
					List<String> skillsList = page
							.locator("//td[@class='name_column sorting_1']//span[@class='info']/span")
							.allTextContents();
					System.out.println("Skills :");
					for (String allSkills : skillsList) {
						System.out.println(allSkills);
					}
					Thread.sleep(1000);
					page.click("div[class='hnp-skill-sidedrawer sidedrawer'] button[type='button']");
					
				} else if (!page.locator("//tr[@data-name='" + contentName + "']//li[contains(text(),'+')]")
						.isVisible()) {

					List<String> twoSkills = page
							.locator("//tr[@data-name='" + contentName + "']/td[@class=' skills group_names']//span")
							.allTextContents();
					System.out.println("Skills :");
					for (String allSkills : twoSkills) {
						System.out.println(allSkills);
					}
				} else {
					System.out.println("Skills Not found");
				}
				
				
				//Fetching the channels-------->
				if (page.isVisible("//tr[@data-name='" + contentName
						+ "']/td[@class=' channels group_names']//li[contains(text(),'+')]")) {
					page.locator("//tr[@data-name='" + contentName.trim()
							+ "']//td[@class=' channels group_names']//li[contains(text(),'+')]").click();
					Thread.sleep(2000);
					List<String> channelsList = page
							.locator("//div[@class='hnp-channel-sidedrawer sidedrawer']//td[@class='name_column sorting_1']//span[@class='info']/span[text()]")
							.allTextContents();
					System.out.println("Channels :");
					for (String allChannels : channelsList) {
						System.out.println(allChannels);
					}
					Thread.sleep(1000);
					page.click("div[class='hnp-channel-sidedrawer sidedrawer'] button[type='button']");
				} else if (!page.isVisible("//tr[@data-name='" + contentName
						+ "']/td[@class=' channels group_names']//li[contains(text(),'+')]")) {
					List<String> twoChannels = page
							.locator("//tr[@data-name='" + contentName + "']/td[@class=' channels group_names']//span")
							.allTextContents();
					System.out.println("Cannels :");
					for (String allChannels : twoChannels) {
						System.out.println(allChannels);
					}
				} else {
					System.out.println("Channels Not found");
				}
				
				//Fetching the Duration--------->
				String duration = page.textContent("//tr[@data-name='" + contentName + "']/td[@class=' duration']");
				System.out.println("Duration :" + duration);
			} else {
				System.out.println("Content " + contentName + " Not found");
			}
			page.navigate("https://team.hownowindia.com/dashboard/insights/hnp_partners/20/hnp_partner_courses");

		}
		page.close();
		browser.close();
		playwright.close();
	}
}
