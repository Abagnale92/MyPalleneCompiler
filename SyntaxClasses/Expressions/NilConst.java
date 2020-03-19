package Expressions;

import Syntax.ExprNode;
import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class NilConst extends ExprNode {
    private final String nil="NIL";
    public NilConst(Location left, Location right) {
        super(left, right, "NIL");
    }
    public String getValue (){
        return this.nil;
    }
    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
