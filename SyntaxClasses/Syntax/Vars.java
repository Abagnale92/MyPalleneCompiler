package Syntax;

import Expressions.IdExpr;
import Visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory.Location;

import java.util.ArrayList;
public class Vars {
    private ArrayList<IdExpr> ids=new ArrayList<IdExpr>();
    private Location left, right;

    public Vars(Location left, Location right ,String id) {
        this.ids.add(new IdExpr(left,right,id));
        this.left = left;
        this.right = right;
    }
    public  ArrayList<IdExpr> getIds(){return this.ids;}
    public void add(String id) {
        this.ids.add(new IdExpr(left,right,id));
    }
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
