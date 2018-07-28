package cinema;

import java.io.Serializable;

public class Hall implements Serializable{
	private static final long serialVersionUID = 8650208462287385673L;
	private int number;
	private int seats;
	
	public Hall(){}
	
	public Hall(int number, int seats) {
		this.number = number;
		this.seats = seats;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	@Override
	public String toString() {
		return "Номер залу: " + number + ", кількість місць: " + seats;
	}

}
