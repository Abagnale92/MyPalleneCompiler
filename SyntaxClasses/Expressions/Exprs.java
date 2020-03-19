package Expressions;

import Syntax.ExprNode;
import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

import java.util.ArrayList;

public class Exprs {
    private Location left;
    private Location right;
    private ArrayList<ExprNode> expressions=new ArrayList<ExprNode>();
    public Exprs(Location left, Location right) {
        this.left=left;
        this.right=right;

    }
    public Exprs(Location left, Location right, ExprNode expr) {
        this.left=left;
        this.right=right;
        this.expressions.add(expr);
    }
    public void add(ExprNode expr){
        this.expressions.add(expr);
    }
    public ArrayList<ExprNode> getExpressionNodes() {
        return expressions;
    }

    public void setExpressions(ArrayList<ExprNode> expressions) {
        this.expressions = expressions;
    }
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
