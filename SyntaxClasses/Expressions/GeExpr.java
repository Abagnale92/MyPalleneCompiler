package Expressions;

import Syntax.ExprNode;
import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;
public class GeExpr extends ExprNode {

    public ExprNode getLeft() {
        return left;
    }

    public void setLeft(ExprNode left) {
        this.left = left;
    }

    public ExprNode getRight() {
        return right;
    }

    public void setRight(ExprNode right) {
        this.right = right;
    }

    private ExprNode left, right;

    public GeExpr(Location left, Location right, ExprNode leftExpr, ExprNode rightExpr) {
        super(left, right);
        this.left = leftExpr;
        this.right = rightExpr;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
