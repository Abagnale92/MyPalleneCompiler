package Expressions;

import Syntax.ExprNode;
import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class StringConstExpr extends ExprNode {
    private String value;
    public StringConstExpr(Location left, Location right, String value) {
        super(left, right, value);
        this.value=value;
    }

    public String getValue(){
        return value;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}