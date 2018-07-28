package cinema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Cinema implements Serializable{

	private static final long serialVersionUID = 7871360856095134012L;
	private List<Film> films;
    private List<Hall> halls;
    private List<Session> sessions;

    public Cinema(){
    films = new ArrayList<>();
    halls = new ArrayList<>();
    sessions = new ArrayList<>();}

	public List<Film> getFilms() {
		return films;
	}

	public List<Hall> getHalls() {
		return halls;
	}

	public List<Session> getSessions() {
		return sessions;
	}
	
	public Film getFilm(String name){
		Iterator<Film> iter = films.iterator();
		while(iter.hasNext()){
			Film f = iter.next();
			if(f.getName().equalsIgnoreCase(name)){
				return f;
			}
		}
		return null;
	}
	
	public Hall getHall(int num){
		Iterator<Hall> iter = halls.iterator();
		while(iter.hasNext()){
			Hall h = iter.next();
			if(h.getNumber() == num){
				return h;
			}
		}
		return null;
	}
	
	public Session getSession(Date date){
		Iterator<Session> iter = sessions.iterator();
		while(iter.hasNext()){
			Session s = iter.next();
			if(s.equals(date)){
				return s;
			}
		}
		return null;
	}
	
	public void delFilm(String name){
		Iterator<Film> iter = films.iterator();
		while(iter.hasNext()){
			Film f = iter.next();
			if(f.getName().equalsIgnoreCase(name)){
				iter.remove();
			}
		}
	}
    
	public void delHall(int num){
		Iterator<Hall> iter = halls.iterator();
		while(iter.hasNext()){
			Hall h = iter.next();
			if(h.getNumber() == num){
				iter.remove();
			}
		}
	}
	
	public void delSession(Date date){
		Iterator<Session> iter = sessions.iterator();
		while(iter.hasNext()){
			Session s = iter.next();
			if(s.getDate().equals(date)){
				iter.remove();
			}
		}
	}
	
	public void delSession(String name){
		Iterator<Session> iter = sessions.iterator();
		while(iter.hasNext()){
			Session s = iter.next();
			if(s.getFilm().getName().equalsIgnoreCase(name)){
				iter.remove();
			}
		}
	}
	
	public void delSession(int num){
		Iterator<Session> iter = sessions.iterator();
		while(iter.hasNext()){
			Session s = iter.next();
			if(s.getHall().getNumber() == num){
				iter.remove();
			}
		}
	}
}
