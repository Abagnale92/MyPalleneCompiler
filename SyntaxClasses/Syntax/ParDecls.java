package Syntax;

import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

import java.util.ArrayList;

public class ParDecls {

    private Location left;
    private Location right;
    private ArrayList<ParDecNode> parDecNodes= new ArrayList<ParDecNode>();
    public ParDecls(Location left, Location right) {
        this.left=left;
        this.right=right;
    }
    public void add (Location left, Location right, String id, TypeNode type) {
        ParDecNode temp = new ParDecNode(left, right, id, type);
        this.parDecNodes.add(temp);
    }

    public ParDecls(ParDecNode parDecNode) {
        this.left=parDecNode.getLeft();
        this.right=parDecNode.getRight();
        this.parDecNodes.add(parDecNode);

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

    public ArrayList<ParDecNode> getParDecNodes() {
        return parDecNodes;
    }

    public void setVarDecNodes(ArrayList<ParDecNode> parDecNodes) {
        this.parDecNodes = parDecNodes;
    }

    public ParDecls(Location left, Location right, ArrayList<ParDecNode>parDecNodes) {
        this.left = left;
        this.right = right;
        this.parDecNodes = parDecNodes;
    }
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
