package Syntax;
import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

import java.util.ArrayList;

public class FunctionsNode {
    private ArrayList<DefFun> functions=new ArrayList<DefFun>();
    private Location left, right;

    public FunctionsNode(Location left, Location right,DefFun funct) {
        this.functions.add(funct);
        this.left = left;
        this.right = right;
    }
    public FunctionsNode(Location left, Location right,ArrayList<DefFun> functions) {
        this.functions = functions;
        this.left = left;
        this.right = right;
    }
    public FunctionsNode(Location left, Location right) {
        this.functions = new ArrayList<DefFun>();
        this.left = left;
        this.right = right;
    }


    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
    public ArrayList<DefFun> getFunctions() {
        return functions;
    }
    public void add(DefFun df) {
        this.functions.add(df);
    }
    public void setFunctions(ArrayList<DefFun> functions) {
        this.functions = functions;
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
}
