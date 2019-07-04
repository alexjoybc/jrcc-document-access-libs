package ca.gov.bc.open.jrccaccess.libs;

/**
 * Specification for the document output services
 * @author alexjoybc
 *
 */
public interface DocumentOutput {

	/**
	 * Sends a document to the desired output
	 * @param content The content of the document
	 * @param documentInfo The document informations
	 * @param transactionInfo The transaction Informations
	 */
	void send(String content, TransactionInfo transactionInfo);
	
	
}