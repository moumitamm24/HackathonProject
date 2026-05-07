package tests;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.GiftCardPage;

public class TC24_InvalidSenderEmailTest extends BaseTest
{
    private static final Logger log =
            LogManager.getLogger(TC24_InvalidSenderEmailTest.class);

    @Test(priority = 24)
    public void tc24_invalidSenderEmailValidation()
    {
        SoftAssert softAssert = new SoftAssert();
        GiftCardPage giftCardPage = new GiftCardPage(driver);

        giftCardPage.navigateToGiftCardPage();
        softAssert.assertTrue(giftCardPage.isGiftCardPageDisplayed());

        giftCardPage.selectBirthdayGiftCard();
        softAssert.assertTrue(giftCardPage.isBirthdayGiftCardSelected());

        giftCardPage.enterInvalidSenderEmail();

        softAssert.assertTrue(
                giftCardPage.isInvalidEmailErrorDisplayed(),
                "Invalid email error not displayed"
        );
        String errorText = giftCardPage.getInvalidEmailErrorText();
        log.info("Captured Email Error Message: {}", errorText);
        softAssert.assertAll();
    }
}







































