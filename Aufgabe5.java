import java.util.Scanner;

public class Aufgabe5 {

	public static void main(String[] args) {
		Spiel meinSpiel = new Spiel();
		Spieler[] competitors = new Spieler[2];
		competitors[0] = new Spieler("Spieler 1", 'X');
		competitors[1] = new Spieler("Spieler 2", 'O');
		meinSpiel.setSpieler(competitors);
		// Initialisierung Ende
		Scanner sc = new Scanner(System.in);
		int input = 0;
		boolean endInput = false;
		int i = 0;
		do {
			for (i = 0; i < meinSpiel.getSpieler().length; i++) {
				meinSpiel.getSpielfeld().printSpielfeld();
				System.out.println(meinSpiel.getSpieler()[i].getName() + " ("
						+ meinSpiel.getSpieler()[i].getSpielstein() + ") wirft in Spalte: ");
				input = sc.nextInt();
				meinSpiel.setzeSpielstein(input, meinSpiel.getSpieler()[i].getSpielstein());
				endInput = meinSpiel.isGameOver(meinSpiel.getSpieler()[i].getSpielstein());
				if (endInput) {
					break;
				}
			}

		} while (!endInput);
		System.out.println("Der Sieger ist: " + meinSpiel.getSpieler()[i].getName());
		System.out.println("========================================");
		System.out.println("Endbildschirm: ");
		System.out.println();
		meinSpiel.getSpielfeld().printSpielfeld();
		sc.close();
	}

}

class Spiel {
	private Spieler[] spieler = new Spieler[2];
	private Spielfeld spielfeld = new Spielfeld();

	public Spieler[] getSpieler() {
		return spieler;
	}

	public void setSpieler(Spieler[] spieler) {
		this.spieler = spieler;
	}

	public Spielfeld getSpielfeld() {
		return spielfeld;
	}

	public void setSpielfeld(Spielfeld spielfeld) {
		this.spielfeld = spielfeld;
	}

	public void setzeSpielstein(int spalte, char spielStein) {
		spielfeld.setSpalte(spalte, spielStein);
	}

	public boolean isGameOver(char spielStein) {
		boolean gameOver = false;
		if (spielfeld.fourInColumn(spielStein) || spielfeld.fourInRow(spielStein)
				|| spielfeld.leftToRightDiagonal(spielStein) || spielfeld.rightToLeftDiagonal(spielStein)) {
			gameOver = true;
		}
		return gameOver;
	}
}

class Spieler {
	private String name;
	private char spielstein;

	public Spieler(String name, char spielstein) {
		this.name = name;
		this.spielstein = spielstein;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getSpielstein() {
		return spielstein;
	}

	public void setSpielstein(char spielstein) {
		this.spielstein = spielstein;
	}
}

class Spielfeld {
	private char[][] spielFeld = new char[6][7];

	public void printSpielfeld() {
		System.out.println("| 1 | 2 | 3 | 4 | 5 | 6 | 7 |");
		System.out.println("-----------------------------");
		for (int i = 0; i < 6; i++) {
			System.out.println(
					"| " + spielFeld[i][0] + " | " + spielFeld[i][1] + " | " + spielFeld[i][2] + " | " + spielFeld[i][3]
							+ " | " + spielFeld[i][4] + " | " + spielFeld[i][5] + " | " + spielFeld[i][6] + " |");
		}
		System.out.println("-----------------------------");
	}

	public void setSpalte(int spalte, char spielStein) {
		int i = 5;
		if (spielFeld[0][spalte - 1] != '\u0000') {
			System.out.println("Spalte bereits voll !!!");
			return;
		}
		if (spielFeld[5][spalte - 1] == '\u0000') {
			spielFeld[5][spalte - 1] = spielStein;
		} else {
			while (spielFeld[i][spalte - 1] != '\u0000') {
				i--;
			}
			spielFeld[i][spalte - 1] = spielStein;
		}

	}

	public char[][] getSpielFeld() {
		return spielFeld;
	}

	public void setSpielFeld(char[][] spielFeld) {
		this.spielFeld = spielFeld;
	}

	public boolean fourInColumn(char spielStein) {
		int counter = 0;
		boolean ret = false;
		for (int j = 0; j < 7; j++) {
			counter = 0;
			for (int i = 0; i < 6; i++) {
				if (spielFeld[i][j] == spielStein) {
					counter++;
				}
			}
			if (counter >= 4) {

				break;
			}
		}
		ret = counter >= 4;
		return ret;
	}

	public boolean fourInRow(char spielStein) {
		int counter = 0;
		boolean ret = false;
		for (int i = 0; i < 6; i++) {
			counter = 0;
			for (int j = 0; j < 7; j++) {
				if (spielFeld[i][j] == spielStein) {
					counter++;
				}
			}
			if (counter >= 4) {

				break;
			}
		}
		ret = counter >= 4;
		return ret;
	}

	public boolean leftToRightDiagonal(char spielStein) {
		boolean ret = false;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				if (spielFeld[i][j] == spielStein && spielFeld[i + 1][j + 1] == spielStein
						&& spielFeld[i + 2][j + 2] == spielStein && spielFeld[i + 3][j + 3] == spielStein) {
					ret = true;
				}
			}
		}
		return ret;
	}

	public boolean rightToLeftDiagonal(char spielStein) {
		boolean ret = false;
		for (int i = 0; i < 3; i++) {
			for (int j = 6; j > 2; j--) {
				if (spielFeld[i][j] == spielStein && spielFeld[i + 1][j - 1] == spielStein
						&& spielFeld[i + 2][j - 2] == spielStein && spielFeld[i + 3][j - 3] == spielStein) {
					ret = true;
				}
			}
		}
		return ret;
	}
}