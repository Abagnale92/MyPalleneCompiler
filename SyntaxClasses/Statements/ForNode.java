package Statements;

import Semantic.Scoped;
import Semantic.SymbolTable;
import Syntax.ExprNode;
import Syntax.StatNode;
import Syntax.Statements;
import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class ForNode extends StatNode implements Scoped {
    private String id;
    private StatNode assExpr;
    private ExprNode condexpr;
    private Statements statements;
    private SymbolTable scope;

    public ForNode(Location left, Location right, String id, StatNode assExpr, ExprNode condexpr, Statements statements) {
        super(left, right);
        this.id = id;
        this.assExpr = assExpr;
        this.condexpr = condexpr;
        this.statements = statements;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StatNode getAssExpr() {
        return assExpr;
    }

    public void setAssExpr(StatNode assExpr) {
        this.assExpr = assExpr;
    }

    public ExprNode getCondexpr() {
        return condexpr;
    }

    public void setCondexpr(ExprNode condexpr) {
        this.condexpr = condexpr;
    }

    public Statements getStatements() {
        return statements;
    }

    public void setStatements(Statements statements) {
        this.statements = statements;
    }
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public void attachScope(SymbolTable scope) {
        this.scope=scope;
    }

    @Override
    public SymbolTable getScope() {
        return this.scope;
    }
}
