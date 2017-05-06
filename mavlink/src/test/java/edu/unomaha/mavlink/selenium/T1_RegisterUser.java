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

public class T1_RegisterUser {
	private WebDriver driver;
	private String baseUrl = "http://localhost:8080/";
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
	public void testT1RegisterUser() throws Exception {
		// open | /mavlink/logout |
		driver.get("http://localhost:8080/mavlink/logout");
		// click | link=Admin |
		driver.findElement(By.linkText("Admin")).click();
		// click | link=Courses |
		driver.findElement(By.linkText("Courses")).click();
		// click | link=Info Science and Tech |
		driver.findElement(By.linkText("Info Science and Tech")).click();
		// click | link=Courses |
		driver.findElement(By.linkText("Courses")).click();
		// click | link=Computer Science |
		driver.findElement(By.linkText("Computer Science")).click();
		// click | link=Courses |
		driver.findElement(By.linkText("Courses")).click();
		// click | link=All |
		driver.findElement(By.linkText("All")).click();
		// click | //table[@id='table_units']/tbody/tr[10]/td[2]/a/span |
		driver.findElement(By.xpath("//table[@id='table_units']/tbody/tr[10]/td[2]/a/span")).click();
		// click | link=Courses |
		driver.findElement(By.linkText("Courses")).click();
		// click | link=All |
		driver.findElement(By.linkText("All")).click();
		// click | //table[@id='table_units']/tbody/tr[2]/td[2]/a/span |
		driver.findElement(By.xpath("//table[@id='table_units']/tbody/tr[2]/td[2]/a/span")).click();
		// click | link=Courses |
		driver.findElement(By.linkText("Courses")).click();
		// click | link=All |
		driver.findElement(By.linkText("All")).click();
		// click | //table[@id='table_units']/tbody/tr[7]/td[2]/a/span |
		driver.findElement(By.xpath("//table[@id='table_units']/tbody/tr[7]/td[2]/a/span")).click();
		// assertText | css=html | *This course teaches students Web-based
		// programming*
		assertTrue(driver.findElement(By.cssSelector("html")).getText()
		        .matches("^[\\s\\S]*This course teaches students Web-based programming[\\s\\S]*$"));
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
