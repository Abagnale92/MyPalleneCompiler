package Visitor;

import Expressions.*;
import Semantic.SemanticSym;
import Semantic.SymbolTable;
import Statements.*;
import Syntax.*;

import java.util.Stack;

public class SemanticVisitor implements Visitor<Object> {
    private Stack<SymbolTable> tables;
    private SymbolTable globalSymbolTable;
    private SymbolTable actualTable;

    private boolean isDefined(String id)
    {
        SymbolTable temp = this.actualTable;
        while (temp!=null)
        {
            if (temp.probe(id)){
                return true;
            }
            temp = temp.getParentTable();
        }
    return false;
    }

    public SemanticVisitor(){
        this.actualTable=new SymbolTable("");
        this.tables = new Stack<SymbolTable>();
        this.globalSymbolTable=new SymbolTable("");
    }

    public Object visit(AndExpr expr){
        expr.getLeft().accept(this);
        expr.getRight().accept(this);
        return null;
    }

    public Object visit(ArrayConst expr){
        expr.getTypeNode().accept(this);
        return null;
    }


    public Object visit(ParDecls parDecls) {

        for (ParDecNode x : parDecls.getParDecNodes()){
            x.accept(this);
        }
        return null;
    }

    public Object visit (TypeNode types){
        if (types.getTypesProduction()!=null) {
            types.getTypesProduction().accept(this);
        }
        return null;
    }

    public Object visit(DivExpr expr) {
        expr.getLeft().accept(this);
        expr.getRight().accept(this);
        return null;
    }


    public Object visit(NopNode nopNode) {
        return null;
    }

    public Object visit(FunctionCallStatement functionCallStatement)
    {

        try {
            if (functionCallStatement.getId().equals("main"))
                throw new IllegalArgumentException();
            if (globalSymbolTable.probe(functionCallStatement.getId())==false){
                throw new IllegalStateException();
            }
            else {
                SemanticSym temp = globalSymbolTable.lookUp(functionCallStatement.getId());
                if (!(temp.getValue().equals("function")))
                    throw new IllegalStateException();
            }
        }  catch (IllegalStateException e){System.err.println("Error: " + functionCallStatement.getId() + " undeclared!" +" R: "+ functionCallStatement.getLeft()  + " C: " + functionCallStatement.getRight());}
        catch ( IllegalArgumentException e){System.err.println("Error! Can't call main() as a function!");}
        functionCallStatement.getExprs().accept(this);
        return null;
    }


    public Object visit(AssignExprToId assignExprToId) {
        try {
            if (!isDefined(assignExprToId.getId()))
                throw new IllegalStateException();
        }
        catch (IllegalStateException e) {
            System.err.println("Error AssignExprToID! Variable: " + assignExprToId.getId() + " undefined in current scope!" + "R: " + assignExprToId.getLeft() + " C: " + assignExprToId.getRight());
        }
        assignExprToId.getExpr().accept(this);
        return null;
    }

    public Object visit(ArrayAccess expr){
        expr.getExpr1().accept(this);
        expr.getExpr2().accept(this);
        return null;
    }

    public Object visit(EqExpr expr){
        expr.getLeft().accept(this);
        expr.getRight().accept(this);
        return null;
    }
    public Object visit(Exprs expr){
        for (ExprNode x: expr.getExpressionNodes()) {
            x.accept(this);
        }
        return null;
    }
    public Object visit(FalseExpr expr){
        return null;
    }

    public Object visit(FloatConstExpr expr){
        return null;
    }

    public Object visit(GeExpr expr) {
        expr.getLeft().accept(this);
        expr.getRight().accept(this);
        return null;
    }
    public Object visit(GtExpr expr){
        expr.getLeft().accept(this);
        expr.getRight().accept(this);
        return null;
    }
    public Object visit(IdExpr expr){
        try {
            if (isDefined(expr.getId())) {
                return null;
            }
            else {
                throw new IllegalStateException();
            }
        }  catch(IllegalStateException e){System.err.println("Error: " + expr.getId() + " undeclared!" +" R: "+ expr.getLeft()  + " C: " + expr.getRight());}
        return null;
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
        expr.getExprs().accept(this);
        return null;
    }

    public Object visit(IntConstExpr expr){
        return null;
    }

    public Object visit(LeExpr expr){

        expr.getLeft().accept(this);
        expr.getRight().accept(this);
        return null;
    }
    public Object visit(LtExpr expr){
        expr.getLeft().accept(this);
        expr.getRight().accept(this);
        return null;
    }
    public Object visit(MinusExpr expr){
        expr.getLeft().accept(this);
        expr.getRight().accept(this);
        return null;
    }
    public Object visit(NeExpr expr){
        expr.getLeft().accept(this);
        expr.getRight().accept(this);
        return null;
    }
    public Object visit(NilConst expr){
        return null;
    }
    public Object visit(OrExpr expr){
        expr.getLeft().accept(this);
        expr.getRight().accept(this);
        return null;
    }
    public Object visit(PlusExpr expr) {
        expr.getLeft().accept(this);
        expr.getRight().accept(this);
        return null;
    }

    public Object visit(StringConstExpr expr){
        return null;
    }

    public Object visit(TimesExpr expr){
        expr.getLeft().accept(this);
        expr.getRight().accept(this);
        return null;
    }
    public Object visit(UnaryExpr expr) {
        return null;

    }

    public Object visit(TrueExpr trueExpr) {
        return null;
    }

    public Object visit (DefFun function){
        if(function.getParams()!=null)
            function.getParams().accept(this);
        function.getStatements().accept(this);
        function.getType().accept(this);
        return null;
    }

    public Object visit (GlobalNode global){
        global.getDeclarations().accept(this);
        return null;
    }

    public Object visit (ParDecNode pardec){
        this.actualTable.addId(pardec.getId(), new SemanticSym(pardec.getType(),"var"));
        if (pardec.getType().getTypesProduction()!=null)
            this.actualTable.addId(pardec.getId(), new SemanticSym(pardec.getType(), "FunctionAsParam"));
        return null;
    }

    public Object visit (Statements statements){
        for (StatNode x: statements.getStatements()){
            x.accept(this);
        }
        return null;
    }
    public Object visit (IfThenNode ifthen){
        ifthen.getIfExpr().accept(this);
        ifthen.getThenStatements().accept(this);
        return null;
    }

    public Object visit (IfThenElseNode ifThenElse){
        ifThenElse.getIfExpr().accept(this);
        ifThenElse.getThenStatements().accept(this);
        ifThenElse.getElseStatements().accept(this);
        return null;
    }

    public Object visit (ForNode forNode){
        SymbolTable symbolTable= new SymbolTable("FORLOOP");
        symbolTable.setParentTable(this.tables.peek());
        this.tables.push(symbolTable);
        this.actualTable=this.tables.peek();
        forNode.attachScope(this.actualTable);
        try {
            if (isDefined(forNode.getId())) {
                throw new IllegalStateException();
            }
        } catch (IllegalStateException e) { System.err.println("Error! For index: " + forNode.getId() + " Already defined! R:" + forNode.getLeft() + "C: "+ forNode.getRight());}
        this.actualTable.addId(forNode.getId(), new SemanticSym(new TypeNode("int"),"var"));
        forNode.getAssExpr().accept(this);
        forNode.getCondexpr().accept(this);
        forNode.getStatements().accept(this);
        this.tables.pop();
        this.actualTable=this.tables.peek();
        return null;
    }

    public Object visit (VarDecNode varDec) {
            if (varDec.getVarInitNode() != null) {
                try {
                    if (this.actualTable.probe(varDec.getId())) {
                        throw new IllegalStateException();
                    } else {
                        SemanticSym sm;
                        if(varDec.getVarInitNode().getExpr() instanceof ArrayConst){
                             sm = new SemanticSym(varDec.getDecType(),"array");
                        }
                        else
                            sm = new SemanticSym(varDec.getDecType(),"var");
                        varDec.getDecType().accept(this);
                        varDec.getVarInitNode().accept(this);
                        this.actualTable.addId(varDec.getId(), sm);
                    }
                } catch (IllegalStateException e) {
                    System.err.println("Error! Variable : " + varDec.getId() + " already declared in the same scope R: " + varDec.getLeft() + " C: " + varDec.getRight());
                }
            } else {
                try {
                    if (this.actualTable.probe(varDec.getId())) {
                        throw new IllegalStateException();
                    } else {
                        SemanticSym sm = new SemanticSym(varDec.getDecType(),"var");
                        varDec.getDecType().accept(this);
                        this.actualTable.addId(varDec.getId(), sm);
                    }
                } catch(IllegalStateException e){ System.err.println("Error! Variable : " + varDec.getId() + " already declared in the same scope R: " + varDec.getLeft() + " C: " + varDec.getRight());}
            }
        return null;
    }

    public Object visit (VarInitNode varInit) {
        varInit.getExpr().accept(this);
        return null;
    }


    public Object visit (ProgramNode program) {
        this.tables.push(new SymbolTable("ProgramTable"));
        this.actualTable = this.tables.peek();
        program.attachScope(actualTable);
        program.getScope().setParentTable(null);
        this.globalSymbolTable=this.tables.peek();
        program.setGlobSymTable(this.globalSymbolTable);
        program.getGlobal().accept(this);
        program.getFunctions().accept(this);
        try {
            if (!this.globalSymbolTable.probe("main"))
                throw new IllegalStateException();
        }
        catch (IllegalStateException e) {System.err.println("Error! main() undefined!");}
        program.setGlobSymTable(this.globalSymbolTable);
        this.tables.pop();
        return program;
    }

    public Object visit (Types types){
        return null;
    }

    public Object visit (Vars vars){
        for ( IdExpr x : vars.getIds())
        {
            x.accept(this);
        }
        return null;
    }

    public Object visit (WhileNode whileNode){
        whileNode.getExpression().accept(this);
        whileNode.getStatements().accept(this);
        return null;
    }

    public Object visit (VarDecls varDecls){
        for (VarDecNode x : varDecls.getVarNodes())
        {
            x.accept(this);
        }
        return null;
    }

    public Object visit (LocalNode localNode){
        SymbolTable symbolTable= new SymbolTable("LOCAL");
        symbolTable.setParentTable(this.tables.peek());
        this.tables.push(symbolTable);
        this.actualTable=this.tables.peek();
        localNode.attachScope(actualTable);
        localNode.getVarDecs().accept(this);
        localNode.getStatements().accept(this);
        this.tables.pop();
        this.actualTable=this.tables.peek();
        return null;
    }

    public Object visit (ArrayAssign arrayAssign){
        arrayAssign.getExpr1().accept(this);
        arrayAssign.getExpr2().accept(this);
        arrayAssign.getExpr3().accept(this);
        return null;
    }


    public Object visit (ReadNode readNode){
        readNode.getVars().accept(this);
        return null;
    }

    public Object visit (WriteNode writeNode){
        writeNode.getExprs().accept(this);
        return null;
    }

    public Object visit (ReturnNode returnNode){
        returnNode.getExpression().accept(this);
        return null;
    }

    public Object visit (FunctionsNode functionsNode) {
        for (DefFun x : functionsNode.getFunctions())
        {
            String funName=x.getId();
            try {
                if (this.globalSymbolTable.probe(funName) && this.globalSymbolTable.lookUp(funName).getValue().equals("function")) {
                    throw new IllegalStateException();
                }
                else {
                    this.globalSymbolTable.addId(funName, new SemanticSym(x.getType(), "function"));
                    SymbolTable.FunDefPars.put(funName,x.getParams());
                }
            }
            catch (IllegalStateException e){
                System.err.println("Function :" + funName + " Already defined!");
            }
        }

        for (DefFun x : functionsNode.getFunctions()) {
            String funName=x.getId();
            SymbolTable funSymTable = new SymbolTable(funName, this.tables.peek());
            this.tables.push(funSymTable);
            this.actualTable = this.tables.peek();
            x.attachScope(actualTable);
            x.accept(this);
            this.tables.pop();
            this.actualTable = this.tables.peek();
        }
        return null;

    }
}