import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Aufgabe4 {

	public static void main(String[] args) {

		Rennschnecke willy = new Rennschnecke(1, 20, "Nacktschnecke");
		Rennschnecke erwin = new Rennschnecke(2, 15, "Weinbergschnecke");
		Rennschnecke hubert = new Rennschnecke(3, 5, "Bayer");
		Rennschnecke magnus = new Rennschnecke(4, 25, "Lateiner");
		Rennschnecke hans = new Rennschnecke(5, 13, "Tölpel");
		Wettbuero lotto = new Wettbuero();
		lotto.getRennen().addSchnecke(willy);
		lotto.getRennen().addSchnecke(erwin);
		lotto.getRennen().addSchnecke(hubert);
		lotto.getRennen().addSchnecke(magnus);
		lotto.getRennen().addSchnecke(hans);

		Scanner sc = new Scanner(System.in);
		int input = 0;
		boolean endInput = input == 0 || input == 3;
		do {
			System.out.println("1. Wette setzen.");
			System.out.println("2. Schnecken ausgeben.");
			System.out.println("3. Rennen starten.");
			System.out.println("0. Ende.");
			input = sc.nextInt();
			sc.nextLine();
			endInput = input == 0 || input == 3;
			;
			switch (input) {
			case 1:
				lotto.setzeWette();
				break;
			case 2:
				lotto.getRennen().printAlleSchnecken();
				break;
			case 3:
				while (lotto.getRennen().getAnzahlRennschnecken() != lotto.getRennen().getPlatzierungen().size()) {
					lotto.getRennen().kriechen();
				}
				break;
			}
		} while (!endInput);
		lotto.printResults();
		lotto.printWettergebnis();
	}
}

class Rennschnecke {
	private int startNummer;
	private int geschwindigkeit; // Zurückgelegte Strecke innerhalb einer Minute in Centimeter
	private String rasse;
	private double zurueckgelegteStrecke = 0;

	public Rennschnecke(int startNummer, int geschwindigkeit, String rasse) {
		this.startNummer = startNummer;
		this.geschwindigkeit = geschwindigkeit;
		this.rasse = rasse;
	}

	public void printSchnecke() {
		System.out.println("Startnummer: " + startNummer);
		System.out.println("Legt " + geschwindigkeit + " Centimeter in einer Minute zurück.");
		System.out.println("Rasse: " + rasse);
		System.out.println("Bereits zurückgelegte Strecke: " + zurueckgelegteStrecke);
	}

	public int getStartNummer() {
		return startNummer;
	}

	public void setStartNummer(int startNummer) {
		this.startNummer = startNummer;
	}

	public int getGeschwindigkeit() {
		return geschwindigkeit;
	}

	public void setGeschwindigkeit(int geschwindigkeit) {
		this.geschwindigkeit = geschwindigkeit;
	}

	public String getRasse() {
		return rasse;
	}

	public void setRasse(String rasse) {
		this.rasse = rasse;
	}

	public double getZurueckgelegteStrecke() {
		return zurueckgelegteStrecke;
	}

	public void setZurueckgelegteStrecke(double zurueckgelegteStrecke) {
		this.zurueckgelegteStrecke = zurueckgelegteStrecke;
	}

}

class Rennen {
	private List<Rennschnecke> rennSchnecken = new ArrayList<>();
	private List<Rennschnecke> platzierungen = new ArrayList<>();
	private int streckenLaenge = 5; // Länge der Strecke in Metern

	public void kriechen() {
		for (Rennschnecke currentSchnecke : rennSchnecken) {
			currentSchnecke.setZurueckgelegteStrecke((currentSchnecke.getZurueckgelegteStrecke())
					+ (Math.random() % currentSchnecke.getGeschwindigkeit()));
			if (currentSchnecke.getZurueckgelegteStrecke() > streckenLaenge) {
				if (!platzierungen.contains(currentSchnecke)) {
					platzierungen.add(currentSchnecke);
				} else {
					continue;
				}
			}
		}
	}

	public void addSchnecke(Rennschnecke rennSchnecke) {
		rennSchnecken.add(rennSchnecke);
	}

	public int getAnzahlRennschnecken() {
		return rennSchnecken.size();
	}

	public void printAlleSchnecken() {
		int i = 0;
		for (Rennschnecke currentSchnecke : rennSchnecken) {
			System.out.println("======================");
			System.out.print((i++ + 1) + ". ");
			currentSchnecke.printSchnecke();
			System.out.println("======================");
		}
	}

	public List<Rennschnecke> getRennSchnecken() {
		return rennSchnecken;
	}

	public void setRennSchnecken(List<Rennschnecke> rennSchnecken) {
		this.rennSchnecken = rennSchnecken;
	}

	public List<Rennschnecke> getPlatzierungen() {
		return platzierungen;
	}

	public void setPlatzierungen(List<Rennschnecke> platzierungen) {
		this.platzierungen = platzierungen;
	}

	public int getStreckenLaenge() {
		return streckenLaenge;
	}

	public void setStreckenLaenge(int streckenLaenge) {
		this.streckenLaenge = streckenLaenge;
	}

}

class Wettbuero {
	private List<Wetteinsatz> wetten = new ArrayList<>();
	private Rennen rennen = new Rennen();

	public void setzeWette() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Schnecke auswählen: ");
		rennen.printAlleSchnecken();
		int input = sc.nextInt();
		sc.nextLine();
		System.out.println("Einsatz eingeben: ");
		int einsatz = sc.nextInt();
		sc.nextLine();
		Wetteinsatz wette = new Wetteinsatz(einsatz, rennen.getRennSchnecken().get(input - 1));
		wetten.add(wette);
	}

	public void printResults() {
		int i = 1;
		for (Rennschnecke currentSchnecke : rennen.getPlatzierungen()) {
			System.out.println("Platz " + (i++) + ". " + "Startnummer " + currentSchnecke.getStartNummer());
		}
	}

	public void printWettergebnis() {
		int amountOfWinners = 3;
		int winningFactor = 2;
		for (Wetteinsatz currentWette : wetten) {
			if (rennen.getPlatzierungen().indexOf(currentWette.getRennschnecke()) < amountOfWinners) {
				System.out.println("Schnecke Nummer " + currentWette.getRennschnecke().getStartNummer() + " hat "
						+ currentWette.getEinsatz() * winningFactor + " gewonnen.");
			} else {
				System.out.println("Schnecke Nummer " + currentWette.getRennschnecke().getStartNummer() + " hat "
						+ currentWette.getEinsatz() + "verloren");
			}
		}
	}

	public Rennen getRennen() {
		return rennen;
	}

	public void setRennen(Rennen rennen) {
		this.rennen = rennen;
	}

}

class Wetteinsatz {
	private int einsatz;
	private Rennschnecke rennschnecke;

	public Wetteinsatz(int einsatz, Rennschnecke rennschnecke) {
		this.einsatz = einsatz;
		this.rennschnecke = rennschnecke;
	}

	public int getEinsatz() {
		return einsatz;
	}

	public void setEinsatz(int einsatz) {
		this.einsatz = einsatz;
	}

	public Rennschnecke getRennschnecke() {
		return rennschnecke;
	}

	public void setRennschnecke(Rennschnecke rennschnecke) {
		this.rennschnecke = rennschnecke;
	}
}
