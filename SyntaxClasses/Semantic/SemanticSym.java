package Semantic;

import Syntax.TypeNode;

public class SemanticSym {
    private  TypeNode returnType;
    private String val;

    public SemanticSym(TypeNode returnType, String value) {
        this.val=value;
        this.returnType=returnType;
    }

    public TypeNode getTypeNode() {
        return returnType;
    }

    public void setReturnType(TypeNode type) {this.returnType=type;}

    public Object getValue(){
        return  this.val;
    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj)
            return true;
        if (obj==null)
            return false;
        if (getClass()!= obj.getClass())
            return false;
        SemanticSym sm = (SemanticSym) obj;
        return returnType == sm.getTypeNode();
    }
}
