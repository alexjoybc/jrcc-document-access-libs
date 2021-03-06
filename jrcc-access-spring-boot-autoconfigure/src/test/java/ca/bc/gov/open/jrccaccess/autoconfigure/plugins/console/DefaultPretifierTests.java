package ca.bc.gov.open.jrccaccess.autoconfigure.plugins.console;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class DefaultPretifierTests {

	private Prettifier sut;
	
	@Before
	public void init() {
		sut = new DefaultPrettifier();
	}
	
	@Test
	public void with_input_over_100_should_truncate() {
		
		String validInput = String.join("", Collections.nCopies(200, String.valueOf('a')));
		String result = sut.prettify(validInput);
		
		assertEquals(104, result.length());
		
	}
	
	@Test
	public void with_input_100_should_be_intact() {
		
		String validInput = String.join("", Collections.nCopies(100, String.valueOf('a')));
		String result = sut.prettify(validInput);
		
		assertEquals(104, result.length());
		
	}
	
	@Test
	public void with_input_99_should_be_intact() {
		
		String validInput = String.join("", Collections.nCopies(99, String.valueOf('a')));
		String result = sut.prettify(validInput);
		
		assertEquals(99, result.length());
		
	}
	
}
