package io.cucumber.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends Page {

  public HomePage(ChromeDriver driver) {
    super(driver);
    System.out.println("Homepage title is : " + getTitle().getText());
  }

  @FindBy(css = "h1")
  private WebElement title;

  @FindBy(xpath = "//li")
  private List<WebElement> availableExamples;

  @FindBy(xpath = "//a[@href='/basic_auth']")
  private WebElement basicAuthLink;

  public WebElement getTitle() {
    return title;
  }

  public void verifyHomePage() {
    Assertions.assertEquals("Welcome to the-internet", getTitle().getText());
  }

  public List<String> getDisplayedExamples() {
    List<String> listText = new ArrayList<>();

    for(WebElement element : availableExamples) {
      listText.add(element.getText().trim());
    }

    return listText;
  }

  public void clickBasicAuth() {
    basicAuthLink.click();
  }
}
