package Statements;

import Semantic.Scoped;
import Semantic.SymbolTable;
import Syntax.StatNode;
import Syntax.Statements;
import Syntax.VarDecls;
import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class LocalNode extends StatNode implements Scoped {
    private VarDecls varDecls;
    private Statements statements;
    private SymbolTable scope;

    public LocalNode(Location left, Location right, VarDecls varDecls, Statements statements) {
        super(left, right);
        this.varDecls = varDecls;
        this.statements = statements;
    }

    public VarDecls getVarDecs() {
        return varDecls;
    }

    public void setVarDecs(VarDecls varDecls) {
        this.varDecls = varDecls;
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
