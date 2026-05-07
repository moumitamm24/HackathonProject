package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.GiftCardPage;

public class TC22_SelectBirthdayEGVTest extends BaseTest {

    @Test(priority = 22)
    public void tc22_selectBirthdayEGVDesign()
    {
        SoftAssert softAssert = new SoftAssert();
        GiftCardPage giftCardPage = new GiftCardPage(driver);

        giftCardPage.navigateToGiftCardPage();
        softAssert.assertTrue(
                giftCardPage.isGiftCardPageDisplayed(),
                "Gift Card page not opened"
        );

        giftCardPage.selectBirthdayGiftCard();
        softAssert.assertTrue(
                giftCardPage.isBirthdayGiftCardSelected(),
                "Birthday E-Gift Voucher not selected"
        );
        softAssert.assertAll();
    }
}




































