package Statements;

import Expressions.Exprs;
import Syntax.StatNode;
import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class FunctionCallStatement extends StatNode{
    private String id;
    private Exprs expr;
    public FunctionCallStatement(Location left, Location right, String id, Exprs exprs) {
        super(left, right);
        this.id = id;
        this.expr = exprs;
    }
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
    public String getId(){return this.id;}

    public Exprs getExprs(){return expr;}
}
