/**
 * @file: Parser.java
 * @description: This class represents a parser that processes input commands from a file to manage
 *               a Binary Search Tree (BST) containing Pokemon objects. The parser can handle commands
 *               such as inserting, searching, removing, and printing Pokemon records in the BST. The results
 *               of these operations are written to an output file.
 * @author: Andrew Dwyer
 * @date: September 22, 2024
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    private BST<Pokemon> pokemonBST = new BST<>();
    private ArrayList<Pokemon> pokemonList = new ArrayList<>();

    // Constructor that accepts a filename to process and loads the Pokémon data
    public Parser(String filename) throws FileNotFoundException {
        loadPokemonData("./src/Pokemon.csv"); // Load all Pokémon data into the list
        process(new File(filename)); // Process the input commands
    }

    // Method to load all Pokémon data from the CSV file into the ArrayList
    private void loadPokemonData(String csvFile) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(csvFile))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                // Assuming CSV format: id,name,type1,type2,total,hp,attack,defense,specialAttack,specialDefense,speed,generation,isLegendary
                String[] attributes = line.split(",");
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
            }
        } catch (FileNotFoundException e) {
            System.out.println("CSV file not found: " + csvFile);
            throw e;
        }
    }

    // Process the input file and handle commands
    public void process(File input) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim(); // Remove leading and trailing spaces
                if (line.isEmpty()) continue; // Skip blank lines

                // Split the command by spaces
                String[] command = line.split("\\s+");

                // Call operate_BST to execute the command
                operate_BST(command);
            }
        }
    }

    // Determine the incoming command and operate on the BST
    public void operate_BST(String[] command) {
        String operation = command[0].toLowerCase(); // Convert to lowercase to handle case sensitivity
        try {
            switch (operation) {
                case "insert":
                    if (command.length < 2) {
                        writeToFile("Invalid Command", "./output.txt");
                        return;
                    }
                    String insertName = command[1];
                    Pokemon insertPokemon = findPokemonByName(insertName);
                    if (insertPokemon != null) {
                        pokemonBST.insert(insertPokemon);
                        writeToFile("insert " + insertName, "./output.txt");
                    } else {
                        writeToFile("Pokemon not found: " + insertName, "./output.txt");
                    }
                    break;

                case "search":
                    if (command.length < 2) {
                        writeToFile("Invalid Command", "./output.txt");
                        return;
                    }
                    String searchName = command[1];
                    Pokemon searchPokemon = findPokemonByName(searchName);
                    if (searchPokemon != null && pokemonBST.search(searchPokemon)) {
                        writeToFile("found " + searchName, "./output.txt");
                    } else {
                        writeToFile("search failed", "./output.txt");
                    }
                    break;

                case "remove":
                    if (command.length < 2) {
                        writeToFile("Invalid Command", "./output.txt");
                        return;
                    }
                    String removeName = command[1];
                    Pokemon removePokemon = findPokemonByName(removeName);
                    if (removePokemon != null && pokemonBST.search(removePokemon)) {
                        pokemonBST.remove(removePokemon);
                        writeToFile("removed " + removeName, "./output.txt");
                    } else {
                        writeToFile("remove failed", "./output.txt");
                    }
                    break;

                case "print":
                    // Print the BST in ascending order
                    StringBuilder result = new StringBuilder();
                    for (Pokemon p : pokemonBST) {
                        result.append(p.getName()).append(" ");
                    }
                    writeToFile(result.toString().trim(), "./output.txt");
                    break;

                default:
                    // Handle invalid commands
                    writeToFile("Invalid Command", "./output.txt");
                    break;
            }
        } catch (Exception e) {
            // Handle invalid input cases (e.g., missing parameters)
            writeToFile("Invalid Command", "./output.txt");
        }
    }

    // Helper method to find a Pokemon by name from the list
    private Pokemon findPokemonByName(String name) {
        for (Pokemon p : pokemonList) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    // Write the output to a result file
    public void writeToFile(String content, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(content); // Write the content
            writer.newLine(); // Add a new line
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}