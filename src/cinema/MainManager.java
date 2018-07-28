package cinema;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainManager {
	private Scanner sc;
	private Cinema cin;

	public MainManager(Scanner sc, Cinema cin) {
		this.sc = sc;
		this.cin = cin;
	}

	public void sessionsByFilm() throws InputMismatchException{
		if (!cin.getFilms().isEmpty()) {
			System.out.println("Виберіть фільм зі списку: ");
			listFilms();
		}
		System.out.println("Введіть назву фільму:");
		String name = sc.next();
		boolean b = false;
		for (Session s : cin.getSessions()) {
			if (s.getFilm().getName().equalsIgnoreCase(name)) {
				System.out.println(s);
				b = true;
			}
		}
		if(!b)System.out.println("Сеансів з таким фільмом немає");
	}

	@SuppressWarnings("deprecation")
	public void sessionsByDay() {
		System.out.println("Введіть день:");
		Date d = newDate();
		boolean b = false;
		for (Session s : cin.getSessions()) {
			if (s.getDate().getYear() == d.getYear() && s.getDate().getMonth() 
					== d.getMonth() && s.getDate().getDate() 
					== d.getDate()) {
				System.out.println(s);
				b = true;
			}
		}
		if(!b)System.out.println("У цей день немає сеансів");
	}

	public void addSession() throws InputMismatchException{
		Film f = new Film();
		boolean isExit = false;

		while (!isExit) {
			System.out.println("[1] Вибрати фільм зі списку");
			System.out.println("[2] добавити новий фільм");
			switch (sc.nextInt()) {
			case 1:
				if (!cin.getFilms().isEmpty()) {
					System.out.println("Виберіть фільм зі списку: ");
					listFilms();
					String name = null;
					while(!isExit){
						System.out.println("Введіть назву фільму:");
						name = sc.next();
						if(!isEqualsFilm(name)){
							System.out.println("Такого фільму немає у списку");
						}else{
							isExit = true;
						}
					}
					isExit = false;
					f = cin.getFilm(name);
					isExit = true;
				} else {
					System.out
							.println("Список фільмів пустий, добавте новий фільм");
				}
				break;
			case 2:
				f = newFilm();
				cin.getFilms().add(f);
				isExit = true;
				break;
			default:
				System.out.println("Хибний ввід");
				break;
			}
		}

		Hall h = new Hall();
		isExit = false;
		while (!isExit) {
			System.out.println("[1] Вибрати зал зі списку");
			System.out.println("[2] добавити новий зал");
			switch (sc.nextInt()) {
			case 1:
				if (!cin.getHalls().isEmpty()) {
					System.out.println("Виберіть зал зі списку: ");
					listHalls();
					int num = 0;
					while(!isExit){
						System.out.println("Введіть номер залу:");
						num = sc.nextInt();
						if(!isEqualsHall(num)){
							System.out.println("Такого залу не існує");
						}else{
							isExit = true;
						}
					}
					isExit = false;
					h = cin.getHall(num);
					isExit = true;
				} else {
					System.out
							.println("Список залів пустий, добавте новий зал");
				}
				break;
			case 2:
				h = newHall();
				cin.getHalls().add(h);
				isExit = true;
				break;
			default:
				System.out.println("Хибний ввід");
				break;
			}
		}
		System.out.println("Введіть дату сеансу");
		Date d = newTime(newDate());
		boolean b = false;
		for(Session s : cin.getSessions()){
			if(s.getHall().getNumber() == h.getNumber() && Math.abs(s.getDate().getTime()-d.getTime()) < 7_200_000){
				System.out.println("В цей час цей зал зайнятий, спробуйте ще раз");
				b = true;
			}
		}
		if(!b)cin.getSessions().add(new Session(f, h, d));
	}

	public Film newFilm() throws InputMismatchException{
		boolean isExit = false;
		String name = null;
		if (!cin.getFilms().isEmpty()){
			while(!isExit){
				System.out.println("Введіть назву фільму:");
				name = sc.next();
				if(isEqualsFilm(name)){
					System.out.println("Такий фільм уже існує");
				}else{
					isExit = true;
				}
			}
		}else{
			System.out.println("Введіть назву фільму:");
			name = sc.next();
		}
		System.out.println("Введіть вартість квитка, грн:");
		int price = sc.nextInt();
		return new Film(name, price);
	}

	public Hall newHall() throws InputMismatchException{
		boolean isExit = false;
		int num = 0;
		if (!cin.getHalls().isEmpty()){
			while(!isExit){
				System.out.println("Введіть номер залу:");
				num = sc.nextInt();
				if(isEqualsHall(num)){
					System.out.println("Такий зал уже існує");
				}else{
					isExit = true;
				}
			}
		}else{
			System.out.println("Введіть номер залу:");
			num = sc.nextInt();
		}
		System.out.println("Введіть кількість місць:");
		int seats = sc.nextInt();
		return new Hall(num, seats);
	}
	
	private boolean isEqualsHall(int num){
		for(Hall h : cin.getHalls()){
			if(h.getNumber() == num){
				return true;
			}
		}
		return false;
	}
	
	private boolean isEqualsFilm(String name){
		for(Film f : cin.getFilms()){
			if(f.getName().equalsIgnoreCase(name)){
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	private Date newDate() throws InputMismatchException{
		Date date = new Date(0);
		System.out.print("Введіть рік - ");
		int year = sc.nextInt();
		date.setYear(year);
		int month = 0;
		while (month < 1 || month > 12) {
			System.out.print("Введіть номер місяця - ");
			month = sc.nextInt();
			date.setMonth(month - 1);
		}
		int day = 0;
		while (day < 1 || day > 31) {
			System.out.print("Введіть день - ");
			day = sc.nextInt();
			date.setDate(day);
		}
		return date;
	}

	@SuppressWarnings("deprecation")
	private Date newTime(Date date) throws InputMismatchException{
		int hours = -1;
		while (hours < 0 || hours > 24) {
			System.out.print("Введіть години - ");
			hours = sc.nextInt();
			date.setHours(hours);
		}
		int minutes = -1;
		while (minutes < 0 || minutes > 60) {
			System.out.print("Введіть хвилини - ");
			minutes = sc.nextInt();
			date.setMinutes(minutes);
		}
		return date;
	}

	public void dellFilm() throws InputMismatchException{
		if (!cin.getFilms().isEmpty()) {
			System.out.println("Виберіть фільм зі списку: ");
			listFilms();
			System.out.println("Введіть назву фільму:");
			String name = sc.next();
			cin.delFilm(name);
			cin.delSession(name);
		} else {
			System.out.println("Список фільмів пустий");
		}
	}

	public void dellHall() throws InputMismatchException{
		if (!cin.getHalls().isEmpty()) {
			System.out.println("Виберіть зал зі списку: ");
			listHalls();
			System.out.println("Введіть номер залу:");
			int num = sc.nextInt();
			cin.delHall(num);
			cin.delSession(num);
		} else {
			System.out.println("Список залів пустий");
		}
	}

	public void dellSession() throws InputMismatchException{
		if (!cin.getSessions().isEmpty()) {
			System.out.println("Виберіть дату сеансу зі списку: ");
			listSessions();
			System.out.println("Введіть дату сеансу");
			cin.delSession(newTime(newDate()));
		} else {
			System.out.println("Список сеансів пустий");
		}
	}

	public void listFilms() {
		if (!cin.getFilms().isEmpty()) {
			for (Film f : cin.getFilms()) {
				System.out.println(f);
			}
		} else {
			System.out.println("Список фільмів пустий");
		}
	}

	public void listHalls() {
		if (!cin.getHalls().isEmpty()) {
			for (Hall h : cin.getHalls()) {
				System.out.println(h);
			}
		} else {
			System.out.println("Список залів пустий");
		}
	}

	public void listSessions() {
		if (!cin.getSessions().isEmpty()) {
			for (Session s : cin.getSessions()) {
				System.out.println(s + "\n");
			}
		} else {
			System.out.println("Список сеанів пустий");
		}
	}

	public void printMenu() {
		System.out.println("---Головне меню---");
		System.out.println("[1] Адміністратор");
		System.out.println("[2] Користувач");
		System.out.println("[0] Вийти");
	}

	public void printMenuAdmin1() {
		System.out.println("---Адміністратор---");
		System.out.println("[1] Переглянути");
		System.out.println("[2] Видалити");
		System.out.println("[3] Добавити");
		System.out.println("[0] Назад");
	}

	public void printMenuAdmin2() {
		System.out.println("[1] Фільми");
		System.out.println("[2] Зали");
		System.out.println("[3] Сеанси");
		System.out.println("[0] Назад");
	}

	public void printMenuUser() {
		System.out.println("---Користувач---");
		System.out.println("Переглянути: ");
		System.out.println("[1] Сеанси за днем");
		System.out.println("[2] Сеанси за фільмом");
		System.out.println("[3] Список фільмів");
		System.out.println("[0] Назад");
	}
}
