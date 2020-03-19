package Statements;

import Expressions.Exprs;
import Syntax.StatNode;
import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory;

public class WriteNode extends StatNode{

    private Exprs expressions;

    public Exprs getExprs() {
        return expressions;
    }

    public void setExprs(Exprs expressions) {
        this.expressions = expressions;
    }

    public WriteNode(ComplexSymbolFactory.Location left, ComplexSymbolFactory.Location right, Exprs expressions) {
        super(left, right);
        this.expressions = expressions;
    }

    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
