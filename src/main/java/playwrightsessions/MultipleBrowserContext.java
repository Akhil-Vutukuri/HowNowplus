package playwrightsessions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class MultipleBrowserContext {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create();

		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

		BrowserContext brcx1 = browser.newContext();

		Page p1 = brcx1.newPage();

		p1.navigate("https://team.hownowindia.com/users/sign_in");

		System.out.println(p1.title());

		BrowserContext brcx2 = browser.newContext();

		Page p2 = brcx2.newPage();

		p2.navigate("https://team.hownowindia.com/users/sign_in");

		System.out.println(p1.title());

		p1.close();
		brcx1.close();

		p2.close();
		brcx2.close();
	}

}
