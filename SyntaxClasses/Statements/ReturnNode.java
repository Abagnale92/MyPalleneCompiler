package Statements;

import Syntax.ExprNode;
import Syntax.StatNode;
import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;
public class ReturnNode  extends StatNode{
    private ExprNode expression;

    public ExprNode getExpression() {
        return expression;
    }

    public void setExpression(ExprNode expression) {
        this.expression = expression;
    }

    public ReturnNode(Location left, Location right, ExprNode expression) {
        super(left, right);
        this.expression = expression;
    }

    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
