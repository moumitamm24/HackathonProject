package tests;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.GiftCardPage;

public class TC25_InvalidMobileNumberTest extends BaseTest
{

    private static final Logger log =
            LogManager.getLogger(TC25_InvalidMobileNumberTest.class);

    @Test(priority = 25)
    public void tc25_invalidMobileNumberValidation() {

        SoftAssert softAssert = new SoftAssert();

        GiftCardPage giftCardPage = new GiftCardPage(driver);

        giftCardPage.navigateToGiftCardPage();
        softAssert.assertTrue(
                giftCardPage.isGiftCardPageDisplayed(),
                "Gift Card page not displayed"
        );

        giftCardPage.selectBirthdayGiftCard();
        softAssert.assertTrue(
                giftCardPage.isBirthdayGiftCardSelected(),
                "Birthday Gift Card not selected"
        );

        giftCardPage.enterInvalidMobileNumber();

        softAssert.assertTrue(
                giftCardPage.isInvalidMobileErrorDisplayed(),
                "Invalid mobile number error message not displayed"
        );

        String errorText = giftCardPage.getInvalidMobileErrorText();
        log.info("Captured Mobile Error Message: {}", errorText);

        softAssert.assertTrue(
                errorText.toLowerCase().contains("mobile"),
                "Unexpected mobile validation error text"
        );
        softAssert.assertAll();
    }
}