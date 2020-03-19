package Syntax;

import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

public abstract class StatNode {


    Location right;
    public StatNode(Location left, Location right) {
        this.right = right;
        this.left = left;
    }

    public Location getRight() {
        return right;
    }

    public void setRight(Location right) {
        this.right = right;
    }

    public Location getLeft() {
        return left;
    }

    public void setLeft(Location left) {
        this.left = left;
    }

    Location left;
    public abstract  <T> T accept(Visitor<T> visitor);
}
