package Syntax;

import Semantic.Scoped;
import Semantic.SymbolTable;
import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class ProgramNode implements Scoped {
    private Location left;
    private Location right;
    private SymbolTable scope;
    private SymbolTable globSymTable;

    public SymbolTable getGlobSymTable() {
        return globSymTable;
    }

    private GlobalNode global;
    private FunctionsNode functions;
    public FunctionsNode getFunctions() {
        return functions;
    }
    public void setGlobSymTable(SymbolTable global){ this.globSymTable=global;}
    public void setFunctions(FunctionsNode functions) {
        this.functions = functions;
    }

    public ProgramNode(Location left, Location right, GlobalNode global, FunctionsNode functions) {
        this.global = global;
        this.functions = functions;
        this.left = left;
        this.right = right;
        this.globSymTable=null;
    }
    public GlobalNode getGlobal() {
        return global;
    }

    public void setGlobal(GlobalNode global) {
        this.global = global;
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
