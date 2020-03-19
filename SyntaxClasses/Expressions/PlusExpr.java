package Expressions;

import Syntax.ExprNode;
import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;
public class PlusExpr extends ExprNode {
    private ExprNode left, right;

    public PlusExpr(Location left, Location right, ExprNode left1, ExprNode right1) {

        super(left, right, null);
        this.left = left1;
        this.right = right1;
    }

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
    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
