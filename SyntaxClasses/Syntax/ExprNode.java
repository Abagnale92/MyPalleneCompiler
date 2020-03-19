package Syntax;

import Semantic.SymbolTable;
import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

public abstract class ExprNode{
    public Location right;
    public Location left;
    SymbolTable.Type returnType;
    public void setReturnType(SymbolTable.Type returnType){this.returnType=returnType;}
    public SymbolTable.Type getReturnType(){return this.returnType;}
    private  Object value;
    public ExprNode(Location left, Location right, Object value) {
        this.right = right;
        this.left = left;
        this.value = value;
    }

    public ExprNode(Location left, Location right) {
        this.right = right;
        this.left = left;
        this.value= null;
    }



    public ExprNode (Object value ){this.value=value; this.left=null; this.right=null;}

    public ExprNode(){this.value=null;}

    public abstract <T> T accept(Visitor<T> visitor);
}