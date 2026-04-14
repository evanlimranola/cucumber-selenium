package io.cucumber.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class BasicAuthPage extends Page{

    public BasicAuthPage(ChromeDriver driver) {
        super(driver);
        System.out.println("Homepage title is : " + getTitle().getText());
    }

    @FindBy(css = "h3")
    private WebElement title;

    @FindBy(xpath = "//p")
    private WebElement congratulationElement;

    public WebElement getTitle() {
        return title;
    }

    public WebElement getCongratulationElement() {
        return congratulationElement;
    }

    public void verifyPageReach() {
        Assertions.assertEquals("Basic Auth", getTitle().getText());
    }
    public void verifyCongratulationsVisible() {
        Assertions.assertEquals("Congratulations! You must have the proper credentials.", getCongratulationElement().getText());
    }
}
