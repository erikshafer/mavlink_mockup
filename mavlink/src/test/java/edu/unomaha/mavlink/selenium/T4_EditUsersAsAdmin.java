package edu.unomaha.mavlink.selenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class T4_EditUsersAsAdmin {
	private WebDriver driver;
	private String baseUrl = "http://localhost:8080";
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private final static String driverLocation = "lib/chromedriver.exe";

	@BeforeClass
	public static void init() {
		System.setProperty("webdriver.chrome.driver", driverLocation);
	}

	@Before
	public void setUp() throws Exception {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-web-security");

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability("binary", driverLocation);
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		driver = new ChromeDriver(capabilities);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void test4GoToEditUsers() throws Exception {
		// open | /mavlink/logout |
		driver.get("http://localhost:8080/mavlink/logout");
		// open | /mavlink/index |
		driver.get(baseUrl + "/mavlink/index");
		// type | id=username | admin
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		// type | id=password | admin
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("admin");
		// click | css=button.btn.btn-cta |
		driver.findElement(By.cssSelector("button.btn.btn-cta")).click();
		// open | /mavlink/ |
		driver.get(baseUrl + "/mavlink/");
		// click | link=Admin |
		driver.findElement(By.linkText("Admin")).click();
		// click | link=Edit Users |
		driver.findElement(By.linkText("Edit Users")).click();
		// click | id=user-eshafer |
		driver.findElement(By.id("user-eshafer")).click();
		// type | id=lastName | Shafer Test
		driver.findElement(By.id("lastName")).clear();
		driver.findElement(By.id("lastName")).sendKeys("Shafer Test");
		// click | //button[@type='submit'] |
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		// type | id=lastName | Shafer
		driver.findElement(By.id("lastName")).clear();
		driver.findElement(By.id("lastName")).sendKeys("Shafer");
		// click | //button[@type='submit'] |
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		// click | link=Admin |
		driver.findElement(By.linkText("Admin")).click();
		// click | link=Edit Users |
		driver.findElement(By.linkText("Edit Users")).click();
		// assertNotText | css=body | *Erik Shafer Test*
		assertFalse(
		        driver.findElement(By.cssSelector("body")).getText().matches("^[\\s\\S]*Erik Shafer Test[\\s\\S]*$"));
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
