package Semantic;

import Syntax.ParDecls;

import java.util.HashMap;

public class SymbolTable {

    public static boolean hasParams(String funName){
        return FunDefPars.get(funName) != null;
    }

    public static HashMap<String, ParDecls> FunDefPars=new HashMap<String, ParDecls>();
    public enum Type{
        INT,
        BOOL,
        FLOAT,
        STRING,
        NIL
    }
    private String currentScopeName;
    private HashMap<String, SemanticSym> currentScope;
    private SymbolTable parentTable;

    public String getCurrentScopeName() {
        return currentScopeName;
    }

    public SymbolTable getParentTable() {
        return parentTable;
    }
    public void setParentTable(SymbolTable parent){
        this.parentTable=parent;
    }

    public SymbolTable (String scopeName){
        this.parentTable=null;
        this.currentScopeName = scopeName;
        this.currentScope = new HashMap<String, SemanticSym>();
    }
    public SymbolTable (SymbolTable symbolTable){
        this.parentTable=symbolTable;
        this.currentScopeName = "";
        this.currentScope = new HashMap<String, SemanticSym>();
    }
    public SymbolTable (String scopeName, SymbolTable symbolTable){
        this.parentTable=symbolTable;
        this.currentScopeName = scopeName;
        this.currentScope = new HashMap<String, SemanticSym>();
    }

    public SemanticSym lookUp(String id){
        if(this.currentScope.containsKey(id))
            return this.currentScope.get(id);
        else
            return null;
    }



    public boolean probe(String id){
        return this.currentScope.containsKey(id);
    }


    public void addId(String id, SemanticSym entry) {
        currentScope.put(id, entry);
    }


    public void printTable(){
        this.currentScope.forEach((k,v) -> {
            System.out.println("ID: "+ k + " Type: " + v.getTypeNode().getType() + "  Kind: " + v.getValue());
        });


    }


}
