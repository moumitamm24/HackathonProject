package base;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.WaitUtils;
import java.io.FileInputStream;
import java.io.File;
import java.nio.file.Files;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class BaseTest
{
    protected WebDriver driver;
    protected Properties prop;

    @BeforeMethod
    public void setUp() throws Exception
    {
        // Load config.properties
        prop = new Properties();
        FileInputStream fis =
                new FileInputStream("src/main/resources/config.properties");
        prop.load(fis);

        String browser = prop.getProperty("browser");

        // Browser setup
        if (browser.equalsIgnoreCase("chrome"))
        {
            ChromeOptions options = new ChromeOptions();
            // options.addArguments("--headless=new");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }
        else if (browser.equalsIgnoreCase("edge"))
        {
            EdgeOptions options = new EdgeOptions();
            // options.addArguments("--headless=new");
            driver = new EdgeDriver(options);
            driver.manage().window().maximize();
        }
        else
        {
            throw new RuntimeException("Invalid browser name");
        }
        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(Long.parseLong(prop.getProperty("implicitWait")))
        );
        driver.get(prop.getProperty("url"));
    }

    @AfterMethod
    public void tearDown(ITestResult result)
    {
        if (driver != null)
        {
            try {
                WaitUtils.waitForPageLoad(driver);

                String timestamp = LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

                String status;
                if (result.getStatus() == ITestResult.SUCCESS)
                {
                    status = "PASS";
                }
                else if (result.getStatus() == ITestResult.FAILURE)
                {
                    status = "FAIL";
                }
                else
                {
                    status = "SKIP";
                }
                File src = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.FILE);

                File dest = new File(
                        prop.getProperty("screenshotPath")
                                + result.getName() + "_" + status + "_" + timestamp + ".png"
                );
                Files.createDirectories(dest.getParentFile().toPath());
                Files.copy(src.toPath(), dest.toPath());
            }
            catch (Exception e)
            {
            }
        }
        if (driver != null)
        {
            driver.quit();
        }
    }
}