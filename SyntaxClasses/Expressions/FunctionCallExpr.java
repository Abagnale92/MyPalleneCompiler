package Expressions;

import Syntax.ExprNode;
import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class FunctionCallExpr extends ExprNode {
    public String id;
    public Exprs expressions;
    private Location left, right;

    public FunctionCallExpr(Location left, Location right, Exprs expressions, String id) {
        super(left, right,null);
        this.left=left;
        this.right=right;
        this.id=id;
        this.expressions = expressions;
    }

    public Exprs getExpressions() {
        return expressions;
    }

    public Location getLeft() {
        return left;
    }

    public Location getRight() {
        return right;
    }

    public FunctionCallExpr(Location left, Location right, Object value, String id, Exprs expressions) {

        System.out.println(this.id);
        this.id = id;
        this.expressions = expressions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Exprs getExprs() {
        return expressions;
    }

    public void setExprs(Exprs expressions) {
        this.expressions = expressions;
    }
    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
