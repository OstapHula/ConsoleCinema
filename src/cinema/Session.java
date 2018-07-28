package cinema;

import java.io.Serializable;
import java.util.Date;

public class Session implements Serializable{

	private static final long serialVersionUID = 3075525237753248544L;
	private Film film;
	private Hall hall;
	private Date date;
	
	public Session(Film film, Hall hall, Date date) {
		this.film = film;
		this.hall = hall;
		this.date = date;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@SuppressWarnings("deprecation")
	@Override
	public String toString() {
		return "Сеанс: " + date.getDate() + "/" + (int)(date.getMonth()+1) + "/" + date.getYear() + " " + date.getHours() + ":" + date.getMinutes() 
				+ "\nФільм: " + film.getName() 
				+ "\nЗал: " + hall.getNumber();
	}
	
	
}
