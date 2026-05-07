package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.GiftCardPage;

public class TC21_GiftCardValidationTest extends BaseTest {

    @Test(priority = 21)
    public void tc21_clickOnGiftCard() {

        SoftAssert softAssert = new SoftAssert();

        GiftCardPage giftCardPage = new GiftCardPage(driver);
        giftCardPage.navigateToGiftCardPage();

        softAssert.assertTrue(
                giftCardPage.isGiftCardPageDisplayed(),
                "Gift Card page not opened"
        );

        softAssert.assertAll();
    }
}































