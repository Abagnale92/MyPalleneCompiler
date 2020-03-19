package Expressions;

import Syntax.ExprNode;
import Syntax.TypeNode;
import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;
public class ArrayConst extends ExprNode {
    private TypeNode typeNode;

    public TypeNode getTypeNode() {
        return typeNode;
    }

    public void setTypeNode(TypeNode type) {
        this.typeNode = type;
    }

    public ArrayConst(Location left, Location right, TypeNode type) {
        super(left, right, null);
        this.typeNode = type;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
