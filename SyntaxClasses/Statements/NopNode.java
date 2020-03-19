package Statements;

import Syntax.StatNode;
import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class NopNode extends StatNode {
    String nop ="NOP";
    public NopNode(Location left, Location right){
        super(left, right);

    }
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
