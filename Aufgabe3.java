import java.util.Random;
import java.util.Scanner;

public class Aufgabe3 {

	public static void main(String[] args) {
		Map map = new Map();
		map.setBorders();
		map.setFood();
		map.setObstacles();
		Hamster hamster = new Hamster();
		Game game = new Game(hamster, map);
		game.placeHamster();
		// Initialisierung Ende

		Scanner sc = new Scanner(System.in);
		char input = '\u0000';
		System.out.println("Sie können ihren Hamster mit wasd bewegen.");
		System.out.println("Um das Programm zu beenden geben Sie 0 ein.");
		System.out.println("Um die bereits gegessenen Krümel auszugeben, geben sie i ein.");
		game.getMap().printMap();
		boolean endInput = false;
		do {
			input = sc.next().charAt(0);
			endInput = input == '0';
			switch (input) {
			case 's':
				game.goDown();
				break;
			case 'w':
				game.goUp();
				break;
			case 'a':
				game.goLeft();
				break;
			case 'd':
				game.goRight();
				break;
			case 'i':
				game.printFood();
				break;
			default:
				break;
			}
			game.getMap().printMap();

		} while (!endInput);
		System.out.println("Sie haben das Spiel beendet.");
	}

}

class Game {
	private Hamster hamster;
	private Map map;

	public Game(Hamster hamster, Map map) {
		this.hamster = hamster;
		this.map = map;
	}

	public void placeHamster() {
		int i = 1;
		while (map.getMap()[1][i] == '#') {
			i++;
		}
		map.getMap()[1][i] = hamster.getDirection();
		hamster.getPosition().setxPosition(i);
		hamster.getPosition().setyPosition(1);

	}

	public void deleteOldPosition(Position position) {
		map.getMap()[position.getyPosition()][position.getxPosition()] = ' ';
	}

	public void changeMapPosition(Position position, char direction) {
		map.getMap()[position.getyPosition()][position.getxPosition()] = direction;
	}

	public void goRight() {
		hamster.setDirection('>');
		if (map.getMap()[hamster.getPosition().getyPosition()][hamster.getPosition().getxPosition() + 1] != '#') {
			deleteOldPosition(hamster.getPosition());
			hamster.getPosition().setxPosition(hamster.getPosition().getxPosition() + 1);
			checkForFood(hamster.getPosition());
			changeMapPosition(hamster.getPosition(), hamster.getDirection());
		} else {
			changeMapPosition(hamster.getPosition(), hamster.getDirection());
		}
	}

	public void goLeft() {
		hamster.setDirection('<');
		if (map.getMap()[hamster.getPosition().getyPosition()][hamster.getPosition().getxPosition() - 1] != '#') {
			deleteOldPosition(hamster.getPosition());
			hamster.getPosition().setxPosition(hamster.getPosition().getxPosition() - 1);
			checkForFood(hamster.getPosition());
			changeMapPosition(hamster.getPosition(), hamster.getDirection());
		} else {
			changeMapPosition(hamster.getPosition(), hamster.getDirection());
		}
	}

	public void goDown() {
		hamster.setDirection('v');
		if (map.getMap()[hamster.getPosition().getyPosition() + 1][hamster.getPosition().getxPosition()] != '#') {
			deleteOldPosition(hamster.getPosition());
			hamster.getPosition().setyPosition(hamster.getPosition().getyPosition() + 1);
			checkForFood(hamster.getPosition());
			changeMapPosition(hamster.getPosition(), hamster.getDirection());
		} else {
			changeMapPosition(hamster.getPosition(), hamster.getDirection());
		}
	}

	public void goUp() {
		hamster.setDirection('^');
		if (map.getMap()[hamster.getPosition().getyPosition() - 1][hamster.getPosition().getxPosition()] != '#') {
			deleteOldPosition(hamster.getPosition());
			hamster.getPosition().setyPosition(hamster.getPosition().getyPosition() - 1);
			checkForFood(hamster.getPosition());
			changeMapPosition(hamster.getPosition(), hamster.getDirection());
		} else {
			changeMapPosition(hamster.getPosition(), hamster.getDirection());
		}
	}

	public void checkForFood(Position position) {
		if (map.getMap()[position.getyPosition()][position.getxPosition()] == '*') {
			hamster.setAmountOfConsumedFood(hamster.getAmountOfConsumedFood() + 1);
		}
	}

	public void printFood() {
		System.out.println("Der Hamster hat bisher " + hamster.getAmountOfConsumedFood() + " Krümel gegessen.");
	}

	public Hamster getHamster() {
		return hamster;
	}

	public void setHamster(Hamster hamster) {
		this.hamster = hamster;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

}

class Hamster {
	private int amountOfConsumedFood = 0;
	private char direction = 'v';
	private Position position = new Position();

	public int getAmountOfConsumedFood() {
		return amountOfConsumedFood;
	}

	public void setAmountOfConsumedFood(int amountOfConsumedFood) {
		this.amountOfConsumedFood = amountOfConsumedFood;
	}

	public void eat() {
		amountOfConsumedFood++;
	}

	public char getDirection() {
		return direction;
	}

	public void setDirection(char direction) {
		this.direction = direction;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

}

class Map {
	private char[][] map = new char[10][20];
	private int amountOfMapElements = 6;

	public void setAmountOfMapElements(int amountOfMapElements) {
		this.amountOfMapElements = amountOfMapElements;
	}

	public void setBorders() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (i == 0 || i == map.length - 1) {
					map[i][j] = '#';
				} else if (j == 0 || j == map[0].length - 1) {
					map[i][j] = '#';
				}
			}
		}
	}

	public void setObstacles() {
		Random rand = new Random();
		for (int i = 0; i < amountOfMapElements; i++) {
			map[rand.nextInt(map.length - 2) + 1][rand.nextInt(map[0].length - 2) + 1] = '#';
		}
	}

	public void setFood() {
		Random rand = new Random();
		for (int i = 0; i < amountOfMapElements; i++) {
			map[rand.nextInt(map.length - 2) + 1][rand.nextInt(map[0].length - 2) + 1] = '*';
		}
	}

	public void printMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j]);
			}
			System.out.print("\n");
		}
	}

	public char[][] getMap() {
		return map;
	}

	public void setMap(char[][] map) {
		this.map = map;
	}

	public int getAmountOfMapElements() {
		return amountOfMapElements;
	}

}

class Position {
	private int xPosition;
	private int yPosition;

	public int getxPosition() {
		return xPosition;
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

}