import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main (String args[]){
        int MAX_MOVIES = 100;
        String [] movies = new String[MAX_MOVIES];
        int current = 0;
        Random random = new Random();

        try{
            File file = new File("list.txt");
            //System.out.println(new File(".").getAbsoluteFile());
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                movies [current] = scanner.nextLine();
                current++;
            }
            int randomPosition = random.nextInt(current);
            String randomMovie = movies [randomPosition];
            int randomMovieLength = randomMovie.length();

            Scanner input = new Scanner(System.in);
            System.out.println("I have picked up the movie name, now let's get started!");
            System.out.print("You are guessing (one letter at a time): ");
            //
            //System.out.print(randomMovie);
            for(int i = 0; i < randomMovieLength;i++){
                System.out.print("_ ");
            }
            System.out.println();
            int numOfWrongGuess = 0;
            int pointsTotal = 10;
            String [] lettersReveled  = new String[randomMovieLength];

            //randomMovie = "Hello";
            while (pointsTotal > 0 ){
                String letter = input.nextLine().toLowerCase();
                int result = randomMovie.toLowerCase().indexOf(letter.toLowerCase());
                if (result == -1) {
                    numOfWrongGuess++;
                    pointsTotal--;
                    System.out.println("You have guessed ( " + numOfWrongGuess + " ) wrong letters: ");
                } else {
                         if (lettersReveled [result] != null){
                            result = randomMovie.substring(result+1).toLowerCase().indexOf(letter.toLowerCase())+result+1;
                          }
                    lettersReveled [result] = letter;
                    for (int i = 0; i < randomMovieLength;i++){
                        if (lettersReveled [i] != null){
                            System.out.print(randomMovie.charAt(i) + " ");
                        }else {
                            System.out.print("_ ");
                        }
                    }

                }
                System.out.println();
                String output = "";
                for (String str : lettersReveled){
                    output += str;
                }
                if (output.toLowerCase().equals(randomMovie.toLowerCase())){
                    System.out.println("You won!");
                    break;
                }

            }

        }catch (FileNotFoundException e){
            System.out.println("file not found!");
        }
    }
}
