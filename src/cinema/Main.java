package cinema;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		File file = new File("backup");
		if (!file.exists())
			file.createNewFile();

		Cinema cinema = null;
		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			Object o = ois.readObject();
			cinema = (Cinema) o;
		} catch (EOFException e) {
			cinema = new Cinema();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Scanner sc = new Scanner(System.in);
		MainManager m = new MainManager(sc, cinema);
		
		try{
		while (true) {
			m.printMenu();
			switch (sc.nextInt()) {
			case 1:
				System.out.print("Введіть пароль: ");
				if (sc.next().equals("1111")) {
					boolean isExit = false;

					while (!isExit) {
						m.printMenuAdmin1();
						switch (sc.nextInt()) {
						case 1:
							while (!isExit) {
								System.out.println("---Переглянути---");
								m.printMenuAdmin2();
								switch (sc.nextInt()) {
								case 1:
									m.listFilms();
									break;
								case 2:
									m.listHalls();
									break;
								case 3:
									m.listSessions();
									break;
								case 0:
									isExit = true;
									break;
								default:
									System.out.println("Хибний ввід");
									break;
								}
							}
							isExit = false;
							break;
						case 2:
							while (!isExit) {
								System.out.println("---Видалити---");
								m.printMenuAdmin2();
								switch (sc.nextInt()) {
								case 1:
									m.dellFilm();
									break;
								case 2:
									m.dellHall();
									break;
								case 3:
									m.dellSession();
									break;
								case 0:
									isExit = true;
									break;
								default:
									System.out.println("Хибний ввід");
									break;
								}
							}
							isExit = false;
							break;
						case 3:
							while (!isExit) {
								System.out.println("---Добавити---");
								m.printMenuAdmin2();
								switch (sc.nextInt()) {
								case 1:
									cinema.getFilms().add(m.newFilm());
									break;
								case 2:
									cinema.getHalls().add(m.newHall());
									break;
								case 3:
									m.addSession();
									break;
								case 0:
									isExit = true;
									break;
								default:
									System.out.println("Хибний ввід");
									break;
								}
							}
							isExit = false;
							break;
						case 0:
							isExit = true;
							break;
						default:
							System.out.println("Хибний ввід");
							break;
						}
					}
				} else {
					System.out.println("Пароль невірний!");
				}
				break;
			case 2:
				boolean isExit = false;

				while (!isExit) {
					m.printMenuUser();
					switch (sc.nextInt()) {
					case 1:
						m.sessionsByDay();
						break;
					case 2:
						m.sessionsByFilm();
						break;
					case 3:
						m.listFilms();
						break;
					case 0:
						isExit = true;
						break;
					default:
						System.out.println("Хибний ввід");
						break;
					}
				}
				isExit = false;
				break;
			case 0:
				try (FileOutputStream fos = new FileOutputStream(file);
						ObjectOutputStream oos = new ObjectOutputStream(fos);) {
					oos.writeObject(cinema);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
			default:
				System.out.println("Хибний ввід");
				break;
			}
		}
		}catch(InputMismatchException e){
			System.out.println("Хибний ввід");
		}
	}
}
