package de.manigdni.tests.ams;

public class AggregatedData {
	private long key;
	private String value1;
	private String value2;

	public AggregatedData(Data1 d1, Data2 d2) {
		this.key = d1.getKey();
		this.value1 = d1.getValue();
		this.value2 = d2.getValue();
	}

	public long getKey() {
		return key;
	}

	public void setKey(long key) {
		this.key = key;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

}
