package playwrightsessions;

import com.microsoft.playwright.Browser;

import com.microsoft.playwright.BrowserType;

import com.microsoft.playwright.Page;

import com.microsoft.playwright.Playwright;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Print_DataTable {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();

		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

		Page page = browser.newPage();

		page.navigate("https://money.rediff.com/indices/nse/NIFTY-50?src=moneyhome_nseIndices");

		//Row count
		System.out.println(page.locator(".dataTable > tbody").locator("tr").count());

		//column count
		System.out.println(page.locator(".dataTable > tbody").locator("tr:first-child").locator("td").count());
		
		//Printing if the second column has the text Nifty or not.
		assertThat(page.locator(".dataTable > tbody").locator("tr:first-child").locator("td:nth-child(2)")).hasText("Nifty");

		//Printing the entire data table!
		page.locator(".dataTable > tbody").allInnerTexts().forEach(text -> System.out.println(text));

	}

}