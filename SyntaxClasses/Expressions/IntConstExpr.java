package Expressions;

import Syntax.ExprNode;
import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class IntConstExpr extends ExprNode{
    private int integer=0;
    public int getValue(){return integer;}

    public IntConstExpr(Location left, Location right, Integer value) {
        super(left, right, value); this.integer=value; }
    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
