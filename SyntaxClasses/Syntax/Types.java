package Syntax;

import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

import java.util.ArrayList;
public class Types {
    private ArrayList<TypeNode> types=new ArrayList<TypeNode>();
    private Location right, left;
    public Types(Location left, Location right,TypeNode type) {
        this.types.add(type);
        this.right = right;
        this.left = left;
    }
    public Types(Location left, Location right) {
        this.right = right;
        this.left = left;
    }
    public void add(TypeNode t)
    {
        this.types.add(t);
    }
    public Types(TypeNode typeNode){
        this.left=typeNode.getLeft();
        this.right=typeNode.getRight();
        this.types.add(typeNode);
    }
    public ArrayList<TypeNode> getTypes() {
        return types;
    }
    public void setTypes(ArrayList<TypeNode> types) {
        this.types = types;
    }
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
