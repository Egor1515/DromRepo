package helpDesk;

import core.SeleniumPage;
import org.openqa.selenium.support.PageFactory;
import readProperties.ConfigProvider;

public class FavoritePage extends SeleniumPage {
    public FavoritePage() {
        driver.get(ConfigProvider.FAVORITEURL);
        PageFactory.initElements(driver, this);
    }
}
