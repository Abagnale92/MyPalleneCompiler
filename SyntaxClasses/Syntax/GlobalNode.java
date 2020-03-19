package Syntax;

import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

import java.util.ArrayList;

public class GlobalNode {
    private VarDecls declarations;
    private Location left, right;

    public GlobalNode(Location left, Location right, VarDecls varDec) {
        this.declarations=varDec;
        this.left = left;
        this.right = right;
    }
    public GlobalNode(){
        this.left=null;
        this.right=null;
        this. declarations=null;
    }

    public VarDecls getDeclarations() {
        return declarations;
    }
    public void setDeclarations(VarDecls declarations) {
        this.declarations = declarations;
    }
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
