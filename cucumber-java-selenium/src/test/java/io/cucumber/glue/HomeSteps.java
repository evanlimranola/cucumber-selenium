package io.cucumber.glue;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.core.Context;
import io.cucumber.core.Manager;
import io.cucumber.java.en.Then;
import io.cucumber.pages.HomePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class HomeSteps extends Context {

  HomePage homePage;
  WebDriver driver;

  public HomeSteps(Manager manager) {
    super(manager);
    this.driver = manager.getDriver();
    this.homePage = new HomePage(manager.getDriver());
  }

  @Then("the displayed list of listed examples is as expected")
  public void verifyList() {
    List<String> expectedExamples = List.of(
            "A/B Testing", "Add/Remove Elements", "Basic Auth (user and pass: admin)",
                    "Broken Images", "Challenging DOM", "Context Menu", "Digest Authentication (user and pass: admin)",
                    "Disappearing Elements", "Drag and Drop", "Dropdown", "Dynamic Content", "Dynamic Controls",
                    "Dynamic Loading", "Entry Ad", "Exit Intent", "File Download", "File Upload", "Floating Menu",
                    "Forgot Password", "Form Authentication", "Geolocation", "Horizontal Slider", "Infinite Scroll",
                    "Inputs", "JavaScript Alerts", "JavaScript onload event error", "Key Presses", "Large & Deep DOM",
                    "Multiple Windows", "Nested Frames", "Notification Messages", "Redirect Link", "Secure File Download",
                    "Shadow DOM", "Shifting Content", "Slow Resources", "Sortable Data Tables", "Status Codes", "Typos", "WYSIWYG Editor"
    );

    List<String> availableExamples = homePage.getDisplayedExamples();

    List<String> missingExamples = new ArrayList<>(expectedExamples);
    missingExamples.removeAll(availableExamples);

    List<String> extraExamples = new ArrayList<>(availableExamples);
    extraExamples.removeAll(expectedExamples);

    Assertions.assertTrue(missingExamples.isEmpty(),
            "Failed! Missing available examples than expected." + missingExamples);

    Assertions.assertTrue(extraExamples.isEmpty(),
            "Failed! Extra items are available other than the expected." + extraExamples);
  }

  @And("the 'Basic Auth' example is opened")
  public void clickBasicAuth() {
    ((HasAuthentication) driver).register(UsernameAndPassword.of("admin", "admin"));
    homePage.clickBasicAuth();
  }
}