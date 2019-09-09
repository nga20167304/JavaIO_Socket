package exercise1;

import java.util.Comparator;
import java.util.Date;

public class Device {
	String code;
	String name;
	String owner;
	Date inputDate;
	Integer warrantyYear;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	public Integer getWarrantyYear() {
		return warrantyYear;
	}

	public void setWarrantyYear(Integer warrantyYear) {
		this.warrantyYear = warrantyYear;
	}
}
class Sortbyyear implements Comparator<Device> {
	// Device for sorting in ascending order of
	// warrantyYeae
	public int compare(Device a, Device b) {
		return a.warrantyYear - b.warrantyYear;
	}
}
class SortbyyearDESC implements Comparator<Device> {
	// Device for sorting in DESC order of
	// warrantyYeae
	public int compare(Device a, Device b) {
		return b.warrantyYear - a.warrantyYear;
	}
}

