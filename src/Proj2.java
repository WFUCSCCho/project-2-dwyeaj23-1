import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Proj2 {
    public static void main(String[] args) throws IOException {
        // Use command line arguments to specify the input file
        if (args.length != 2) {
            System.err.println("Usage: java Proj2 <input file> <number of lines>");
            System.exit(1);
        }

        String inputFileName = args[0];
        int numLines = Integer.parseInt(args[1]);

        // Step 1: Read the dataset and create Pokemon objects
        ArrayList<Pokemon> pokemonList = new ArrayList<>();
        loadPokemonData(inputFileName, pokemonList, numLines);

        // Step 2: Sort and randomize the Pokemon list
        ArrayList<Pokemon> sortedPokemon = new ArrayList<>(pokemonList);
        Collections.sort(sortedPokemon);

        ArrayList<Pokemon> randomizedPokemon = new ArrayList<>(pokemonList);
        Collections.shuffle(randomizedPokemon);

        // Step 3: Insert into AVL and BST trees
        BST<Pokemon> bstSorted = new BST<>();
        BST<Pokemon> bstRandom = new BST<>();
        AvlTree<Pokemon> avlSorted = new AvlTree<>();
        AvlTree<Pokemon> avlRandom = new AvlTree<>();

        // Measure insertion times
        long bstSortedInsertTime = measureInsertionTime(bstSorted, sortedPokemon);
        long bstRandomInsertTime = measureInsertionTime(bstRandom, randomizedPokemon);
        long avlSortedInsertTime = measureInsertionTime(avlSorted, sortedPokemon);
        long avlRandomInsertTime = measureInsertionTime(avlRandom, randomizedPokemon);

        // Measure search times
        long bstSortedSearchTime = measureSearchTime(bstSorted, pokemonList);
        long bstRandomSearchTime = measureSearchTime(bstRandom, pokemonList);
        long avlSortedSearchTime = measureSearchTime(avlSorted, pokemonList);
        long avlRandomSearchTime = measureSearchTime(avlRandom, pokemonList);

        // Step 5: Write results to output.txt
        writeResultsToFile(numLines, bstSortedInsertTime, bstRandomInsertTime,
                avlSortedInsertTime, avlRandomInsertTime,
                bstSortedSearchTime, bstRandomSearchTime,
                avlSortedSearchTime, avlRandomSearchTime);
    }

    // Method to load Pokemon data from the CSV file
    private static void loadPokemonData(String csvFile, ArrayList<Pokemon> pokemonList, int numLines) {
        try (Scanner scanner = new Scanner(new File(csvFile))) {
            // Skip the first line (header)
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            int count = 0;
            while (scanner.hasNextLine() && count < numLines) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                // Assuming CSV format: id,name,type1,type2,total,hp,attack,defense,specialAttack,specialDefense,speed,generation,isLegendary
                String[] attributes = line.split(",");
                if (attributes.length >= 13) {  // Ensure there are enough columns
                    int id = Integer.parseInt(attributes[0]);
                    String name = attributes[1];
                    String type1 = attributes[2];
                    String type2 = attributes[3].equals("None") ? "" : attributes[3];
                    int total = Integer.parseInt(attributes[4]);
                    int hp = Integer.parseInt(attributes[5]);
                    int attack = Integer.parseInt(attributes[6]);
                    int defense = Integer.parseInt(attributes[7]);
                    int specialAttack = Integer.parseInt(attributes[8]);
                    int specialDefense = Integer.parseInt(attributes[9]);
                    int speed = Integer.parseInt(attributes[10]);
                    int generation = Integer.parseInt(attributes[11]);
                    boolean isLegendary = Boolean.parseBoolean(attributes[12]);

                    // Create a new Pokemon object and add it to the list
                    Pokemon pokemon = new Pokemon(id, name, type1, type2, total, hp, attack, defense,
                            specialAttack, specialDefense, speed, generation, isLegendary);
                    pokemonList.add(pokemon);
                    count++;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("CSV file not found: " + csvFile);
        }
    }

    // Helper method to measure insertion time for BST
    private static long measureInsertionTime(BST<Pokemon> bst, ArrayList<Pokemon> data) {
        long startTime = System.nanoTime();
        for (Pokemon pokemon : data) {
            bst.insert(pokemon);
        }
        return System.nanoTime() - startTime;
    }

    // Helper method to measure insertion time for AVL Tree
    private static long measureInsertionTime(AvlTree<Pokemon> avl, ArrayList<Pokemon> data) {
        long startTime = System.nanoTime();
        for (Pokemon pokemon : data) {
            avl.insert(pokemon);
        }
        return System.nanoTime() - startTime;
    }

    // Helper method to measure search time for BST
    private static long measureSearchTime(BST<Pokemon> bst, ArrayList<Pokemon> data) {
        long startTime = System.nanoTime();
        for (Pokemon pokemon : data) {
            bst.search(pokemon); // Use search() instead of contains()
        }
        return System.nanoTime() - startTime;
    }

    // Helper method to measure search time for AVL Tree
    private static long measureSearchTime(AvlTree<Pokemon> avl, ArrayList<Pokemon> data) {
        long startTime = System.nanoTime();
        for (Pokemon pokemon : data) {
            avl.contains(pokemon);
        }
        return System.nanoTime() - startTime;
    }

    // Helper method to write results to output.txt
    private static void writeResultsToFile(int numLines, long bstSortedInsert, long bstRandomInsert,
                                           long avlSortedInsert, long avlRandomInsert,
                                           long bstSortedSearch, long bstRandomSearch,
                                           long avlSortedSearch, long avlRandomSearch) throws IOException {
        FileOutputStream output = new FileOutputStream("output.txt", true);
        String result = numLines + "," + bstSortedInsert + "," + bstRandomInsert + ","
                + avlSortedInsert + "," + avlRandomInsert + ","
                + bstSortedSearch + "," + bstRandomSearch + ","
                + avlSortedSearch + "," + avlRandomSearch + "\n";
        output.write(result.getBytes());
        output.close();
    }
}