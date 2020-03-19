package Syntax;

import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;
public class VarDecNode {
    private String id;
    private TypeNode decType;
    VarInitNode varInitNode;
    private Location left, right;
    public VarDecNode(Location left, Location right, String s, TypeNode t , VarInitNode vi) {
        this.left = left;
        this.right = right;
        this.id=s;
        this.decType=t;
        this.varInitNode=vi;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TypeNode getDecType() {
        return decType;
    }

    public VarInitNode getVarInitNode() {
        return varInitNode;
    }

    public String getId(){return this.id;}

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
