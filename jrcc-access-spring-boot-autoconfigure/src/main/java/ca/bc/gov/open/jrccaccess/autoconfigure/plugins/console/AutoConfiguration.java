package ca.bc.gov.open.jrccaccess.autoconfigure.plugins.console;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configure the console output plugin
 * @author alexjoybc
 * @since 0.3.0
 *
 */
@Configuration
@ComponentScan
@ConditionalOnExpression("'${bcgov.access.input.plugin}' == 'console' || '${bcgov.access.output.plugin}' == 'console'")
@EnableConfigurationProperties(ConsoleOutputProperties.class)
public class AutoConfiguration {

}
