package Syntax;

import Semantic.SymbolTable;
import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class TypeNode {
    private SymbolTable.Type returnType;
    private String type;
    private Types typesProduction;
    private Location left, right;

    public TypeNode(String type) {
        this.type = type;
    }

    public TypeNode(Location left, Location right, String type) {
        this.type = type;
        this.left = left;
        this.right = right;
        this.typesProduction =null;
    }
    public TypeNode(Location left, Location right, String type, Types blTypes) {
        this.type = type;
        this.left = left;
        this.right = right;
        this.typesProduction =blTypes;
    }

    public SymbolTable.Type getEnumReturnType() {
        return returnType;
    }

    public void setReturnType(SymbolTable.Type returnType) {
        this.returnType = returnType;
    }

    public void setTypesProduction(Types typesProduction) {
        this.typesProduction = typesProduction;
    }

    public void add(Types blparTypes){
        this.typesProduction =blparTypes;
    }

    public String getType() {
        return type;
    }

    public Types getTypesProduction() {
        return this.typesProduction;
    }
    public void setType(String type) {
        this.type = type;
    }

    public Location getLeft() {
        return left;
    }

    public void setLeft(Location left) {
        this.left = left;
    }

    public Location getRight() {
        return right;
    }

    public void setRight(Location right) {
        this.right = right;
    }

    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
