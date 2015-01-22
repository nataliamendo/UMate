package EAProject.UMate;

import java.util.ResourceBundle;

import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class uMateApplication extends ResourceConfig {
	public uMateApplication() {
		super();

		register(DeclarativeLinkingFeature.class);
		//ResourceBundle bundle = ResourceBundle.getBundle("application");

	}
}
