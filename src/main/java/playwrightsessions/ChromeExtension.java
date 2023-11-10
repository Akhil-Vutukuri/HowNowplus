package playwrightsessions;

import com.microsoft.playwright.*;
import com.microsoft.playwright.BrowserType.LaunchPersistentContextOptions;

import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ChromeExtension {
	public static void main(String[] args) throws InterruptedException {
		try (Playwright playwright = Playwright.create()) {

			//add the extension path of the folder that has manifest.json file in it.
			Path extensionPath = Paths
					.get("C:\\Users\\akhil\\Downloads\\hownow-chrome-extension (14)\\hownow-chrome-extension"); 
			
			//define the chromeArgs that will allow to access Chrome-extension.
			List<String> chromeArgs = new ArrayList<>();
			chromeArgs.add("--disable-background-timer-throttling");
			chromeArgs.add("--disable-backgrounding-occluded-windows");
			chromeArgs.add("--disable-renderer-backgrounding");
			chromeArgs.add("--no-sandbox");
			chromeArgs.add("--disable-extensions-except=" + extensionPath);
			chromeArgs.add("--load-extension=" + extensionPath);

			LaunchPersistentContextOptions launchOptions = new LaunchPersistentContextOptions();
			launchOptions.setChannel("chrome");
			launchOptions.setHeadless(false);
			launchOptions.setArgs(chromeArgs);

			// Path userDataDir = Paths.get("C:\\User Data"); // Replace with your user data
			// directory path

			Path userDataDir = Paths.get("C:\\my-user-data"); // to create a custom directory named "my-user-data" on your C: drive

			BrowserContext browser = playwright.chromium().launchPersistentContext(userDataDir, launchOptions);
			Page page = browser.newPage();
			page.navigate("https://hownow.app/extension-fallback"); // Replace with the URL you want to navigate to

			Thread.sleep(1000);

			// Logging into the instance.
			page.frameLocator("//iframe[@id='hownow-extension']").locator("//input[@id='subdomain']").fill("team");
			page.frameLocator("//iframe[@id='hownow-extension']").locator("//input[@id='get-started']").click();
			Thread.sleep(3000);

			// Logging into the extension.
			page.frameLocator("//iframe[@id='hownow-extension']").locator("//input[@id='email']")
					.fill("akhil.vutukuri@gethownow.com");
			page.frameLocator("//iframe[@id='hownow-extension']").locator("//input[@id='password']").fill("Hownow123");
			page.frameLocator("//iframe[@id='hownow-extension']").locator("//input[@id='login']").click();

			page.frameLocator("//iframe[@id='hownow-extension']").locator("//input[@id='option4-notch-show']").click();

			page.frameLocator("//iframe[@id='hownow-extension']").locator("//input[@id='continue']").click();
			
			
			
			Thread.sleep(2000);
			Locator locator = page.frameLocator("//iframe[@id='hownow-extension']").locator("//input[@id='search']");
			if (locator.isVisible()) {
				System.out.println("Logged into extension successfully");
			} else {
				System.out.println("Login failed");
			}

			page.frameLocator("//iframe[@id='hownow-extension']").locator("//input[@id='search']").fill("Nugget test");
			page.keyboard().press("Enter");
			Thread.sleep(5000);
			List<String> contentNames = page.frameLocator("//iframe[@id='hownow-extension']")
					.locator("//div[@id='search-container']//p[@class='component-title']").allInnerTexts();

			for (String contentName : contentNames) {
				if (contentName.contains("Heap Nugget Test")) {
					System.out.println("Content found");
					page.waitForLoadState();

					page.frameLocator("//iframe[@id='hownow-extension']")
							.locator("//div[@id='search-container']//p[text()='" + contentName + "']").click();
					
					String course_title = page.frameLocator("//iframe[@id='hownow-extension']")
							.locator("//div[@class='lesson-name']").textContent();
					
					if (course_title.equals(contentName)) {
						System.out.println("Content Redirection Successfull:- " + course_title);
						break;
					}
				} else if (!contentName.contains("Heap Nugget Test")) {
					System.out.println("content not found");
					break;
				}
			}
			
			//Logging out
			page.frameLocator("//iframe[@id='hownow-extension']").locator("//div[@class='height-40 p-icon-wrapper']")
					.click();
			page.frameLocator("//iframe[@id='hownow-extension']").locator("//input[@id='logout']").click();

			Thread.sleep(3000);

			// Ensure you close the browser context and browser when you're done
			page.close();
			browser.close();
			playwright.close();
		}
	}
}
