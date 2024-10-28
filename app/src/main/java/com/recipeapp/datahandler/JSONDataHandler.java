package com.recipeapp.datahandler;

import java.io.IOException;
import java.util.ArrayList;

import com.recipeapp.model.Recipe;

public class JSONDataHandler implements DataHandler{
    public String getMode(){
        return "JSON";
    }

    @Override
    public ArrayList<Recipe> readData() throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    
    @Override
    public void writeData(Recipe recipe) throws IOException {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public ArrayList<Recipe> searchData(String keyword) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }
    
}
