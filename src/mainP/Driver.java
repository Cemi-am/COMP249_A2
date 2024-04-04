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

public class Driver {

    public static void main(String[] args) {
        
        //Delete all files from previous runs
		Movie.freshStart();
		
		//Names of the manifest files
		String manifest1 = "part1_manifest.txt";
		String manifest2 = "part2_manifest.txt";
		String manifest3 = "part3_manifest.txt";

		//Partitioning data
		Movie.doPart1(manifest1);
		//Serializing data
		Movie.doPart2(manifest2);
		//Deserializing data
		Movie[][] allMovies = Movie.doPart3(manifest3);

        //Main menu
        String menuGenre = "musical";
        
        int nbMovies = 0;
        int genreChoice = 20;
        int movieChoice;
        int cursor = 0;
        //Welcome message
        System.out.println("==========================================");
        System.out.println("   Welcome to the Movie Database");
        System.out.println("Built by Samy M.");
        System.out.println("==========================================");
        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("==========================================");
            System.out.println("\t\tMAIN MENU");
            System.out.println("==========================================");
            System.out.println("s Select a movie array to navigate");
            System.out.println("n Navigate " + menuGenre + " movies (" + nbMovies + " records)");
            System.out.println("x Exit");
            System.out.println("==========================================");
            System.out.print("Enter your choice: ");
            String menuChoice = scanner.nextLine().toLowerCase();

            switch (menuChoice) {
                case "s":
                System.out.println("==========================================");
                System.out.println("\t\tGENRE SUB-MENU");
                System.out.println("==========================================");
                System.out.println("1 musical ("+allMovies[0].length+" movies)\r\n" + //
                                    "2 comedy ("+allMovies[1].length+" movies)\r\n" + //
                                    "3 animation ("+allMovies[2].length+" movies)\r\n" + //
                                    "4 adventure ("+allMovies[3].length+" movies)\r\n" + //
                                    "5 drama ("+allMovies[4].length+" movies)\r\n" + //
                                    "6 crime ("+allMovies[5].length+" movies)\r\n" + //
                                    "7 biography ("+allMovies[6].length+" movies)\r\n" + //
                                    "8 horror ("+allMovies[7].length+" movies)\r\n" + //
                                    "9 action ("+allMovies[8].length+" movies)\r\n" + //
                                    "10 documentary ("+allMovies[9].length+" movies)\r\n" + //
                                    "11 fantasy ("+allMovies[10].length+" movies)\r\n" + //
                                    "12 mystery ("+allMovies[11].length+" movies)\r\n" + //
                                    "13 sci-fi ("+allMovies[12].length+" movies)\r\n" + //
                                    "14 family ("+allMovies[13].length+" movies)\r\n" + //
                                    "15 western ("+allMovies[14].length+" movies)\r\n" + //
                                    "16 romance ("+allMovies[15].length+" movies)\r\n" + //
                                    "17 thriller ("+allMovies[16].length+" movies)\r\n" + //
                                    "18 Exit");
                System.out.println("==========================================");
                System.out.print("Enter your choice: ");
                genreChoice = scanner.nextInt();
                
                switch (genreChoice) {
                    case 1:
                        System.out.println("==========================================");
                        System.out.println("\t\tmusical");
                        System.out.println("==========================================");
                        menuGenre = "musical";
                        nbMovies = allMovies[0].length;
                        break;
                    case 2:
                        System.out.println("==========================================");
                        System.out.println("\t\tcomedy");
                        System.out.println("==========================================");
                        menuGenre = "comedy";
                        nbMovies = allMovies[1].length;
                        break;
                    case 3:
                        System.out.println("==========================================");
                        System.out.println("\t\tanimation");
                        System.out.println("==========================================");
                        menuGenre = "animation";
                        nbMovies = allMovies[2].length;
                        break;
                    case 4:
                        System.out.println("==========================================");
                        System.out.println("\t\tadventure");
                        System.out.println("==========================================");
                        menuGenre = "adventure";
                        nbMovies = allMovies[3].length;
                        break;
                    case 5:
                        System.out.println("==========================================");
                        System.out.println("\t\tdrama");
                        System.out.println("==========================================");
                        menuGenre = "drama";
                        nbMovies = allMovies[4].length;
                        break;
                    case 6:
                        System.out.println("==========================================");
                        System.out.println("\t\tcrime");
                        System.out.println("==========================================");
                        menuGenre = "crime";
                        nbMovies = allMovies[5].length;
                        break;
                    case 7:
                        System.out.println("==========================================");
                        System.out.println("\t\tbiography");
                        System.out.println("==========================================");
                        menuGenre = "biography";
                        nbMovies = allMovies[6].length;
                        break;
                    case 8:
                        System.out.println("==========================================");
                        System.out.println("\t\thorror");
                        System.out.println("==========================================");
                        menuGenre = "horror";
                        nbMovies = allMovies[7].length;
                        break;
                    case 9:
                        System.out.println("==========================================");
                        System.out.println("\t\taction");
                        System.out.println("==========================================");
                        menuGenre = "action";
                        nbMovies = allMovies[8].length;
                        break;
                    case 10:
                        System.out.println("==========================================");
                        System.out.println("\t\tdocumentary");
                        System.out.println("==========================================");
                        menuGenre = "documentary";
                        nbMovies = allMovies[9].length;
                        break;
                    case 11:
                        System.out.println("==========================================");
                        System.out.println("\t\tfantasy");
                        System.out.println("==========================================");
                        menuGenre = "fantasy";
                        nbMovies = allMovies[10].length;
                        break;
                    case 12:
                        System.out.println("==========================================");
                        System.out.println("\t\tmystery");
                        System.out.println("==========================================");
                        menuGenre = "mystery";
                        nbMovies = allMovies[11].length;
                        break;
                    case 13:
                        System.out.println("==========================================");
                        System.out.println("\t\tsci-fi");
                        System.out.println("==========================================");
                        menuGenre = "sci-fi";
                        nbMovies = allMovies[12].length;
                        break;
                    case 14:
                        System.out.println("==========================================");
                        System.out.println("\t\tfamily");
                        System.out.println("==========================================");
                        menuGenre = "family";
                        nbMovies = allMovies[13].length;
                        break;
                    case 15:
                        System.out.println("==========================================");
                        System.out.println("\t\twestern");
                        System.out.println("==========================================");
                        menuGenre = "western";
                        nbMovies = allMovies[14].length;
                        break;
                    case 16:
                        System.out.println("==========================================");
                        System.out.println("\t\tromance");
                        System.out.println("==========================================");
                        menuGenre = "romance";
                        nbMovies = allMovies[15].length;
                        break;
                    case 17:
                        System.out.println("==========================================");
                        System.out.println("\t\tthriller");
                        System.out.println("==========================================");
                        menuGenre = "thriller";
                        nbMovies = allMovies[16].length;
                        break;
                    case 18:
                        System.out.println("==========================================");
                        System.out.println("\t\tGoodbye!");
                        System.out.println("==========================================");
                        scanner.close();
                        System.exit(0);
                        break;
                }//small switch close
                break;
            case "n":
                System.out.println("==========================================");
                System.out.println("Navigate " + menuGenre + " movies (" + nbMovies+ ")");
                System.out.println("==========================================");
                System.out.print("Enter your choice: "); 
                movieChoice = scanner.nextInt();
                if (movieChoice == 0) {break;}
                else if(movieChoice > 0) {
                    
                    movieChoice+=cursor;
                    for (int i = cursor; i < movieChoice; i++) {
                        cursor = movieChoice-1;
                        System.out.println("------------------------------------------");
                        System.out.println(allMovies[genreChoice-1][i]); 
                        System.out.println("------------------------------------------");
                        if (cursor >= allMovies[genreChoice-1].length) {
                            System.out.println("EOF has been reached");
                            break;
                        }
                    }
                }

                //from cursor += movieChoice+1 to cursor as long as cursor is greater or equal moviechoice+1

                else if(movieChoice < 0) {
                    cursor += movieChoice+1;
                    for (int i = cursor; i < cursor-movieChoice; i++) {
                        //cursor = movieChoice+1;
                        System.out.println("------------------------------------------");
                        System.out.println(allMovies[genreChoice-1][i]); 
                        System.out.println("------------------------------------------");
                        if (cursor < 0) {
                           System.out.println("BOF has been reached");
                           break;
                        }
                    }
                }
                break;
            case "x":
                System.out.println("==========================================");
                System.out.println("\t\tGoodbye!");
                System.out.println("==========================================");
                scanner.close();
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
            }//big switch close
        }//while close
    }//main close       
}//class close          
  