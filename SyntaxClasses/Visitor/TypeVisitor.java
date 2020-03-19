package Visitor;

import Expressions.*;
import Semantic.SemanticSym;
import Semantic.SymbolTable;
import Semantic.SymbolTable.Type;
import Statements.*;
import Syntax.*;
import java_cup.runtime.ComplexSymbolFactory.*;

import java.util.ArrayList;
import java.util.Stack;


public class TypeVisitor implements Visitor<Object>
{

    private Stack<SymbolTable> tables;
    private SymbolTable actualScope;
    private SymbolTable globalSymbolTable;
    public TypeVisitor(SymbolTable globalSymbolTable) {

        this.globalSymbolTable = globalSymbolTable;
        this.tables=new Stack<SymbolTable>();

    }

    private SemanticSym getSym(String id)
    {
        SymbolTable temp = this.actualScope;
        while (temp!=null)
        {
            if (temp.probe(id)){
                return temp.lookUp(id);
            }
            temp = temp.getParentTable();
        }
        return null;
    }

    private final SymbolTable.Type[][] ArithOpTable = {
            {Type.INT, null, Type.FLOAT, null,null},
            {null, Type.BOOL,null, null, null},
            {Type.FLOAT, null, Type.FLOAT, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null}
    };

    private final SymbolTable.Type[][] relOpTable = {
            {Type.BOOL, null, Type.BOOL, null, null},
            {null, Type.BOOL, null, null, null},
            {Type.BOOL, null, Type.BOOL, null, null},
            {null, null, null, Type.INT, null},
            {null, null, null, null, null}
    };

    private final SymbolTable.Type[][] assignOpTable = {
            {Type.INT, null, Type.INT, null, null},
            {null, Type.BOOL, null,null, null},
            {Type.FLOAT, null, Type.FLOAT, null, null},
            {null, null, null, Type.STRING, null},
            {null, null, null, null, null}
    };

    private final SymbolTable.Type[][] EqOpTable = {
            {Type.BOOL, null, Type.BOOL, null, null},
            {null, Type.BOOL, null, null, null},
            {Type.BOOL, null, Type.BOOL, null, null},
            {null, null, null, Type.INT, null},
            {null, null, null, null, null}
    };

    private final SymbolTable.Type[][] uminusTable = {{Type.INT, null, Type.FLOAT, null, null}
    };

    private int getIntFromType(SymbolTable.Type t){
        int toReturn;
        switch(t) {
            case INT: toReturn = 0; break;
            case BOOL: toReturn = 1; break;
            case FLOAT: toReturn = 2; break;
            case STRING: toReturn = 3; break;
            case NIL: toReturn = 4; break;
            default: toReturn = -1;
        }
        return toReturn;
    }

    public Type getToEnum (TypeNode types){
        Type toReturn;
        switch(types.getType().toLowerCase()){
            case "int": toReturn = Type.INT;break;
            case "bool": toReturn = Type.BOOL;break;
            case "float": toReturn = Type.FLOAT;break;
            case "string": toReturn = Type.STRING;break;
            case "nil": toReturn = Type.NIL;break;
            default: toReturn = null;
        }
        types.setReturnType(toReturn);
        return toReturn;
    }

    public Object visit(AndExpr expr){
        Type t1 = (Type) expr.getLeft().accept(this);
        Type t2 = (Type) expr.getRight().accept(this);
        Type toReturn = (t1 == t2) && ( t2 == Type.BOOL)? Type.BOOL : null;
        if(toReturn == null){
            System.err.println("Errore tipo in AND");
        }
        expr.setReturnType(toReturn);
        return toReturn;
    }

    public Object visit(ArrayConst expr){
        Type t1 = (Type) expr.getTypeNode().accept(this);
        expr.setReturnType(t1);
        return t1;
    }


    public Object visit(ParDecls parDecls) {
        for ( ParDecNode pd : parDecls.getParDecNodes())
        {
            pd.accept(this);
        }
        return Type.NIL;
    }

    public Object visit (TypeNode types){
        if (types.getTypesProduction()!=null) {
            types.getTypesProduction().accept(this);
        }
        Type toReturn;
        switch(types.getType().toUpperCase()){
            case "INT": toReturn = Type.INT;break;
            case "BOOL": toReturn = Type.BOOL;break;
            case "FLOAT": toReturn = Type.FLOAT;break;
            case "STRING": toReturn = Type.STRING;break;
            case "NIL": toReturn = Type.NIL;break;
            default: toReturn = null;break;
        }
        types.setReturnType(toReturn);
        return toReturn;
    }

    public Object visit(DivExpr expr) {
       Type t1 = (Type)expr.getLeft().accept(this);
       Type t2 = (Type) expr.getRight().accept(this);
        ExprNode ex1 = expr;
       Type toReturn = this.ArithOpTable[getIntFromType(t1)][getIntFromType(t2)];
       if(toReturn == null){
           System.err.println("Errore in Div Expr" + " L: "+ ex1.left + " C: " + ex1.right);
       }
       expr.setReturnType(toReturn);
        return toReturn;
    }


    public Object visit(NopNode nopNode) {
        return Type.NIL;
    }

    public Object visit(FunctionCallStatement functionCallStatement) //ATTENZIONE
    {
            if (functionCallStatement.getId().equals("main"))
                throw new IllegalArgumentException();
            if (globalSymbolTable.probe(functionCallStatement.getId())) {
                if (!globalSymbolTable.lookUp(functionCallStatement.getId()).getValue().equals("function")) {
                    System.err.println("ERROR! " + functionCallStatement.getId() + " not declared as function");
                }
            }
        functionCallStatement.getExprs().accept(this);
        if (SymbolTable.FunDefPars.get(functionCallStatement.getId())!=null)
            SymbolTable.FunDefPars.get(functionCallStatement.getId()).accept(this);
        ArrayList<ParDecNode> pd=null;
        if (SymbolTable.hasParams(functionCallStatement.getId())) { //se ha parametri
            pd = SymbolTable.FunDefPars.get(functionCallStatement.getId()).getParDecNodes(); //recupera la lista parametri
            if (pd.size()!= functionCallStatement.getExprs().getExpressionNodes().size()) {//se il numero di parametri differisce, lancia errore
                System.err.println("Error! Wrong number of arguments for function: " + functionCallStatement.getId() + " L :" + functionCallStatement.getLeft() + " C: "+ functionCallStatement.getRight());
                return null;
            }
            else { //Se il numero di parametri coincide, verifica uno ad uno che i loro tipi coincidano
                int cnt=0;
                for (ExprNode x : functionCallStatement.getExprs().getExpressionNodes()) {
                    Type t = (Type) x.accept(this);
                    if (!t.equals(pd.get(cnt).getType().getEnumReturnType())) {
                        System.err.println("StatArgument number:  " + cnt + " has different type than prototype L: " + functionCallStatement.getLeft() + " C: " + functionCallStatement.getRight());
                        return null;
                    }
                    cnt++;
                }
            }

        }
        else if( functionCallStatement.getExprs().getExpressionNodes().size() != 1 || !(functionCallStatement.getExprs().getExpressionNodes().get(0) instanceof NilConst) ) { //se invece non ha parametri, lancia errore se non è chiamato con nil
            System.err.println("Error! Function " + functionCallStatement.getId() + " declared with no parameters! L: " + functionCallStatement.getLeft() + " C: " +functionCallStatement.getRight());
            return null;
        }
        return Type.NIL;
    }

    public Object visit(AssignExprToId assignExprToId) { //vedere tipo di ritorno da sym
            Type t1 = (Type) assignExprToId.getExpr().accept(this);
            SemanticSym sm =getSym(assignExprToId.getId());
            Type t2 = sm.getTypeNode().getEnumReturnType();
            Type toReturn = this.assignOpTable[getIntFromType(t1)][getIntFromType(t2)];
            if(toReturn == null){
                System.err.println("Errore in AssignExpr" + " L: " + assignExprToId.getLeft() + " C: " + assignExprToId.getRight());
            }
            return toReturn;
    }

    public Object visit(ArrayAccess expr){
        Type t1 = (Type) expr.getExpr1().accept(this);
        Type t2 = (Type)expr.getExpr2().accept(this);
        Location l, r;
        if (expr.getExpr1() instanceof  IdExpr){
            IdExpr tmp = (IdExpr) expr.getExpr1();
            l=tmp.getLeft();
            r=tmp.getRight();
            if (getSym(tmp.getId()).getValue().equals("array"))
            {
                if (t2 != Type.INT) {
                    System.err.println("Errore ArrayAccess" + " L: " + expr.left + " C: " + expr.right);
                }
                expr.setReturnType(t1);
                return t1;
            }
            else{
                System.err.println("ERROR!" + tmp.getId() + " not declared as array!" + "L: " + l + " R: " + r);
                return null;
            }
        }
        else {
            System.err.println("Error! Object is not an array! L: " +expr.left + "C: " + expr.right);
        }
        return null;
    }

    public Object visit(EqExpr expr){
        Type t1 = (Type)expr.getLeft().accept(this);
        Type t2 = (Type)expr.getRight().accept(this);
        Type toReturn = this.EqOpTable[getIntFromType(t1)][getIntFromType(t2)];
        if(toReturn == null){
            ExprNode ex = expr.getLeft();
            System.err.println("Errore EqExpr! L: " + ex.left + " C: "+ ex.right);
        }
        expr.setReturnType(Type.NIL);
        return toReturn;
    }
    public Object visit(Exprs expr){
        for (ExprNode x: expr.getExpressionNodes()) {
            x.accept(this);
        }
        return null;
    }
    public Object visit(FalseExpr expr){
        expr.setReturnType(Type.BOOL);
        return Type.BOOL;
    }

    public Object visit(FloatConstExpr expr){
        expr.setReturnType(Type.FLOAT);
        return Type.FLOAT;
    }

    public Object visit(GeExpr expr) {
       Type t1 = (Type) expr.getLeft().accept(this);
       Type t2 = (Type) expr.getRight().accept(this);
       Type toReturn = this.relOpTable[getIntFromType(t1)][getIntFromType(t2)];
       if(toReturn == null){
           System.err.println("Error GEexpr");
       }
       expr.setReturnType(toReturn);
       return toReturn;
    }
    public Object visit(GtExpr expr){
        Type t1 = (Type) expr.getLeft().accept(this);
        Type t2 = (Type) expr.getRight().accept(this);
        Type toReturn = this.relOpTable[getIntFromType(t1)][getIntFromType(t2)];
        if(toReturn == null){
            System.err.println("Error Gtexpr");
        }
        expr.setReturnType(toReturn);
        return toReturn;
    }
    public Object visit(IdExpr expr){
        if (expr==null)
            System.err.println("ERROR");
        SemanticSym sm = getSym(expr.getId());
        expr.setReturnType(sm.getTypeNode().getEnumReturnType());
        return sm.getTypeNode().getEnumReturnType();
    }

    public Object visit(FunctionCallExpr expr){
        try {
            if (expr.getId().equals("main"))
                throw new IllegalArgumentException();
            if (globalSymbolTable.probe(expr.getId())==false){
                throw new IllegalStateException();
            }
            else {
                SemanticSym temp = globalSymbolTable.lookUp(expr.getId());
                if (!(temp.getValue().equals("function")))
                    throw new IllegalStateException();
            }
        }  catch (IllegalStateException e){System.err.println("Error: " + expr.getId() + " undeclared!" +" R: "+ expr.getLeft()  + " C: " + expr.getRight());}
        catch ( IllegalArgumentException e){System.err.println("Error! Can't call main() as a function!");}
        if (SymbolTable.FunDefPars.get(expr.getId())!=null)
            SymbolTable.FunDefPars.get(expr.getId()).accept(this);
        ArrayList<ParDecNode> pd=null;
        if (SymbolTable.hasParams(expr.getId())) { //se ha parametri
            pd = SymbolTable.FunDefPars.get(expr.getId()).getParDecNodes(); //recupera la lista parametri
            if (pd.size()!= expr.getExpressions().getExpressionNodes().size()) {//se il numero di parametri differisce, lancia errore
                System.err.println("Error! Wrong number of arguments for function: " + expr.getId() + " L :" + expr.getLeft() + " C: "+ expr.getRight());
                return null;
            }
            else { //Se il numero di parametri coincide, verifica uno ad uno che i loro tipi coincidano
                int cnt=0;
                for (ExprNode x : expr.getExprs().getExpressionNodes()) {
                    Type t = (Type) x.accept(this);
                    if (!t.equals(pd.get(cnt).getType().getEnumReturnType())) {
                        System.err.println("ExprArgument number:  " + cnt + " has different type than prototype L: " + expr.getLeft() + " C: " + expr.getRight());
                        return null;
                    }
                    cnt++;
                }
            }

        }
       else if( expr.getExpressions().getExpressionNodes().size() != 1 || !(expr.getExpressions().getExpressionNodes().get(0) instanceof NilConst) ) { //se invece non ha parametri, lancia errore se non è chiamato con nil
                System.err.println("Error! Function " + expr.getId() + " declared with no parameters! L: " + expr.getLeft() + " C: " +expr.getRight());
                return null;
        }

        SemanticSym sm = this.globalSymbolTable.lookUp(expr.getId());
        expr.setReturnType(getToEnum(sm.getTypeNode()));
        return expr.getReturnType();
    }

    public Object visit(IntConstExpr expr){
        expr.setReturnType(Type.INT);
        return Type.INT;
    }

    public Object visit(LeExpr expr){
        Type t1 = (Type) expr.getLeft().accept(this);
        Type t2 = (Type) expr.getRight().accept(this);
        Type toReturn = this.relOpTable[getIntFromType(t1)][getIntFromType(t2)];
        if(toReturn == null){
            System.err.println("Error LEexpr! L: " + expr.getLeft().left + "C: "+ expr.getLeft().right );
        }
        expr.setReturnType(toReturn);
        return toReturn;
    }
    public Object visit(LtExpr expr){
        Type t1 = (Type) expr.getLeft().accept(this);
        Type t2 = (Type) expr.getRight().accept(this);
        Type toReturn = this.relOpTable[getIntFromType(t1)][getIntFromType(t2)];
        if(toReturn == null){
            System.err.println("Error LTEexpr! L: " + expr.getLeft().left + "C: "+ expr.getLeft().right );
        }
        expr.setReturnType(toReturn);
        return toReturn;
    }
    public Object visit(MinusExpr expr){
        Type t1 = (Type)expr.getLeft().accept(this);
        Type t2 = (Type) expr.getRight().accept(this);
        Type toReturn = this.ArithOpTable[getIntFromType(t1)][getIntFromType(t2)];
        if(toReturn == null){
            System.err.println("Errore in Minus Expr! L: " + expr.getLeft().left + "C: "+ expr.getLeft().right );
        }
        expr.setReturnType(toReturn);
        return toReturn;
    }
    public Object visit(NeExpr expr){
        Type t1 = (Type) expr.getLeft().accept(this);
        Type t2 = (Type) expr.getRight().accept(this);
        Type toReturn = this.relOpTable[getIntFromType(t1)][getIntFromType(t2)];
        if(toReturn == null){
            System.err.println("Error NEexpr! L: " + expr.getLeft().left + "C: "+ expr.getLeft().right );
        }
        expr.setReturnType(toReturn);
        return toReturn;
    }
    public Object visit(NilConst expr){
        expr.setReturnType(Type.NIL);
        return Type.NIL;
    }
    public Object visit(OrExpr expr){
        Type t1 = (Type) expr.getLeft().accept(this);
        Type t2 = (Type) expr.getRight().accept(this);
        Type toReturn = (t1 == t2) && ( t2 == Type.BOOL)? Type.BOOL : null;
        if(toReturn == null){
            System.err.println("Errore tipo in OR! L: " + expr.getLeft().left + "C: "+ expr.getLeft().right );
        }
        expr.setReturnType(toReturn);
        return toReturn;
    }
    public Object visit(PlusExpr expr) {
        Type t1 = (Type)expr.getLeft().accept(this);
        Type t2 = (Type) expr.getRight().accept(this);
        Type toReturn = this.ArithOpTable[getIntFromType(t1)][getIntFromType(t2)];
        if(toReturn == null){
            System.err.println("Errore in Plus Expr! L: " + expr.getLeft().left + "C: "+ expr.getLeft().right );
        }
        expr.setReturnType(toReturn);
        return toReturn;
    }

    public Object visit(StringConstExpr expr){
        expr.setReturnType(Type.STRING);
        return Type.STRING;
    }

    public Object visit(TimesExpr expr){
        Type t1 = (Type)expr.getLeft().accept(this);
        Type t2 = (Type) expr.getRight().accept(this);
        Type toReturn = this.ArithOpTable[getIntFromType(t1)][getIntFromType(t2)];
        if(toReturn == null){
            System.err.println("Errore in Times Expr! L: " + expr.getLeft().left + "C: "+ expr.getLeft().right );
        }
        expr.setReturnType(toReturn);
        return toReturn;
    }
    public Object visit(UnaryExpr expr) {
        Type tmp = (Type) expr.getExpression().accept(this);
        String unaryOp = expr.getUnaryOp();
        if( unaryOp.equals("-") && (tmp == Type.INT || tmp == Type.FLOAT )){
            expr.setReturnType(tmp);
            return tmp;
        }
        else if( unaryOp.equals("NOT") && (tmp == Type.BOOL)){
            expr.setReturnType(tmp);
            return tmp;
        }
        else if( unaryOp.equals("#")){
            expr.setReturnType(Type.INT);
            return Type.INT;
        }
        System.err.println("MisMatch UnaryOp! L: " + expr.left + "C: "+ expr.right );
        return null;
    }

    public Object visit(TrueExpr trueExpr) {
        trueExpr.setReturnType(Type.BOOL);
        return Type.BOOL;
    }

    public Object visit (DefFun function){
        this.globalSymbolTable.lookUp(function.getId()).setReturnType(function.getType());
        this.tables.push(function.getScope());
        this.actualScope=this.tables.peek();
        String funName=function.getId();
        function.getType().accept(this);
        if (function.getParams()!=null)
            function.getParams().accept(this);
        function.getStatements().accept(this);
        this.tables.pop();
        this.actualScope=this.tables.peek();
        return Type.NIL;
    }

    public Object visit (GlobalNode global){
        global.getDeclarations().accept(this);
        return Type.NIL;
    }

    public Object visit (ParDecNode pardec){
        pardec.getType().accept(this);
        if (pardec.getType().getTypesProduction()!=null)
            pardec.getType().getTypesProduction().accept(this);
        return Type.NIL;
    }

    public Object visit (Statements statements){
        for (StatNode x: statements.getStatements()){
            x.accept(this);
        }
        return Type.NIL;
    }
    public Object visit (IfThenNode ifthen){
        ifthen.getIfExpr().accept(this);
        ifthen.getThenStatements().accept(this);
        return Type.NIL;
    }

    public Object visit (IfThenElseNode ifThenElse){
        ifThenElse.getIfExpr().accept(this);
        ifThenElse.getThenStatements().accept(this);
        ifThenElse.getElseStatements().accept(this);
        return Type.NIL;
    }

    public Object visit (ForNode forNode){

        this.tables.push(forNode.getScope());
        this.actualScope=this.tables.peek();
        SemanticSym sm=getSym(forNode.getId());
        TypeNode tp = new TypeNode(forNode.getLeft(), forNode.getRight(), "INT");
        tp.setReturnType(Type.INT);
        sm.setReturnType(tp);
        forNode.getAssExpr().accept(this);
        forNode.getCondexpr().accept(this);
        forNode.getStatements().accept(this);
        this.tables.pop();
        this.actualScope=this.tables.peek();
        return null;
    }

    public Object visit (VarDecNode varDec) {
        Type t1 = (Type) varDec.getDecType().accept(this);

        if (varDec.getVarInitNode() == null) {
            return t1;
        }
        Type t2 = (Type) varDec.getVarInitNode().getExpr().accept(this);
        if (getSym(varDec.getId()).getValue().equals("array")){
            if (t1 != t2)
                System.err.println("ERROR! Type mismatch in array creation! L: " + varDec.getLeft() + "C: " + varDec.getRight());
        }
        Type toReturn = this.assignOpTable[getIntFromType(t1)][getIntFromType(t2)];
        if (toReturn==null)
        {
            System.err.println("TYPE MISMATCH BETWEEN: " + varDec.getId() + " and right assign operand");
        }
        return toReturn;
    }

    public Object visit (VarInitNode varInit) {
        return varInit.getExpr().accept(this);
    }


    public Object visit (ProgramNode program) {
        this.globalSymbolTable=program.getGlobSymTable();
        this.tables.push(program.getScope());
        this.actualScope = this.tables.peek();
        program.getGlobal().accept(this);
        program.getFunctions().accept(this);
        try {
            if (!this.globalSymbolTable.probe("main")) {
                throw new IllegalStateException();
            }
        }
        catch (IllegalStateException e) {System.err.println("Error! main() undefined!");}
        this.tables.pop();
        return program;
    }

    public Object visit (Types types){

        for (TypeNode temp :  types.getTypes()){
            temp.accept(this);
        }
        return Type.NIL;
    }

    public Object visit (Vars vars){
        for ( IdExpr x : vars.getIds())
        {
            x.accept(this);
        }
        return Type.NIL;
    }

    public Object visit (WhileNode whileNode){
        if(( whileNode.getExpression().accept(this) != Type.INT) && (whileNode.getExpression().accept(this) != Type.BOOL) ){
            System.err.println("Errore WhileNode expr");
        }
        whileNode.getStatements().accept(this);
        return Type.NIL;
    }

    public Object visit (VarDecls varDecls){
        for (VarDecNode x : varDecls.getVarNodes())
        {
            x.accept(this);
        }
        return Type.NIL;
    }

    public Object visit (LocalNode localNode){
        this.tables.push(localNode.getScope());
        this.actualScope=this.tables.peek();
        localNode.getVarDecs().accept(this);
        localNode.getStatements().accept(this);
        this.tables.pop();
        this.actualScope=this.tables.peek();
        return Type.NIL;
    }

    public Object visit (ArrayAssign arrayAssign){
        Type t1 = (Type) arrayAssign.getExpr1().accept(this);
        arrayAssign.getExpr1().setReturnType(t1);
        Type t2 = (Type) arrayAssign.getExpr2().accept(this);
        arrayAssign.getExpr2().setReturnType(t2);
        Type t3 = (Type) arrayAssign.getExpr3().accept(this);
        arrayAssign.getExpr3().setReturnType(t3);
        if ((arrayAssign.getExpr1() instanceof IdExpr)){
            IdExpr exprTemp = (IdExpr) arrayAssign.getExpr1();
            if (!getSym(exprTemp.getId()).getValue().equals("array")){
                System.err.println("Not declared as Array!");
            }
        }
        else {
            System.err.println("Need array ID as left operand");
        }
        if (assignOpTable[getIntFromType(t1)][getIntFromType(t3)] == null){
            System.err.println("Errore ArrayAssign");
        }
        if (t2 != Type.INT)
            System.err.println("Errore Tipo Indice ArrayAasign");
        return Type.NIL;
    }


    public Object visit (ReadNode readNode){
        readNode.getVars().accept(this);
        return Type.NIL;
    }

    public Object visit (WriteNode writeNode){
        writeNode.getExprs().accept(this);
        return Type.NIL;
    }

    public Object visit (ReturnNode returnNode){
        Type t1 = (Type)returnNode.getExpression().accept(this);
        SymbolTable temp= this.actualScope;
        SemanticSym sm =globalSymbolTable.lookUp(temp.getCurrentScopeName());
        while (sm==null) {
            temp=temp.getParentTable();
            sm =globalSymbolTable.lookUp(temp.getCurrentScopeName());
       }
        if (t1 != sm.getTypeNode().getEnumReturnType()){
            System.err.println("ERROR! Return type for function: " + temp.getCurrentScopeName() + " differs from return statement. L: "+ returnNode.getLeft() + " C: "+ returnNode.getRight() );
            }
        return t1;
    }

    public Object visit (FunctionsNode functionsNode) {
        for (DefFun x : functionsNode.getFunctions()) {
            if (x.getParams()!=null)
                x.getParams().accept(this);
            x.accept(this);
        }
        return Type.NIL;

    }
}
