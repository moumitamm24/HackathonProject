package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.io.FileHandler;

public class ScreenShotUtil {

    public static void takeScreenshot(WebDriver driver, String testName)
    {
        try
        {
            File screenshotDir = new File("screenshots");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdir();
            }

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(screenshotDir,
                    testName + "_" + timeStamp + ".png");

            FileHandler.copy(srcFile, destFile);

        } catch (IOException e) {
            System.out.println("Screenshot capture failed: " + e.getMessage());
        }
    }
}