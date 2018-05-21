package energycostchecker;


import java.io.InputStream;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import energycostchecker.listener.PreferenceConstants;
import energycostchecker.model.CostCheckerModel;

import org.eclipse.jface.preference.*;



/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "EnergyCostChecker"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	private CostCheckerModel model;
	
	IPreferenceStore store;
	
	String str;

	public CostCheckerModel getModel() {
		return model;
	}
	
	

	
	public Activator() {
		model = new CostCheckerModel();
	}
	

	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		store = Activator.getDefault().getPreferenceStore();
		str = store.getString(PreferenceConstants.P_CHOICE);
		InputStream is = this.getClass().getResourceAsStream("/Resources/"+str+".csv");
		if(is == null) {
			InputStream isReplace = this.getClass().getResourceAsStream("/Resources/0.0.1.csv");
			model.doLoad(isReplace, true);
		}else {
		model.doLoad(is, true);
		}
	}


	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	
	public static Activator getDefault() {
		return plugin;
	}

	
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
