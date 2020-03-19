package Expressions;

import Syntax.ExprNode;
import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class IdExpr extends ExprNode {
    private  String id;
    public String getId(){return this.id;}
    private Location left, right;
    public IdExpr(Location left, Location right, String value) {
        super(left, right);
        this.left=left;
        this.right=right;
        this.id=value;
    }

    public Location getLeft() {
        return left;
    }

    public Location getRight() {
        return right;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
