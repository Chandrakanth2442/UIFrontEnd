import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class DemoTests {

    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    Page page;

    @BeforeAll
    static void setupClass() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
    }

    @AfterAll
    static void tearDownClass() {
        browser.close();
        playwright.close();
    }

    @BeforeEach
    void setup() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void cleanup() {
        context.close();
    }

    // 1. Verify Page Title
    @Test
    void checkPageTitle() {
        page.navigate("https://example.com");
        assertTrue(page.title().contains("Example Domain"));
    }

    // 2. Login with Valid Credentials
    @Test
    void loginValidUser() {
        page.navigate("https://the-internet.herokuapp.com/login");
        page.fill("#username", "tomsmith");
        page.fill("#password", "SuperSecretPassword!");
        page.click("button[type='submit']");
        assertTrue(page.locator(".flash.success").textContent().contains("You logged into a secure area!"));
    }

    // 3. Login with Invalid Credentials
    @Test
    void loginInvalidUser() {
        page.navigate("https://the-internet.herokuapp.com/login");
        page.fill("#username", "wrong");
        page.fill("#password", "wrong");
        page.click("button[type='submit']");
        assertTrue(page.locator(".flash.error").textContent().contains("Your username is invalid!"));
    }

    // 4. Fill a Form and Submit
    @Test
    void formSubmission() {
        page.navigate("https://demoqa.com/automation-practice-form");
        page.fill("#firstName", "John");
        page.fill("#lastName", "Doe");
        page.fill("#userEmail", "john@example.com");
        page.click("label[for='gender-radio-1']");
        page.fill("#userNumber", "9876543210");
        page.click("#submit");
        assertTrue(page.locator(".modal-content").isVisible());
    }

    // 5. Handle Dropdown
    @Test
    void selectDropdownOption() {
        page.navigate("https://the-internet.herokuapp.com/dropdown");
        page.selectOption("#dropdown", "2");
        assertEquals("2", page.inputValue("#dropdown"));
    }

    // 6. Mouse Hover Action
    @Test
    void mouseHoverTest() {
        page.navigate("https://the-internet.herokuapp.com/hovers");
        page.hover(".figure:nth-child(3) img");
        assertTrue(page.locator(".figure:nth-child(3) .figcaption").isVisible());
    }

    // 7. File Upload
    @Test
    void uploadFile() {
        page.navigate("https://the-internet.herokuapp.com/upload");
        page.setInputFiles("#file-upload", "src/test/resources/sample.txt");
        page.click("#file-submit");
        assertTrue(page.locator("#uploaded-files").textContent().contains("sample.txt"));
    }

    // 8. Table Data Validation
    @Test
    void validateTableData() {
        page.navigate("https://the-internet.herokuapp.com/tables");
        String email = page.textContent("table#table1 tr:nth-child(2) td:nth-child(3)");
        assertEquals("fbach@yahoo.com", email);
    }

    // 9. API Mocking
    @Test
    void mockApiResponse() {
        page.route("**/users", route -> {
            route.fulfill(new Route.FulfillOptions()
                    .setStatus(200)
                    .setBody("[{\"id\":1, \"name\":\"Mocked User\"}]"));
        });
        page.navigate("https://example.com/users");
        assertTrue(page.locator("text=Mocked User").isVisible());
    }

    // 10. Screenshot
    @Test
    void takeScreenshot() {
        page.navigate("https://example.com");
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(java.nio.file.Paths.get("screenshots/example.png"))
                .setFullPage(true));
        assertTrue(java.nio.file.Files.exists(java.nio.file.Paths.get("screenshots/example.png")));
    }
}

