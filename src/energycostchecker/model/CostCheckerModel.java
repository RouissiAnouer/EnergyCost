package energycostchecker.model;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class CostCheckerModel {

	private Map<String, Integer> data;
	
	public CostCheckerModel() {
		data = new HashMap<String, Integer>(1024);
	}
	
	public int getCostModel(String name) {
		if (data.containsKey(name)) {
			return data.get(name);
		} else {
			return 0;
		}
	}
	
	public void Clear() {
		this.data.clear();
	}
	
	public void doLoad(InputStream input, boolean override) throws Exception {
		Scanner scanner = new Scanner(input);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			this.doLoad(line, override);
		}
		scanner.close();
	}

	@SuppressWarnings("resource")
	private void doLoad(String line, boolean override) throws Exception {
		Scanner scanner = new Scanner(line);
		scanner.useDelimiter(",");
		while (scanner.hasNext()) {
			String name = scanner.next();
			int cost = scanner.nextInt();
			if (data.containsKey(name)) {
				if (override) {
					data.put(name, cost);
				} else {
					throw new Exception(name);
				}
			} else {
				data.put(name, cost);
			}
		}
		scanner.close();
	}
	
}
