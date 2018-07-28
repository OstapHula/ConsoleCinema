package cinema;

import java.io.Serializable;

public class Film implements Serializable{
	private static final long serialVersionUID = 8193632509642421580L;
	private String name;
	private int price;
	
	public Film(){}
	
	public Film(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Назва фільму: " + name + ", ціна = " + price;
	}
	
}
