import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Aufgabe2 {

	public static void searchArticleNumber(int articleNumber, List<Article> articles) {
		boolean containsArticle = false;
		for (Article currentArticle : articles) {
			if (currentArticle.getArticleNumber() == articleNumber) {
				currentArticle.printArticle();
				containsArticle = true;
			}
		}
		if (!containsArticle) {
			System.out.println("Artikel nicht gefunden.");
		}
	}

	public static void printArticlesWithPriceBelow(double price, List<Article> articles) {
		boolean containsArticle = false;
		for (Article currentArticle : articles) {
			if(currentArticle.getPrice().getValue() <= price) {
				currentArticle.printArticle();
				containsArticle = true;
			}
		}
		if (!containsArticle) {
			System.out.println("Artikel nicht gefunden.");
		}
	}

	public static void main(String[] args) {
		List<Article> articles = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		int input;
		boolean endInput = false;
		do {
			System.out.println("=========================================");
			System.out.println("1.Artikel hinzufügen.");
			System.out.println("2.Artikelnummer suchen.");
			System.out.println("3.Artikel nach Preiskategorie ausgeben.");
			System.out.println("4.Artikel ausgeben.");
			System.out.println("0.Ende.");
			System.out.println("Eingabe: ");
			input = sc.nextInt();
			sc.nextLine();
			endInput = input == 0;
			switch (input) {
			case 1:
				Article article = new Article();
				article.enterArticles();
				articles.add(article);
				break;
			case 2:
				System.out.println("Bitte Artikelnummer eingeben: ");
				int articleNumber = sc.nextInt();
				sc.nextLine();
				searchArticleNumber(articleNumber, articles);
				break;
			case 3:
				System.out.println("Bitte Maximalpreis eingeben: ");
				double price = sc.nextDouble();
				sc.nextLine();
				printArticlesWithPriceBelow(price, articles);
				break;
			case 4:
				for (Article currentArticle : articles) {
					currentArticle.printArticle();
				}
				break;

			}
		} while (!endInput);
		
	}

}

class Article {
	private int articleNumber;
	private String description;
	private Price price;
	private Color color;

	public void enterArticles() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Artikelbeschreibung: ");
		this.description = sc.nextLine();
		System.out.println("Artikelnummer: ");
		this.articleNumber = sc.nextInt();
		System.out.println("Preis: ");
		double value = sc.nextDouble();
		sc.nextLine();
		System.out.println("Währung (bitte ausformulieren): ");
		String currency = sc.nextLine();
		price = new Price(value, currency);
		System.out.println("Farbanteil rot: ");
		int red = sc.nextInt();
		System.out.println("Farbanteil grün: ");
		int green = sc.nextInt();
		System.out.println("Farbanteil blau: ");
		int blue = sc.nextInt();
		sc.nextLine();
		color = new Color(red, green, blue);
	}

	public void printArticle() {
		System.out.println("=========================================");
		System.out.println("Artikelbeschreibung: " + description);
		System.out.println("Artikelnummer: " + articleNumber);
		System.out.println("Preis: " + price.getValue());
		System.out.println("Währung: " + price.getCurrency());
		System.out.println("Farbanteil rot: " + color.getRed());
		System.out.println("Farbanteil grün: " + color.getGreen());
		System.out.println("Farbanteil blau: " + color.getBlue());
		System.out.println("=========================================");
	}

	public int getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(int articleNumber) {
		this.articleNumber = articleNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}

class Price {
	private double value;
	private String currency;

	public Price(double value, String currency) {
		this.value = value;
		this.currency = currency;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}

class Color {
	private int red;
	private int green;
	private int blue;

	public Color(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}
}