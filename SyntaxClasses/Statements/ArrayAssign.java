package Statements;

import Syntax.ExprNode;
import Syntax.StatNode;
import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;
public class ArrayAssign extends StatNode {
    private ExprNode expr1;
    private ExprNode expr2;
    private ExprNode expr3;

    public ArrayAssign(Location left, Location right, ExprNode expr1, ExprNode expr2, ExprNode expr3) {
        super(left, right);
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.expr3 = expr3;
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

    public ExprNode getExpr3() {
        return expr3;
    }

    public void setExpr3(ExprNode expr3) {
        this.expr3 = expr3;
    }

    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
