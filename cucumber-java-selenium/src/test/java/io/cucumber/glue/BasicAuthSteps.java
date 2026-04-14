package io.cucumber.glue;

import io.cucumber.core.Context;
import io.cucumber.core.Manager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.pages.BasicAuthPage;
import org.openqa.selenium.WebDriver;

public class BasicAuthSteps extends Context {

    BasicAuthPage basicAuthPage;
    WebDriver driver;

    public BasicAuthSteps(Manager manager) {
        super(manager);
        this.driver = manager.getDriver();
        this.basicAuthPage = new BasicAuthPage(manager.getDriver());
    }

    @Then("Congratulations should be displayed")
    public void congratulationsVisible() {
        basicAuthPage.verifyCongratulationsVisible();
    }

    @And("valid credentials are supplied")
    public void basicAuthLoginSuccess() {
        basicAuthPage.verifyPageReach();
    }
}
