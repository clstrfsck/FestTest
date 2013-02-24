package festtest;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.testng.testcase.FestSwingTestngTestCase;
import org.testng.annotations.Test;

public class CalculatorTest extends FestSwingTestngTestCase {

    private FrameFixture window;

    @Override
    protected void onSetUp() {
        Calculator frame = GuiActionRunner.execute(new GuiQuery<Calculator>() {
            @Override
            protected Calculator executeInEDT() {
                return new Calculator();
            }
        });
        // IMPORTANT: note the call to 'robot()'
        // we must use the Robot from FestSwingTestngTestCase
        window = new FrameFixture(robot(), frame);
        window.show(); // shows the frame to test
    }

    @Test
    public void simpleSum() {
        window.button("1").click();
        window.button("2").click();
        window.button("+").click();
        window.button("1").click();
        window.button("3").click();
        window.button("=").click();
        window.textBox("answer").requireText("25");
    }


    @Test
    public void simpleSubtract() {
        window.button("1").click();
        window.button("2").click();
        window.button("-").click();
        window.button("1").click();
        window.button("3").click();
        window.button("=").click();
        window.textBox("answer").requireText("-1");
    }

    @Test
    public void simpleProduct() {
        window.button("1").click();
        window.button("2").click();
        window.button("*").click();
        window.button("1").click();
        window.button("3").click();
        window.button("=").click();
        window.textBox("answer").requireText("156");
    }

    @Test
    public void simpleDivide() {
        window.button("1").click();
        window.button("5").click();
        window.button("6").click();
        window.button("/").click();
        window.button("1").click();
        window.button("2").click();
        window.button("=").click();
        window.textBox("answer").requireText("13");
    }
}