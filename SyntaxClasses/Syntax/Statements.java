package Syntax;

import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

import java.util.ArrayList;
public class Statements {
    private Location left, right;
    private ArrayList<StatNode> statements=new ArrayList<StatNode>();

    public Statements(Location left, Location right, ArrayList<StatNode> statements) {
        this.left = left;
        this.right = right;
        this.statements = statements;
    }
    public void add(StatNode statnode){
        this.statements.add(statnode);
    }
    public Statements (StatNode statNode){
        this.left=statNode.getLeft();
        this.right=statNode.getRight();
        this.statements.add(statNode);
    }
    public Statements(Location left, Location right) {

        this.left = left;
        this.right = right;

    }
    public ArrayList<StatNode> getStatements() {
        return statements;
    }

    public void setStatements(ArrayList<StatNode> statements) {
        this.statements = statements;
    }

    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
