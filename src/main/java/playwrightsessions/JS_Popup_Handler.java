package playwrightsessions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class JS_Popup_Handler {

	public static void main(String[] args) {
		
		Playwright playwright = Playwright.create(); 
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

		Page page = browser.newPage();
		
		//playwright will handle all the JS alerts handle internally like, Alert, COnfirmation Pop-up's and Prompts
		//if you want to page listner event we can use the below code to handle it manually using Lambda.
		page.onDialog(dialog -> {
			String text = dialog.message();
			System.out.println(text);
			dialog.accept("Adding the text into the Prompt manually"); //If you want to accept the Alerts
			dialog.dismiss(); //If you want to manually dismiss.
		});
	}

}
