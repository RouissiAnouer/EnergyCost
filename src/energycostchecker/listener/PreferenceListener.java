package energycostchecker.listener;

import java.io.InputStream;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import energycostchecker.Activator;
import energycostchecker.model.CostCheckerModel;

public class PreferenceListener {
	
	private CostCheckerModel model;

	
	public PreferenceListener() {
		model = Activator.getDefault().getModel();
	}
	IPropertyChangeListener PrefListener = new IPropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent event) {
			InputStream is = this.getClass().getResourceAsStream("/Resources/"+event.getNewValue()+".csv");
			try {
				model.Clear();
				model.doLoad(is, true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	public IPropertyChangeListener getPrefListener() {
		return PrefListener;
	}

}
