import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

import org.junit.Test;

public class SelectionScreenTest {

	@Test
	public void testStart() {
		assertEquals(SelectionScreen.seatsLeft, 120);
		assertEquals(SelectionScreen.businessSeat, false);
	}

}
