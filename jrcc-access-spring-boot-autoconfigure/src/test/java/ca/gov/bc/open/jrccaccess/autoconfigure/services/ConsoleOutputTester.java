package ca.gov.bc.open.jrccaccess.autoconfigure.services;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.gov.bc.open.jrccaccess.autoconfigure.plugins.console.ConsoleOutput;
import ca.gov.bc.open.jrccaccess.autoconfigure.plugins.console.Prettifier;
import ca.gov.bc.open.jrccaccess.libs.TransactionInfo;

public class ConsoleOutputTester {

	private ConsoleOutput sut;
	
	@Mock
	private Prettifier prettifier;
	
	@Before
	public void Init() {
		MockitoAnnotations.initMocks(this);
		sut = new ConsoleOutput(this.prettifier);
	}
	
	@Test
	public void send_with_valid_input_should_print_to_console() {
	
		sut.send("my content", new TransactionInfo("my file.txt", "bcgov", LocalDateTime.now()));
		
	}
	
	
}
