package energycostchecker.listener;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

public class OpenFile {
	
	public void fileOpen(String message) throws PartInitException {		
		String[] filePath= message.split("file:");
		IFile inputFile = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(Path.fromOSString(filePath[1]));
		if(inputFile != null) {
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			IDE.openEditor(page, inputFile);
		}
		
	}

}
