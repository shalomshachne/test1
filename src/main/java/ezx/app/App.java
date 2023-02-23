package ezx.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eztech.app.ApplicationUtility;

public class App {

	private static final Logger LOG = LogManager.getFormatterLogger(App.class);
	
	
	
	public static void main(String[] args) {
		App app = new App();
		LOG.info("main(): starting %s, version=%s", app, ApplicationUtility.getApplicationVersion(app));
		
	}

}
