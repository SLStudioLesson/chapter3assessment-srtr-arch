package com.recipeapp.datahandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class CSVDataHandler implements DataHandler {
    private String filePath;

    public CSVDataHandler(String filePath) {
        this.filePath = filePath;
    }

    public CSVDataHandler() {
        this.filePath = "app/src/main/resources/recipes.csv";

    }

    public String getMode() {
        return "CSV";
    }

    @Override
    // recipes.csvからレシピデータを読み込み、それをリスト形式で返します。
    public ArrayList<Recipe> readData() throws IOException {
        ArrayList<Recipe> recipesList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                ArrayList<Ingredient> ingredientsList = new ArrayList<>();

                String[] recipes = line.split(",");
                for (int i = 1; i < recipes.length; i++) {
                    Ingredient ingredient = new Ingredient(recipes[i]);
                    ingredientsList.add(ingredient);
                }
                recipesList.add(new Recipe(recipes[0], ingredientsList));

            }
            return recipesList;

        }

    }

    @Override
    public void writeData(Recipe recipe) throws IOException {
        try(BufferedWriter writer = new BufferedWriter (new FileWriter(filePath,true))){
            
            String line = recipe.getName();
            for(Ingredient ingredient:recipe.getIngredients()){
                line+=", "+ingredient.getName();
            }
            writer.newLine();
            writer.write(line);
            
        }
       
    }

    @Override
    public ArrayList<Recipe> searchData(String keyword) throws IOException {
        // AraryList<String>list = new ArrayList<>();
        // String[]keywords = keyword.split("&");
        // for(String word :keywords){
        //     String[]word1 = word.split("=");
        //     for(String word2 :word1){
        //         list.add(word2);
        //     }
        // }
        // }
        return null;
    }

}
