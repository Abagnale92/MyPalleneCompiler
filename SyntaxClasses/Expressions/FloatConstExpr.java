package Expressions;

import Syntax.ExprNode;
import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class FloatConstExpr extends ExprNode {
    private float floating=0.0F;
    public float getValue(){return floating;}
    public FloatConstExpr(Location left, Location right, Float floatValue) {
        super(left, right, floatValue); floating=floatValue;
    }
    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
