package Syntax;

import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;
public class ParDecNode {
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

    private String id;
    private  TypeNode type;
    private Location left, right;

    public ParDecNode(Location left, Location right) {
        this.left = left;
        this.right = right;
    }
    public ParDecNode(Location left, Location right, String id, TypeNode type) {
        this.id=id;
        this.type=type;
        this.left = left;
        this.right = right;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TypeNode getType() {
        return type;
    }

    public void setType(TypeNode type) {
        this.type = type;
    }

    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
