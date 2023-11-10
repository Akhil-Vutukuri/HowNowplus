package playwrightsessions;

import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class VisibleElements {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		Page page = browser.newPage();

		// Two ways to check this i.e. button is visible or apply visibility filter.
		page.navigate("https://www.Amazon.com/");
		List<String> links = page.locator("a:visible").allInnerTexts(); // AllInnerTexts will collect all the links and
																		// gives the text.
		for (int i = 0; i < links.size(); i++) {
			System.out.println(links.get(i));
		}

		int imagescount = page.locator("xpath=//img >> visible=true").count(); // Applying the visibility filter.
		System.out.println(imagescount);
	}

}
