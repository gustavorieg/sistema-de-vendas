package br.com.project.javafxmvc.model.database;

public class DatabaseFactory {
    public static Database getDatabase(String nome){
        if(nome.equals("postgresql")){
            return new Database();
        }else if(nome.equals("mysql")){
            return new Database();
        }
        return null;
    }
}
