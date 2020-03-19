package Statements;

import Syntax.ExprNode;
import Syntax.StatNode;
import Syntax.Statements;
import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;
public class WhileNode extends StatNode{
    private ExprNode expression;
    private Statements statements;

    public WhileNode(Location left, Location right, ExprNode expression, Statements statements) {
        super(left, right);
        this.expression = expression;
        this.statements = statements;
    }

    public ExprNode getExpression() {
        return expression;
    }

    public void setExpression(ExprNode expression) {
        this.expression = expression;
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
}
