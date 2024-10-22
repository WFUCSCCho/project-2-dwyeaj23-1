/**
 * @file: Pokemon.java
 * @description: This class represents a Pokemon with various attributes such as name, type, and statistics.
 *               It implements the Comparable interface to allow for comparison based on the Pokemon's name,
 *               and provides methods for accessing and modifying each attribute. This class is used in conjunction
 *               with a Binary Search Tree (BST) to store and manage Pokemon data.
 * @author: Andrew Dwyer
 * @date: September 24, 2024
 */

public class Pokemon implements Comparable<Pokemon> {
    // Fields of the Pokemon class
    private int id; // Unique identifier for each Pokemon
    private String name; // Name of the Pokemon
    private String type1; // Primary type, affects battle strategies
    private String type2; // Secondary type (optional), for dual-type Pokemon
    private int total; // Aggregate statistic representing overall strength
    private int hp; // Health Points: Determines how much damage a Pokemon can take
    private int attack; // Attack power for standard physical attacks
    private int defense; // Resistance against standard physical attacks
    private int specialAttack; // Attack power for special (often elemental) attacks
    private int specialDefense; // Resistance against special attacks
    private int speed; // Determines the order of actions in battles
    private int generation; // The game generation a Pokemon belongs to
    private boolean isLegendary; // Flag indicating if the Pokemon is Legendary

    //Default pokemon constructor
    public Pokemon() {
        this.id = 0;
        this.name = "";
        this.type1 = "";
        this.type2 = "";
        this.total = 0;
        this.hp = 0;
        this.attack = 0;
        this.defense = 0;
        this.specialAttack = 0;
        this.specialDefense = 0;
        this.speed = 0;
        this.generation = 0;
        this.isLegendary = false;
    }
    // Constructor: Initializes a new instance of the Pokemon class
    public Pokemon(int id, String name, String type1, String type2, int total, int hp,
                   int attack, int defense, int specialAttack, int specialDefense,
                   int speed, int generation, boolean isLegendary) {
        this.id = id;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.total = total;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
        this.generation = generation;
        this.isLegendary = isLegendary;
    }

    //Copy constructor
    public Pokemon(Pokemon other) {
        this.id = other.id;
        this.name = other.name;
        this.type1 = other.type1;
        this.type2 = other.type2;
        this.total = other.total;
        this.hp = other.hp;
        this.attack = other.attack;
        this.defense = other.defense;
        this.specialAttack = other.specialAttack;
        this.specialDefense = other.specialDefense;
        this.speed = other.speed;
        this.generation = other.generation;
        this.isLegendary = other.isLegendary;
    }

    // Getter and setter methods for each field
    // Allows external classes to access and modify the fields

    public int getId() {
        return this.id;
    }

    public void setId(int newId) {
        this.id = newId;
    }
    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getType1() {
        return this.type1;
    }

    public void setType1(String newType1) {
        this.type1 = newType1;
    }

    public String getType2() {
        return this.type2;
    }

    public void setType2(String newType2) {
        this.type2 = newType2;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int newTotal) {
        this.total = newTotal;
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int newHp) {
        this.hp = newHp;
    }

    public int getAttack() {
        return this.attack;
    }

    public void setAttack(int newAttack) {
        this.attack = newAttack;
    }

    public int getDefense() {
        return this.defense;
    }

    public void setDefense(int newDefense) {
        this.defense = newDefense;
    }

    public int getSpecialAttack() {
        return this.specialAttack;
    }

    public void setSpecialAttack(int newSpecialAttack) {
        this.specialAttack = newSpecialAttack;
    }

    public int getSpecialDefense() {
        return this.specialDefense;
    }

    public void setSpecialDefense(int newSpecialDefense) {
        this.specialDefense = newSpecialDefense;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int newSpeed) {
        this.speed = newSpeed;
    }

    public int getGeneration() {
        return this.generation;
    }

    public void setGeneration(int newGeneration) {
        this.generation = newGeneration;
    }

    public boolean getIsLegendary() {
        return this.isLegendary;
    }

    public void setIsLegendary(boolean newIsLegendary) {
        this.isLegendary = newIsLegendary;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pokemon pokemon = (Pokemon) obj;
        return name.equalsIgnoreCase(pokemon.name); // Compare only by name
    }

    @Override
    public int compareTo(Pokemon other) {
        return this.name.compareToIgnoreCase(other.name); // Compare only by name
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type1='" + type1 + '\'' +
                ", type2='" + type2 + '\'' +
                ", total=" + total +
                ", hp=" + hp +
                ", attack=" + attack +
                ", defense=" + defense +
                ", specialAttack=" + specialAttack +
                ", specialDefense=" + specialDefense +
                ", speed=" + speed +
                ", generation=" + generation +
                ", isLegendary=" + isLegendary +
                '}';
    }
}