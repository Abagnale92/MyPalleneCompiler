package Syntax;

import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;
public class VarInitNode {
    private ExprNode expr;
    private Location left, right;

    public VarInitNode (ExprNode expr) {
        this.expr=expr;
    }
    public VarInitNode(Location left, Location right, ExprNode expr) {
        this.expr = expr;
        this.left = left;
        this.right = right;
    }

    public ExprNode getExpr() {
        return this.expr;
    }

    public void setExpr(ExprNode expr) {
        this.expr = expr;
    }
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
