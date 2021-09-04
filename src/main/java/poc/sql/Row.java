package poc.sql;

import java.util.ArrayList;
import java.util.List;

public class Row {
	
	private final List<String> data;
	
	public Row() {
		this.data = new ArrayList<>();
	}

	public void add(String value) {
		this.data.add(value);
	}
}
