package Statements;

import Syntax.ExprNode;
import Syntax.StatNode;
import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;
public class AssignExprToId extends StatNode {
    private String id;
    private ExprNode expr;

    public String getId(){return this.id;}
    public ExprNode getExpr() {return  this.expr;}
    public AssignExprToId(Location left, Location right, String id, ExprNode expr) {
        super(left, right);
        this.id = id;
        this.expr = expr;
    }

    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }


}
