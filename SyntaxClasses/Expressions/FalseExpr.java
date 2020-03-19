package Expressions;

import Syntax.ExprNode;
import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class FalseExpr extends ExprNode {

    public FalseExpr(Location left, Location right) {
        super(left, right, new Boolean(false));
    }
    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
