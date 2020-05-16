package ko.maeng.cleancoders.function;

public class ChildRensMovie extends Movie {
	public ChildRensMovie(String title) {
		super(title, Movie.CHILDRENS);
	}

	@Override
	double determineAmount(int daysRented) {
		double rentalAmount = 1.5;
		if (daysRented > 3)
			rentalAmount += (daysRented - 3) * 1.5;
		return rentalAmount;
	}

	@Override
	int determineFrequentRentalPoint(int daysRented) {
		return 1;
	}
}
