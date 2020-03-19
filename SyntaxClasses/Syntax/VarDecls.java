package Syntax;

import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

import java.util.ArrayList;

public class VarDecls {

    private Location left;
    private Location right;
    private ArrayList<VarDecNode> varNodes= new ArrayList<VarDecNode>();

    public VarDecls(VarDecNode varDec){
        this.left=varDec.getLeft();
        this.right=varDec.getRight();
        this.varNodes.add(varDec);
    }
    public VarDecls(Location left, Location right, ArrayList<VarDecNode> varNodes) {
        this.left = left;
        this.right = right;
        this.varNodes = varNodes;
    }
    public void add(VarDecNode varNode) {
        this.varNodes.add(varNode);
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



    public ArrayList<VarDecNode> getVarNodes() {
        return varNodes;
    }

    public void setVarNodes(ArrayList<VarDecNode> varNodes) {
        this.varNodes = varNodes;
    }
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
