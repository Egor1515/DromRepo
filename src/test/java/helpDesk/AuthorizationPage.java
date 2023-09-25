package helpDesk;

import core.SeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthorizationPage extends SeleniumPage {

    @FindBy(css = "input#sign")
    private WebElement loginField;
    @FindBy(css = "input#password")
    private WebElement passwordField;
    @FindBy(css = "button#signbutton")
    private WebElement logInBtn;
    @FindBy(css = "a.auto-shy")
    private WebElement authPageEnterTitle;

    public AuthorizationPage() {
        driver.get("https://my.drom.ru/sign");
        PageFactory.initElements(driver, this);
    }


    public void authWithLoginAndPass(String login, String pass) {
        loginField.sendKeys(login);
        passwordField.sendKeys(pass);
        logInBtn.click();
    }

}
