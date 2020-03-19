package Semantic;

public interface Scoped {
    void attachScope(SymbolTable scope);
    SymbolTable getScope();
}
