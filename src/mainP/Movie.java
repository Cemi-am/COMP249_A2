//------------------------------------------
// Assignment 2
// COMP249
// Written by: Samy Mezimez 40275766
// Due Date: Wednesday 27th March 2024
//------------------------------------------

/*
 * General comments on the program: 
 * This program can take multiple CSV files as input and partition them into multiple files in a genre-specific manner.
 * It can also serialize and deserialize the data to and from binary files.
 * The program can display the movie database in a genre-specific manner and navigate through the records.
 * 
 * The program is divided into three parts:
 * Part 1: Partitioning the data
 *  - The program reads the CSV files and partitions the data into multiple files based on the genre.
 *  - The program creates a manifest file that contains the names of the partitioned files.
 * 
 * Part 2: Serializing the data
 *  - The program reads the manifest file and serializes the data from the partitioned files into binary files.
 *  - The program creates a new manifest file that contains the names of the serialized files.
 *  
 * Part 3: Deserializing the data
 *  - The program reads the manifest file and deserializes the data from the serialized files.
 *  - The program displays the movie database in a genre-specific manner and allows the user to navigate through the records.
 * 
 * Lastly, the program displays a main menu that allows the user to navigate through different movie genres and choose options from the menu.
 * 
 */

package mainP;

import java.util.Scanner;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.nio.file.Files; //Only used to delete files in freshStart()

public class Movie implements Serializable{
	
	//Instance variables
	private int year;
	private int duration;
	private double score;
	
	private String rating;
	private String title;
	private String genre;
	private String director;
	private String actor1;
	private String actor2;
	private String actor3;
	
	private static final String[] validRatings = {"PG", "Unrated", "G", "R", "PG-13", "NC-17"};
	private static final String[] validGenres = {"musical", "comedy", "animation", "adventure", "drama", "crime", "biography",
									"horror", "action", "documentary", "fantasy", "mystery", "sci-fi", "family", "western",
									"romance", "thriller"};
	
	//Default
	public Movie() {}
	
	//Parameterized
	public Movie(int year, int duration, double score, String rating, String title, String genre, String director,
			String actor1, String actor2, String actor3) {
	
		this.year = year;
		this.duration = duration;
		this.score = score;
		this.rating = rating;
		this.title = title;
		this.genre = genre;
		this.director = director;
		this.actor1 = actor1;
		this.actor2 = actor2;
		this.actor3 = actor3;
	}
	
	/*
	 * MUTATORS
	 */
	public void setYear(int y) {
		this.year = y;
	}
	public void setDuration(int d) {
		this.duration = d;
	}
	public void setScore(double s) {
		this.score = s;
	}
	public void setRating(String r) {
		this.rating = r;
	}
	public void setTitle(String t) {
		this.title = t;
	}
	public void setGenre(String g) {
		this.genre = g;
	}
	public void setDirector(String d) {
		this.director = d;
	}
	public void setActor1(String a1) {
		this.actor1 = a1;
	}
	public void setActor2(String a2) {
		this.actor2 = a2;
	}
	public void setActor3(String a3) {
		this.actor3 = a3;
	}
	
	/*
	 * ACCESSORS
	 */
	public int getYear() {
		return this.year;
	}	
	public int setDuration() {
		return this.duration;
	}
	public double setScore() {
		return this.score;
	}
	public String setRating() {
		return this.rating;
	}
	public String setTitle() {
		return this.title;
	}
	public String setGenre() {
		return this.genre;
	}
	public String setDirector() {
		return this.director;
	}
	public String setActor1() {
		return this.actor1;
	}
	public String setActor2() {
		return this.actor2;
	}
	public String setActor3() {
		return this.actor3;
	}
	

	/**
	 * Compares this Movie object with the specified object for equality.
	 * Returns true if the given object is also a Movie object and has the same values for all attributes.
	 * 
	 * @param o the object to be compared for equality
	 * @return true if the given object is equal to this Movie object, false otherwise
	 */
	@Override
	public boolean equals(Object o) {
	  if(o == null) {return false;}
	  else if(getClass( ) != o.getClass()) {return false;}
	  
	  else {
	    Movie oMovie = (Movie) o;
	    return (year == oMovie.year && 
	    		duration == oMovie.duration &&
	    		score == oMovie.score &&
	    		rating.equals(oMovie.rating) &&
	    		title.equals(oMovie.title) &&
	    		genre.equals(oMovie.genre) &&
	    		director.equals(oMovie.director) &&
	    		actor1.equals(oMovie.actor1) &&
	    		actor2.equals(oMovie.actor2) &&
	    		actor3.equals(oMovie.actor3)
	    		);
	  }
	}//equals close
	
	/**
	 * Returns a string representation of the object.
	 * 
	 * @return a string representation of the object
	 */
	@Override
	public String toString() {
		return ("Movie Informations\n" +
				"\nTitle --> " + this.title +
				"\nDuration (min) --> " + this.duration +
				"\nGenre --> " + this.genre +
				"\nRelease year --> " + this.year +
				"\nRating --> " + this.rating +
				"\nScore --> " + this.score +
				"\nDirector --> " + this.director +
				"\nActors --> " + this.actor1 +
				", " + this.actor2 +
				", " + this.actor3
				);
	}//toString close


	/**
	 * Reads a manifest file and deserializes genre files into a 2D array of Movie objects.
	 * 
	 * @param manifest3 the path to the manifest file
	 * @return a 2D array of Movie objects representing the deserialized genre files
	 */
	public static Movie[][] doPart3(String manifest3) {

		//Counting the number of lines in the manifest3 file
		BufferedReader manifestReader = null;
		int lineNb = 0;
		try {
			manifestReader = new BufferedReader(new FileReader(manifest3));
			while (manifestReader.readLine() != null) {
				lineNb++;
			}
			manifestReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//Deserializing the genre files into a 2D array of Movie objects
		Movie[][] allMovies = new Movie[lineNb][];
		for(int i = 0; i < lineNb; i++) {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(validGenres[i] + ".ser"));
				allMovies[i] = (Movie[]) ois.readObject();
				ois.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return allMovies;
	}//getMovies() close


	/**
	 * Deletes specific files from the file system to perform a fresh start.
	 * The files to be deleted are specified in the `toDelete` array.
	 * If an error occurs while deleting a file, the exception is caught.
	 */
	public static void freshStart() {
		String[] toDelete = new String[] {"part2_manifest.txt", "part3_manifest.txt", "bad_movie_records.txt", "musical.txt", "comedy.txt", "animation.txt", "adventure.txt", "drama.txt", "crime.txt", "biography.txt",
			"horror.txt", "action.txt", "documentary.txt", "fantasy.txt", "mystery.txt", "sci-fi.txt", "family.txt", "romance.txt", "thriller.txt", "western.txt", "musical.ser", "comedy.ser", "animation.ser", 
			"adventure.ser", "drama.ser", "crime.ser", "biography.ser", "horror.ser", "action.ser", "documentary.ser", "fantasy.ser", "mystery.ser", "sci-fi.ser", "family.ser", "romance.ser", "thriller.ser", "western.ser"};

		for(int i = 0; i < toDelete.length; i++) {
			File file = new File(toDelete[i]);
			try {
				Files.deleteIfExists(file.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}//freshStart() close

	/**
	 * Performs part 2 of the program.
	 * 
	 * @param manifest2 the path to the manifest file
	 */
	public static void doPart2(String manifest2) {
		//creating part3_manifest.txt with genre.ser files
		serFileCreator(manifest2);
		//serializing the genre files
		movieArraySerializer(manifest2);
	}//doPart2() close
		
	
	/**
	 * Serializes an array of Movie objects into .ser files based on genre-based file names.
	 *
	 * @param manifest2 The path to the manifest file containing genre-based file names.
	 */
	public static void movieArraySerializer(String manifest2) {
		//for each genre file, create a Movie array with each index being a Movie object then serialize the array into a .ser file
		BufferedReader manifestReader = null;
		BufferedReader lineReader = null;
		BufferedReader serFile = null;
		String[] fieldsArray = null;
		
		String fileName = null;
		String movieLine = null;
		String currentSerFile = null;

		try {
			//BufferedReader to read genre-based file names
			manifestReader = new BufferedReader(new FileReader(manifest2));
			serFile = new BufferedReader(new FileReader("part3_manifest.txt"));
			//for every file
			while ((fileName = manifestReader.readLine()) != null) {
				Movie[] movieArray = {};
				currentSerFile = serFile.readLine(); //holds the name of the .ser file
				lineReader = new BufferedReader(new FileReader(fileName));
				//for every line
				while ((movieLine = lineReader.readLine()) != null){
					//Split the line into an array
					fieldsArray = movieLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
					//Create a Movie object with the array
					Movie movie = new Movie(Integer.parseInt(fieldsArray[0]), Integer.parseInt(fieldsArray[2]), Double.parseDouble(fieldsArray[5]), fieldsArray[4], fieldsArray[1], fieldsArray[3], fieldsArray[6], fieldsArray[7], fieldsArray[8], fieldsArray[9]);
					//Add the Movie object to the array
					if (movieArray.length == 0 || movieArray[movieArray.length-1] != null ) {
						Movie[] temp = new Movie[movieArray.length + 1];
						for (int j = 0; j < movieArray.length; j++) {
							temp[j] = movieArray[j];
						}
						movieArray = temp;
						movieArray[movieArray.length-1] = movie;
					}
					else {
						movieArray[movieArray.length-1] = movie;
					}
				}//line
				lineReader.close();
				
				//Write the array to a .ser file
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(currentSerFile));
				oos.writeObject(movieArray);
				oos.close();
			}//file
			manifestReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//movieArraySerializer() close

	
	/**
	 * Creates a new file named "part3_manifest.txt" with the same files as "part2_manifest.txt",
	 * but with the file extensions changed to ".ser".
	 *
	 * @param manifest2 the path of the "part2_manifest.txt" file
	 */
	public static void serFileCreator(String manifest2) {
		//Create part3_manifest.txt with same files as part2_manifest.txt but ending in .ser
		PrintWriter part3_manifest = null;
		BufferedReader part2_manifest = null;
		try {
			part3_manifest = new PrintWriter(new FileOutputStream("part3_manifest.txt", true));
			part2_manifest = new BufferedReader(new FileReader(manifest2));
			String fileName = null;
			while ((fileName = part2_manifest.readLine()) != null) {
				int nbChars = fileName.length();
				part3_manifest.println(fileName.substring(0, nbChars-4) + ".ser");
			}
			part2_manifest.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			part3_manifest.close();
		}
	}//serFileCreator() close

	/**
	 * Performs Part 1 of the movie processing.
	 * This method creates the part2_manifest.txt file, creates files for each genre,
	 * reads the files specified in the manifest, and writes the files for each genre.
	 *
	 * @param manifest the path of the manifest file containing the names of the files to read
	 */
	public static void doPart1(String manifest) {
		//Creating part2_manifest.txt
		part2ManifestCreator();
		//Creating the files for each genre
		genreFileCreator();
		//Array with names of the files to read
		String[] filesArray = part1ManifestArray(manifest);
		//Calling the method to write the files
		try {
			genreFileWriter(filesArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//doPart1() close
	
	/**
	 * Creates a manifest file named "part2_manifest.txt" and writes all genre files into it.
	 * Each genre file is represented by its name followed by the extension ".txt".
	 */
	public static void part2ManifestCreator() {
		//Writing all genre files in part2_manifest.txt
		PrintWriter part2_manifest = null;
		try {
			part2_manifest = new PrintWriter(new FileOutputStream("part2_manifest.txt", true));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for(int i = 0; i < validGenres.length; i++) {
			part2_manifest.println(validGenres[i] + ".txt");
		}
		part2_manifest.close();
	}//part2ManifestCreator() close
	
	/**
	 * Creates a file for each genre using the array of valid genres.
	 * Each file is named after the corresponding genre and has a ".txt" extension.
	 * 
	 * @throws FileNotFoundException if an error occurs while creating or opening the file.
	 */
	public static void genreFileCreator() {
		//Creating a file for each genre using the array of valid genres
		PrintWriter genreFileCreator = null;
		for(String genre: validGenres) {
			try {
				genreFileCreator = new PrintWriter(new FileOutputStream(genre + ".txt", true));
			} catch (FileNotFoundException e) {
				e.getMessage();
			}
		}
		genreFileCreator.close();
	}//genreFileCreator() close
	
	
	/**
	 * Writes movie lines from CSV files into genre-specific text files and handles exceptions.
	 * 
	 * @param filesArray an array of file paths to read from
	 * @throws IOException if an I/O error occurs while reading or writing the files
	 */
	public static void genreFileWriter(String[] filesArray) throws IOException {
		//attributes of the method
		PrintWriter genreWriter = null;
		PrintWriter badMovieWriter = null;
		BufferedReader lineReader = null;
		String movieLine = null; //movieLine is a line from a CSV file
		
		//Looping through the filesArray (names of the csv files to read)
		for(int i = 0; i < filesArray.length; i++) {
			int lineNb = 1; //store the line number
			try {
				lineReader = new BufferedReader(new FileReader(filesArray[i]));
				while((movieLine = lineReader.readLine()) != null) {
					try {
						Movie.stringToArray(movieLine);
						//Creating a file with the name of the genre and putting the line in it
						genreWriter = new PrintWriter(new FileOutputStream((Movie.stringToArray(movieLine)).toLowerCase() + ".txt", true));
						genreWriter.println(movieLine);
						genreWriter.flush();
					} catch (FileNotFoundException e) {
						e.getMessage();
					} catch (Exception e) {
						e.getMessage();
						badMovieWriter = new PrintWriter(new FileOutputStream("bad_movie_records.txt", true));
						badMovieWriter.println(e.getMessage() +" in line "+ lineNb + " of file " + filesArray[i] + " --> " + movieLine);
						badMovieWriter.flush();
					}
					lineNb++;
				}
				genreWriter.close();
			} catch (FileNotFoundException e) {
				e.getMessage();
			} catch (Exception e) {
				e.getMessage();
			} finally {
				badMovieWriter.close();
				lineReader.close();
			}
		}//for close
	}//genreFileWriter() close

	
	
	/**
	 * This method converts ONE Line into an array to check for syntax and semantic errors
	 * 
	 * @param s the string representation of the movie
	 * @return the genre of the movie
	 * @throws MissingQuotesException
	 * @throws MissingFieldsException
	 * @throws ExcessFieldsException
	 * @throws BadYearException
	 * @throws BadTitleException
	 * @throws BadDurationException
	 * @throws BadGenreException
	 * @throws BadRatingException
	 * @throws BadScoreException
	 * @throws BadNameException
	 */
	public static String stringToArray(String s) //returns the genre to be used for putting line in right file
			throws MissingQuotesException, MissingFieldsException, ExcessFieldsException,
			BadYearException, BadTitleException, BadDurationException, BadGenreException,
			BadRatingException, BadScoreException, BadNameException {
		
		//Checking if every quote is closed and throwing Syntax error if not
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '\"') {count++;}
		}
		if(count%2 != 0) {
			throw new MissingQuotesException("Invalid quotes");
		}
		
		//Separating String at every comma
		String[] fieldsArray = s.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
		
		//Throwing Syntax error if array.lenght != 10
		if(fieldsArray.length < 10) {
			throw new MissingFieldsException();
		}
		//Throwing Syntax error if array.lenght != 10
		if(fieldsArray.length > 10 && fieldsArray[10] != "") {
			throw new ExcessFieldsException();
		}
		
		//Throwing Semantic error if any of the array index == null or blank space
		for(int i = 0; i < fieldsArray.length; i++) {
			if(fieldsArray[i].isBlank()) {
				switch (i) {
				case 0: 
					throw new BadYearException("Missing release year");
				case 1: 
					throw new BadTitleException("Missing title");
				case 2: 
					throw new BadDurationException("Missing duration");
				case 3: 
					throw new BadGenreException("Missing genre");
				case 4: 
					throw new BadRatingException("Missing rating");
				case 5: 
					throw new BadScoreException("Missing score");
				case 6: 
					throw new BadNameException("Missing director");
				case 7: 
					throw new BadNameException("Missing first actor");
				case 8: 
					throw new BadNameException("Missing second actor");
				case 9: 
					throw new BadNameException("Missing third actor");
				}	
			}	
		}

		//Throwing Semantic error for invalid: YEAR[0]-DURATION[2]-GENRE[3]-RATING[4]-SCORE[5] 
		//YEAR[0]
		if(Integer.parseInt(fieldsArray[0]) < 1990 || Integer.parseInt(fieldsArray[0]) > 1999) {
			throw new BadYearException("Invalid Year");
		}
		//DURATION[2]
		if(Integer.parseInt(fieldsArray[2]) < 30 || Integer.parseInt(fieldsArray[2]) > 300) {
			throw new BadDurationException("Invalid Duration");
		}
		//SCORE[5]
		if(Double.parseDouble(fieldsArray[5]) < 0 || Double.parseDouble(fieldsArray[5]) > 10) {
			throw new BadScoreException("Invalid Score");
		}
		//RATINGS[4]
		boolean ratingTemp = false;
		for(int i = 0; i < validRatings.length; i++) {
			if(fieldsArray[4].equalsIgnoreCase(validRatings[i])) {ratingTemp = true;}
		}
		if(!ratingTemp) { 
			throw new BadRatingException("Invalid Rating");
		}
		//GENRE[3]
		boolean genreTemp = false;
		for(int i = 0; i < validGenres.length; i++) {
			if(fieldsArray[3].equalsIgnoreCase(validGenres[i])) {genreTemp = true;}
		}
		if(!genreTemp) { 
			throw new BadGenreException("Invalid Genre");
		}
		
		return fieldsArray[3];
	}//stringToArray() close
	

	/**
	 * Method to create an array with all the names of the files we are trying to read.
	 *
	 * @param manifest the path of the manifest file to read
	 * @return an array of file names read from the manifest file
	 */
	public static String[] part1ManifestArray(String manifest) {
		Scanner scanner = null;
		String part1_manifest = manifest;
		String[] filesArray = null;
		
		/*
		 * Creating an array with all file names to read
		 */
		try {
			//First scanner to count how many lines
			scanner = new Scanner(new FileInputStream(part1_manifest));

			int count = 0;
			while(scanner.hasNextLine()) {
				scanner.nextLine();
				count++;
			}
			scanner.close();
			
			//Second scanner to put every line into an array
			filesArray = new String[count];
			scanner = new Scanner(new FileInputStream(part1_manifest));
			int index = 0;
			while(scanner.hasNextLine()) {
				filesArray[index] = scanner.nextLine();
				index++;
			}
			scanner.close();
		}		
		catch(FileNotFoundException e) {
			System.out.println("File was not found");
		}
		return filesArray;
	}//part1ManifestArray() close
	
}//class close