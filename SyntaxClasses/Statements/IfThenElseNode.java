package Statements;

import Syntax.ExprNode;
import Syntax.StatNode;
import Syntax.Statements;
import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;
public class IfThenElseNode extends StatNode {
    private ExprNode ifExpr;
    private Statements thenStatements;
    private Statements elseStatements;

    public IfThenElseNode(Location left, Location right, ExprNode ifExpr, Statements thenStatements, Statements elseStatements) {
        super(left, right);
        this.ifExpr = ifExpr;
        this.thenStatements = thenStatements;
        this.elseStatements = elseStatements;
    }

    public ExprNode getIfExpr() {
        return ifExpr;
    }

    public void setIfExpr(ExprNode ifExpr) {
        this.ifExpr = ifExpr;
    }

    public Statements getThenStatements() {
        return thenStatements;
    }
    public Statements getElseStatements() {
        return elseStatements;
    }
    public void setThenStatements(Statements thenStatements) {
        this.thenStatements = thenStatements;
    }
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}