package Statements;

import Syntax.StatNode;
import Syntax.Vars;
import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;
public class ReadNode extends StatNode {
    private Vars vars=null;

    public ReadNode(Location left, Location right, Vars vars) {
        super(left, right);
        this.vars = vars;
    }

    public Vars getVars() {
        return vars;
    }

    public void setVars(Vars vars) {
        this.vars = vars;
    }
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
