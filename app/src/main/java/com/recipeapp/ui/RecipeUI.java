package com.recipeapp.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.recipeapp.datahandler.CSVDataHandler;
import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class RecipeUI {
    private BufferedReader reader;
    private DataHandler dataHandler;

    public RecipeUI(DataHandler dataHandler) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
    }

    public void displayRecipes() {

        try {
            DataHandler dataHandler = new CSVDataHandler();
            if (dataHandler.readData().isEmpty()||dataHandler.readData().size()==0) {
                System.out.println("No recipes available.");
                return;
            }
            System.out.println("Recipes:");
            System.out.println("-----------------------------------");
            for (Recipe recipe : dataHandler.readData()) {
                System.out.println("Recipe Name: " + recipe.getName());
                System.out.print("Main Ingredients: ");
                for (int i = 0; i < recipe.getIngredients().size(); i++) {
                    if (i == (recipe.getIngredients().size()) - 1) {
                        System.out.print(recipe.getIngredients().get(i).getName());
                    } else {
                        System.out.print(recipe.getIngredients().get(i).getName() + ", ");
                    }
                }
                
                System.out.println("\n-----------------------------------");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: 例外のメッセージ");
        }
    }

    public void searchRecipe(){
        try{System.out.println("Enter search query (e.g., 'name=Tomato&ingredient=Garlic'): ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        DataHandler dataHandler = new CSVDataHandler();
        dataHandler.searchData(input);
    }catch(IOException e){e.getMessage();}
    }

   public void addNewRecipe(){
    try{ ArrayList<Ingredient>ingredients = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Adding a new recipe.");
        System.out.print("Enter recipe name:");
        String name = reader.readLine();
        System.out.println("Enter ingredients (type 'done' when finished):");
        while(true){
            System.out.print("Ingredient:");
            String ingredient = reader.readLine();
            if(ingredient.equals("done")){break;}
            ingredients.add(new Ingredient(ingredient));
            
        }
        System.out.println("Recipe added successfully.");
        
        DataHandler dataHandler = new CSVDataHandler(); 
        dataHandler.writeData(new Recipe(name,ingredients));

   }catch(IOException e){System.out.println("Failed to add new recipe:");

   }
   }
    public void displayMenu() {

        System.out.println("Current mode: " + dataHandler.getMode());

        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        displayRecipes();
                        break;
                    case "2":
                        addNewRecipe();
                        break;
                    case "3":
                        break;
                    case "4":
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }
}
