package is.ru.hugb;

import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
public class CalculatorTest
{

	@Rule
    public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testEmptyString()
	{
		assertEquals(0, StringCalculator.add(""));
	}
	
	@Test
	public void testOneNumber()
	{
		assertEquals(1, StringCalculator.add("1"));
	}
	
	@Test
	public void testTwoNumbers()
	{
		assertEquals(3, StringCalculator.add("1,2"));
	}
	@Test
	public void testMultipleNumbers()
	{
		assertEquals(6, StringCalculator.add("1,2,3"));
	}
	@Test
	public void testNewLineInString()
	{
		assertEquals(6, StringCalculator.add("1,2\n3"));
	}

	@Test
	public void testNegativeNumbers()
	{
		thrown.expect(IllegalArgumentException.class);
		StringCalculator.add("-1,2");
	}
	@Test
	public void testMultipleNegativeNumbers()
	{
		thrown.expectMessage("Negatives not allowed: -3, -2");
		StringCalculator.add("-3,2,-2,3");
	}
	@Test
	public void testBigNumbers()
	{
		assertEquals(7,StringCalculator.add("10002,3,10003,4"));
	}
	@Test
	public void testDelimeter()
	{
		assertEquals(7,StringCalculator.add("///\n3/4"));
		assertEquals(11,StringCalculator.add("//;\n3;4\n4"));
		assertEquals(12,StringCalculator.add("//&\n1001\n4&4\n4"));
		assertEquals(18,StringCalculator.add("//!\n3!4!1054!11"));
		assertEquals(22,StringCalculator.add("//%\n3%4%5%6%4"));
		assertEquals(22,StringCalculator.add("//f%\n3f%4f%5f%6f%4"));
	}
}