package hu.bme.mit.train.tachograph;

import java.util.Date;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class TrainTachograph {
	private Table<Date, Integer, Integer> table = HashBasedTable.create();
	
	public void record(Date date, Integer joystickPosition, Integer referenceSpeed) {
		table.put(date, joystickPosition, referenceSpeed);
	}
	
	public boolean isEmpty() {
		return table.isEmpty();
	}
}
