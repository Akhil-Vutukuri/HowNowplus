package playwrightsessions;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;

public class HN_NuggetCreation {
	public static void main(String[] args) throws InterruptedException {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();

			// Start tracing before creating / navigating a page.
			context.tracing()
					.start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(true));

			Page page = context.newPage();
			page.navigate("https://hownowindia.com/users/sign_in");
			page.getByPlaceholder("Your-Workspace-URL").click();
			page.getByPlaceholder("Your-Workspace-URL").fill("team");
			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Get Started")).click();
			page.getByPlaceholder("Email Address").click();
			page.getByPlaceholder("Email Address").fill("akhil.vutukuri@gethownow.com");
			page.getByPlaceholder("Password").click();
			page.getByPlaceholder("Password").fill("Hownow123");
			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();

			// Stop tracing and export it into a ZIP archive.
			// context.tracing().stop(new
			// Tracing.StopOptions().setPath(Paths.get("trace.zip")));
			Thread.sleep(3000);
			page.getByTitle("Create", new Page.GetByTitleOptions().setExact(true)).click();
			page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Create Nugget")).click();
			page.locator("xpath=//span[@data-bip-value='Nugget Name']").click();
			page.getByRole(AriaRole.TEXTBOX).fill("Nugget demo 2");
			page.getByLabel("Text").click();
			page.locator("xpath=//div[@data-placeholder='Enter your text']").fill("Adding sample text");
			page.getByText("Save & Continue Publish").click();
			page.locator("#s2id_autogen2").click();
			page.locator("#s2id_autogen2").fill("automation");
			page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Automation").setExact(true)).click(); // Need
																														// to
																														// check
																														// what's
																														// happening
																														// here.
			page.locator("#s2id_autogen5").click();
			page.locator("#s2id_autogen5").fill("Akhil manager 2");
			page.getByText("Akhil Manager 2", new Page.GetByTextOptions().setExact(true)).click();
			page.getByText("Apply").click();
			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Publish")).click();

		}
	}
}
