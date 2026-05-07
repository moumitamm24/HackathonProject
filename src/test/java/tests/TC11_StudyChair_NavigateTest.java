package tests;

import base.BaseTest;
import pages.StudyChairPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC11_StudyChair_NavigateTest extends BaseTest
{
    private static final Logger log =
            LogManager.getLogger(TC11_StudyChair_NavigateTest.class);

    @Test
    public void verifyNavigationToStudyChairs()
    {
        log.info("Starting TC11 - Verify Navigation to Study Chairs page");

        // Initialize Page Object
        StudyChairPage studyChairPage = new StudyChairPage(driver);

        //  Navigation handled by Page method
        studyChairPage.navigateToStudyChairs();
        log.info("Navigated to Study Chairs page");

        // Validation: Study Chairs page is opened
        boolean isNavigated = driver.getTitle().contains("Study Chairs");

        Assert.assertTrue(
                isNavigated,
                "Failed to navigate to Study Chairs page"
        );
    }
}