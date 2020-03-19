package Expressions;

import Syntax.ExprNode;
import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class UnaryExpr extends ExprNode {
    private String unaryOp;
    private ExprNode expression;
    public UnaryExpr(Location left, Location right, String unaryOp,ExprNode value) {
        super(left, right);
        this.expression=value;
        this.unaryOp = unaryOp;
    }
    public ExprNode getExpression(){return this.expression;}
    public String getUnaryOp() {
        return unaryOp;
    }

    public void setUnaryOp(String unaryOp) {
        this.unaryOp = unaryOp;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
