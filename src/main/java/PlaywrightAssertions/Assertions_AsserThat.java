package PlaywrightAssertions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Assertions_AsserThat {

	public static void main(String[] args) {

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

		Page page = browser.newPage();
		page.navigate("http://www.tizag.com/htmlT/htmlcheckboxes.php");

		// if assertion has failed then it will throw the exceptions
		// if assertion has passed then it doesn't print any output.

		assertThat(page).hasURL("http://www.tizag.com/htmlT/htmlcheckboxes.php");

		assertThat(page).hasTitle("HTML Tutorial - Checkboxes");

		assertThat(page.locator("#menu > a:nth-child(29)")).hasText("HTML - Tags");

		assertThat(page.locator("//html/body/table[3]/tbody/tr[1]/td[2]/table/tbody/tr/td/div[6]/input[1]"))
				.isChecked();
		assertThat(page.locator("//html/body/table[3]/tbody/tr[1]/td[2]/table/tbody/tr/td/div[6]/input[1]"))
				.isVisible();
		// assertThat(page.locator("//html/body/table[3]/tbody/tr[1]/td[2]/table/tbody/tr/td/div[6]/input[2]")).isChecked();

		/*Few Examples
		 * 
		 * assertThat(locator).isChecked() Checkbox is checked
		 * assertThat(locator).isDisabled() Element is disabled
		 * assertThat(locator).isEditable() Element is editable
		 * assertThat(locator).isEmpty() Container is empty
		 * assertThat(locator).isEnabled() Element is enabled
		 * assertThat(locator).isFocused() Element is focused
		 * assertThat(locator).isHidden() Element is not visible
		 * assertThat(locator).isVisible() Element is visible
		 * assertThat(locator).containsText() Element contains text
		 * assertThat(locator).hasAttribute() Element has a DOM attribute
		 * assertThat(locator).hasClass() Element has a class property
		 * assertThat(locator).hasCount() List has exact number of children
		 * assertThat(locator).hasCSS() Element has CSS property
		 * assertThat(locator).hasId() Element has an ID
		 * assertThat(locator).hasJSProperty() Element has a JavaScript property
		 * assertThat(locator).hasText() Element matches text
		 * assertThat(locator).hasValue() Input has a value
		 * assertThat(locator).hasValues() Select has options selected
		 * assertThat(page).hasTitle() Page has a title assertThat(page).hasURL() Page
		 * has a URL assertThat(response).isOK() Response has an OK status
		 * 
		 */

		page.close();
		browser.close();
		playwright.close();

	}

}