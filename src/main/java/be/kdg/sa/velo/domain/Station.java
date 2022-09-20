package be.kdg.sa.velo.domain;

/**
 * @author Maxim Derboven
 * @version 1.0 20/09/2022 12:42
 */
public class Station {
	private long id;
	private String name;
	private String Street;
	private String Number;
	private String PostalCode;
	private String City;

	private int capacity;
	private int availableBikes;
	private int availableLocks;
	private double latitude;
	private double longitude;
}
