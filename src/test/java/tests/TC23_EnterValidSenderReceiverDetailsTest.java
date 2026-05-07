package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.GiftCardPage;

public class TC23_EnterValidSenderReceiverDetailsTest extends BaseTest {

    @Test(priority = 23)
    public void tc23_enterValidSenderReceiverDetails()
    {
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

        giftCardPage.enterValidSenderAndReceiverDetails();

        softAssert.assertTrue(
                giftCardPage.isFormAccepted(),
                "Form did not accept valid sender and receiver details"
        );
        softAssert.assertAll();
    }
}



































