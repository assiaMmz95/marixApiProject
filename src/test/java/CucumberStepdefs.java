import org.junit.Assert;

public class CucumberStepdefs {
    int result;
    @io.cucumber.java.en.Given("I have a Calculator")
    public void iHaveACalculator() {
    }

    @io.cucumber.java.en.When("I add {int} and {int}")
    public void iAddAnd(int arg0, int arg1) {
        result = arg0 + arg1;
    }

    @io.cucumber.java.en.Then("the result should be {int}")
    public void theResultShouldBe(int arg0) {
        Assert.assertEquals(arg0, result);
    }
}
