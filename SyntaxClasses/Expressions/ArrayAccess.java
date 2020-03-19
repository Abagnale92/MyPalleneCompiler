package Expressions;

import Syntax.ExprNode;
import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class ArrayAccess extends ExprNode{
    private ExprNode expr1, expr2;

    public ArrayAccess(Location left, Location right, ExprNode expr1, ExprNode expr2) {
        super(left, right);
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    public ExprNode getExpr1() {
        return expr1;
    }

    public void setExpr1(ExprNode expr1) {
        this.expr1 = expr1;
    }

    public ExprNode getExpr2() {
        return expr2;
    }

    public void setExpr2(ExprNode expr2) {
        this.expr2 = expr2;
    }
    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
