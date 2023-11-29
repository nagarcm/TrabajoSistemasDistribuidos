package Model.basics;

import java.util.*;

public enum CardMod {
	Exhaust(1), Innate(2), Ethereal(4), Unplayable(8);

	private int value;

	CardMod(int value) {
		this.value = value;
	}

	public int value() {
		return value;
	}

	public static List<CardMod> getMods(int n) {
		ArrayList<CardMod> al = null;
		if (n >= 0 && n < 16) {
			al = new ArrayList<>();

			if (n >= 8) {
				n -= 8;
				al.add(Unplayable);
			}
			if (n >= 4) {
				n -= 4;
				al.add(Ethereal);
			}
			if (n >= 2) {
				n -= 2;
				al.add(Innate);
			}
			if (n == 1) {
				al.add(Exhaust);
			}
		}
		return al;

	}
}