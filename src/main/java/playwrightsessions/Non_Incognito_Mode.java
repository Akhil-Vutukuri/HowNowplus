package playwrightsessions;

import java.nio.file.Paths;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Non_Incognito_Mode {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();

		// below is to launch in a non-incognito mode .
		// BrowserContext context =
		// playwright.chromium().launchPersistentContext(Paths.get(""), new
		// BrowserType.LaunchPersistentContextOptions().setHeadless(false));

		// belowis to launch in your own chrome profile.
		BrowserContext personalContext = playwright.chromium().launchPersistentContext(
				Paths.get("C:\\Users\\akhil\\AppData\\Local\\Google\\Chrome\\User Data\\default"),
				new BrowserType.LaunchPersistentContextOptions().setHeadless(false)
						.setExecutablePath(Paths.get("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe")));
		Page page = personalContext.newPage();
		page.navigate("http://www.google.com");
		System.out.println(page.title());

	}

}
