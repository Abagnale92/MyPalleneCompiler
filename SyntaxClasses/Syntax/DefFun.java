package Syntax;

import Semantic.Scoped;
import Semantic.SymbolTable;
import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;
public class DefFun implements Scoped {
    private String id;
    private Location left, right;
    private TypeNode type;
    private SymbolTable scope;
    private ParDecls params;
    private Statements statements;
    public DefFun(Location left, Location right,String id, ParDecls params, TypeNode type, Statements statements) {
        this.id=id;
        this.type=type;
        this.statements=statements;
        this.left = left;
        this.right = right;
        this.params=params;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Location getLeft() {
        return left;
    }

    public void setLeft(Location left) {
        this.left = left;
    }

    public Location getRight() {
        return right;
    }

    public void setRight(Location right) {
        this.right = right;
    }

    public TypeNode getType() {
        return type;
    }

    public void setType(TypeNode type) {
        this.type = type;
    }

    public Statements getStatements() {
        return statements;
    }

    public void setStatements(Statements statements) {
        this.statements = statements;
    }

    public ParDecls getParams() {
        return params;
    }

    public void setParams(ParDecls params) {
        this.params = params;
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
