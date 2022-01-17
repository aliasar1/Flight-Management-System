package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileManager {

	public static final String CITIES_FILE = "cities";
	public static final String ROUTES_FILE = "routes";
	public static final String STATIC_FILE = "statics";

	public static void saveCitySerialized(ArrayList<City> YourObject) throws IOException {
		saveStaticsSerialized();
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(CITIES_FILE + ".dat"));
			outputStream.writeObject(YourObject);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static ArrayList<City> loadCitySerialized(ArrayList<City> data1) throws IOException {
		try {
			FileInputStream fileIn = new FileInputStream(CITIES_FILE + ".dat");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			try {
				data1 = (ArrayList<City>) in.readObject();
			} catch (ClassNotFoundException ex) {
				System.err.println("Class not found");
			}
			in.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return data1;
	}

	public static void saveRouteSerialized(FlightRoutesLinkedList YourObject) throws IOException {
		saveStaticsSerialized();
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(ROUTES_FILE + ".dat"));
			outputStream.writeObject(YourObject);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static FlightRoutesLinkedList loadRouteSerialized(FlightRoutesLinkedList data1) throws IOException {
		try {
			FileInputStream fileIn = new FileInputStream(ROUTES_FILE + ".dat");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			try {
				data1 = (FlightRoutesLinkedList) in.readObject();
			} catch (ClassNotFoundException ex) {
				System.err.println("Class not found");
			}
			in.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return data1;
	}

	public static void saveStaticsSerialized() throws IOException {
		ObjectOutputStream outputStream = null;
		StaticUtils YourObject = new StaticUtils();
		YourObject.loadTo();
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(STATIC_FILE + ".dat"));
			outputStream.writeObject(YourObject);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static StaticUtils loadStaticsSerialized() throws IOException {
		StaticUtils data1 = new StaticUtils();
		try {
			FileInputStream fileIn = new FileInputStream(STATIC_FILE + ".dat");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			try {
				data1 = (StaticUtils) in.readObject();
			} catch (ClassNotFoundException ex) {
				System.err.println("Class not found");
			}
			in.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return data1;
	}
}
