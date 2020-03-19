package Statements;

import Syntax.ExprNode;
import Syntax.StatNode;
import Syntax.Statements;
import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class IfThenNode extends StatNode {
    private ExprNode ifExpr;
    private Statements thenStatements;

    public IfThenNode(Location left, Location right,ExprNode ifExpr, Statements thenStatements) {
        super(left,right);
        this.ifExpr = ifExpr;
        this.thenStatements = thenStatements;
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

    public void setThenStatements(Statements thenStatements) {
        this.thenStatements = thenStatements;
    }
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
