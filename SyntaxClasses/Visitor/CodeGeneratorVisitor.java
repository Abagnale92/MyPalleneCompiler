package Visitor;
import Expressions.*;
import Semantic.SemanticSym;
import Semantic.SymbolTable;
import Statements.*;
import Syntax.*;

import java.util.Stack;

public class CodeGeneratorVisitor implements Visitor<String> {
    private StringBuilder code;
    private Stack<SymbolTable> tables;
    private SymbolTable actualScope;
    private SymbolTable globalSymbolTable;

    public CodeGeneratorVisitor(SymbolTable globalSymbolTable) {
        this.code = new StringBuilder();
        this.code.append("#include <stdio.h>\n#include <string.h>\n#include <math.h>\ntypedef int bool;\n#define false 0\n#define true 1\n\n");
        this.tables = new Stack<>();
        this.actualScope = null;
        this.globalSymbolTable = globalSymbolTable;
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

    public String visit (PlusExpr plus){
        StringBuilder sb = new StringBuilder();
        sb.append("("+ plus.getLeft().accept(this));
        sb.append(" + ");
        sb.append(plus.getRight().accept(this)+")");
        return sb.toString();
    }
    public String visit(MinusExpr minus){
        StringBuilder sb = new StringBuilder();
        sb.append("(" + minus.getLeft().accept(this));
        sb.append(" - ");
        sb.append(minus.getRight().accept(this) + ")");
        return sb.toString();
    }

    public String visit (DefFun function){
        this.tables.push(function.getScope());
        this.actualScope=this.tables.peek();
        StringBuilder sb = new StringBuilder();
        if (!function.getId().equals("main"))
            sb.append(function.getType().accept(this));
        else
            sb.append("int ");
        sb.append(" ");
        sb.append(function.getId()+" ");
        sb.append("(");
        if(function.getParams()!=null)
            sb.append(function.getParams().accept(this) + " ");
        sb.append(")\n");
        sb.append("{");
        sb.append(function.getStatements().accept(this));
        sb.append("\n}\n");
        this.tables.pop();
        this.actualScope=this.tables.peek();
        return sb.toString();

    }
    public String visit (GlobalNode global){
        StringBuilder sb = new StringBuilder();
        sb.append(global.getDeclarations().accept(this));
        return sb.toString();
    }
    public String visit (ParDecNode pardec){
        StringBuilder sb = new StringBuilder();
        sb.append(pardec.getType().accept(this));
        sb.append(" " + pardec.getId());
        return sb.toString();
    }
    public String visit (TypeNode types){
        StringBuilder sb = new StringBuilder();
        if (!types.getType().toLowerCase().equals("nil"))
            sb.append(types.getType().toLowerCase());
        else
            sb.append("void ");
        sb.append(" ");
        return sb.toString();
    }
    public String visit (Statements statements) {
        StringBuilder sb = new StringBuilder();
        for (StatNode x : statements.getStatements()) {
            if (!(x instanceof NopNode))
                sb.append("\n\t"+x.accept(this)+";");
        }
        return sb.toString();
    }

    public String visit (IfThenNode ifthen){
        StringBuilder sb = new StringBuilder();
        sb.append("if " + ifthen.getIfExpr().accept(this));
        sb.append("{");
        sb.append(ifthen.getThenStatements().accept(this));
        sb.append("\n}");
        return sb.toString();
    }

    public String visit (IfThenElseNode ifThenElse){
        StringBuilder sb = new StringBuilder();
        sb.append("if " + ifThenElse.getIfExpr().accept(this));
        sb.append("{");
        sb.append(ifThenElse.getThenStatements().accept(this));
        sb.append("\n}\n");
        sb.append("else{");
        sb.append(ifThenElse.getElseStatements().accept(this));
        sb.append("\n}");
        return sb.toString();}

    public String visit (Exprs exprs){
        StringBuilder sb = new StringBuilder();
        for (ExprNode x: exprs.getExpressionNodes()) {
            sb.append(x.accept(this));
            sb.append(", ");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    public String visit (ForNode forNode){  this.tables.push(forNode.getScope());
        this.actualScope=this.tables.peek();
        StringBuilder sb = new StringBuilder();

        sb.append("int "+forNode.getId()+"=0" +";\nfor(" + forNode.getAssExpr().accept(this)+";");
        sb.append(forNode.getCondexpr().accept(this)+ ";");
        sb.append(" " + forNode.getId() + "++){\n");
        sb.append(forNode.getStatements().accept(this));
        sb.append("\n}");
        this.tables.pop();
        this.actualScope=this.tables.peek();
        return sb.toString();
    }

    public String visit (VarDecNode varDec){
        StringBuilder sb = new StringBuilder();
        sb.append(varDec.getDecType().accept(this));
        sb.append(varDec.getId());
        if (varDec.getVarInitNode() == null) {

                return sb.toString();
        }
        else{
            sb.append(" = ");
            sb.append(varDec.getVarInitNode().getExpr().accept(this));
            return sb.toString();
        }
    }
    public String visit (VarInitNode varInit) {
        StringBuilder sb = new StringBuilder();
        if (varInit.getExpr().getReturnType().equals(SymbolTable.Type.STRING))
        {
            sb.append("{\"");
            sb.append(varInit.getExpr().accept(this));
            sb.append("\"}");
            return sb.toString();
        }
        sb.append(varInit.getExpr().accept(this));
        return sb.toString();
    }

    public String visit (ProgramNode program) {
        this.globalSymbolTable=program.getGlobSymTable();
        this.tables.push(program.getScope());
        this.actualScope = this.tables.peek();
        this.code.append(program.getGlobal().accept(this));
        this.code.append("\n\n");
        this.code.append(program.getFunctions().accept(this));
        this.tables.pop();
        return this.code.toString();
    }

    public String visit (IntConstExpr intConstExpr){
        StringBuilder sb = new StringBuilder();
        sb.append(intConstExpr.getValue());
        return sb.toString();

    }
    public String visit (StringConstExpr stringConstExpr){
        return stringConstExpr.getValue();
    }
    public String visit (Types types){
        StringBuilder sb = new StringBuilder();
        for (TypeNode temp :  types.getTypes()){
            sb.append(temp.accept(this));
            sb.append(", ");
        }
        return sb.toString();
    }
    public String visit (ArrayAccess arrayAccess){
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(arrayAccess.getExpr1().accept(this) + "[");
        sb.append(arrayAccess.getExpr2().accept(this)+ "]");
        sb.append(")");
        return sb.toString();
        }

    public String visit (FunctionCallExpr functionCallExpr){
        StringBuilder sb = new StringBuilder();
        sb.append(functionCallExpr.getId() + "(");
        for (ExprNode x : functionCallExpr.getExprs().getExpressionNodes())
        {
            sb.append(x.accept(this));
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(")");
        return sb.toString();
    }


    public String visit (FloatConstExpr floatConstExpr){
        StringBuilder sb = new StringBuilder();
        sb.append(floatConstExpr.getValue());
        return sb.toString();
    }
    public String visit (Vars vars){return null;}


    public String visit (WhileNode whileNode){
        StringBuilder sb = new StringBuilder();
        sb.append("while ");
        sb.append(whileNode.getExpression().accept(this));
        sb.append("{");
        sb.append("\t"+whileNode.getStatements().accept(this));
        sb.append("\n\t}");
        return sb.toString();
    }
    public String visit (LocalNode localNode){
        this.tables.push(localNode.getScope());
        this.actualScope=this.tables.peek();
        StringBuilder sb= new StringBuilder();
        sb.append("{\n");
        sb.append("\t"+localNode.getVarDecs().accept(this));
        sb.append("\t"+localNode.getStatements().accept(this));
        sb.append("\n\t}");
        this.tables.pop();
        this.actualScope=this.tables.peek();
        return sb.toString();
    }

    public String visit (ArrayAssign arrayAssign){
        StringBuilder sb = new StringBuilder();
        sb.append(arrayAssign.getExpr1().accept(this) +"[");
        sb.append(arrayAssign.getExpr2().accept(this) + "]=");
        sb.append(arrayAssign.getExpr3().accept(this));
        return sb.toString();
    }
    public String visit (ReadNode readNode){
        StringBuilder sb = new StringBuilder();
        for (IdExpr x : readNode.getVars().getIds())
        {
            sb.append("scanf(");
            sb.append("\"");
            switch(x.getReturnType())
            {
                case BOOL: sb.append("%d\", &"); sb.append(x.accept(this) + ")"); break;
                case INT: sb.append("%d\", &"); sb.append(x.accept(this)+ ")"); break;
                case FLOAT: sb.append("%f\", &"); sb.append(x.accept(this)+ ")"); break;
                case STRING: sb.append("%s\", "+x.accept(this)+")\n"); break;
            }
            sb.append(";\n");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }



    public String visit (WriteNode writeNode){
        StringBuilder sb = new StringBuilder();
        for (ExprNode x : writeNode.getExprs().getExpressionNodes())
        {
            sb.append("printf(");
            sb.append("\"");
            if (x instanceof IdExpr){
                SemanticSym sm = getSym(((IdExpr) x).getId());
                if (sm!=null)
                {
                    switch (sm.getTypeNode().getType().toLowerCase()){
                        case "bool":
                            sb.append("%d\", ");
                            sb.append(x.accept(this) + ")");
                            break;
                        case "int":
                            sb.append("%d\", ");
                            sb.append(x.accept(this) + ")");
                            break;
                        case "float":
                            sb.append("%f\", ");
                            sb.append(x.accept(this) + ")");
                            break;
                        case "string":
                            sb.append("%s\", ");
                            sb.append(x.accept(this)+")");
                            break;
                    }
                }
            }
            else{
                switch (x.getReturnType()) {
                    case BOOL:
                        sb.append("%d\", ");
                        sb.append(x.accept(this) + ")");
                        break;
                    case INT:
                        sb.append("%d\", ");
                        sb.append(x.accept(this) + ")");
                        break;
                    case FLOAT:
                        sb.append("%f\", ");
                        sb.append(x.accept(this) + ")");
                        break;
                    case STRING:
                        sb.append(x.accept(this));
                        sb.deleteCharAt(sb.length()-1);
                        sb.append("\")");
                        break;
                }
            }
            sb.append(";\n");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    public String visit (ReturnNode returnNode){
        return  "return " + returnNode.getExpression().accept(this);

    }

    public String visit (FunctionsNode functionsNode){
        StringBuilder sb = new StringBuilder();
        for (DefFun x : functionsNode.getFunctions()) { //inserisco prima i prototipi
            if (!(x.getId().toLowerCase().equals("main"))) {
                sb.append(x.getType().accept(this));
                sb.append(x.getId() + " ");
                sb.append("(");
                if (x.getParams() != null) {
                    for (ParDecNode pd : x.getParams().getParDecNodes()) {
                        sb.append(pd.getType().accept(this));
                        sb.append(" ,");
                    }
                    sb.deleteCharAt(sb.length() - 1);
                }

                sb.append(");\n");
            }

        }
        sb.append("\n");
        for (DefFun x : functionsNode.getFunctions()) {
            sb.append(x.accept(this));
            sb.append("\n");
        }
        return sb.toString();
    }

    public String visit(AndExpr AndExpr){
        StringBuilder sb = new StringBuilder();
        sb.append("(" + AndExpr.getLeft().accept(this) + " && ");
        sb.append(AndExpr.getRight().accept(this) + ")");
        return sb.toString();

    }
    public String visit(ArrayConst blparExpr){
        return  "";
    }

    public String visit(ParDecls parDecls){
        StringBuilder sb = new StringBuilder();
        for ( ParDecNode pd : parDecls.getParDecNodes())
        {
            sb.append(pd.accept(this));
            sb.append(", ");
        }
        sb.deleteCharAt(sb.length()-1); //elimina spazio
        sb.deleteCharAt(sb.length()-1); //elimina virgola
        return  sb.toString();
    }

    public String visit(VarDecls varDecls){
        StringBuilder sb = new StringBuilder();
        for (VarDecNode x : varDecls.getVarNodes()){

            if (x.getVarInitNode()!= null) {
                if (x.getVarInitNode().getExpr() instanceof ArrayConst) {
                    sb.append(x.getDecType().getType().toLowerCase() + " ");
                    sb.append(x.getId());
                    sb.append("[50]");
                } else {
                    if (x.getDecType().getType().toLowerCase().equals("string"))
                    {
                        sb.append("char ");
                        sb.append(x.getId());
                        sb.append("[50]=");
                        sb.append(x.getVarInitNode().accept(this));
                    }
                    else
                        sb.append(x.accept(this));
                }
            }
            else{
                if (x.getDecType().getType().toLowerCase().equals("string"))
                {
                    sb.append("char " + x.getId() + "[50]");
                }
                else
                    sb.append(x.accept(this));
            }
            sb.append(";\n");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    public String visit(UnaryExpr unaryExpr){

        String unaryOp = unaryExpr.getUnaryOp();
        if( unaryOp.equals("-")){
            return "(-" + unaryExpr.getExpression().accept(this)+")";
        }
        else if( unaryOp.equals("NOT")){
            return "(!("+unaryExpr.getExpression().accept(this) + "))";
        }
        else if( unaryOp.equals("#")){
            return "("+unaryExpr.getExpression().accept(this)+")";
        }
        return "errUnary";
    }

    public String visit(TrueExpr trueExpr){return "true";}

    public String visit(TimesExpr timesExpr){
        StringBuilder sb = new StringBuilder();
        sb.append("("+timesExpr.getLeft().accept(this) + " * ");
        sb.append(timesExpr.getRight().accept(this)+")");
        return sb.toString();}

    public String visit(OrExpr orExpr){
        StringBuilder sb = new StringBuilder();
        sb.append("("+orExpr.getLeft().accept(this) + " || ");
        sb.append(orExpr.getRight().accept(this) +")");
        return sb.toString();}

    public String visit(NilConst nilConst){
        return "";
    }

    public String visit(NeExpr neExpr){
        StringBuilder sb = new StringBuilder();
        sb.append("("+neExpr.getLeft().accept(this));
        sb.append(" != ");
        sb.append(neExpr.getRight().accept(this) +")");
        return sb.toString();

    }

    public String visit(LtExpr ltExpr){
        StringBuilder sb = new StringBuilder();
        if(ltExpr.getLeft().getReturnType().equals(SymbolTable.Type.STRING) && ltExpr.getRight().getReturnType().equals(SymbolTable.Type.STRING)){
            sb.append("(strcmp(" +ltExpr.getLeft().accept(this)+", ");
            sb.append(ltExpr.getRight().accept(this) +")<0)");
        }
        else{
            sb.append("(" +ltExpr.getLeft().accept(this));
            sb.append(" < ");
            sb.append(ltExpr.getRight().accept(this) +")");
        }
        return sb.toString();
    }

    public String visit(LeExpr leExpr){
        StringBuilder sb = new StringBuilder();
        if(leExpr.getLeft().getReturnType().equals(SymbolTable.Type.STRING) && leExpr.getRight().getReturnType().equals(SymbolTable.Type.STRING)){
            sb.append("(strcmp(" +leExpr.getLeft().accept(this)+", ");
            sb.append(leExpr.getRight().accept(this) +")<=0)");
        }
        else{
            sb.append("(" +leExpr.getLeft().accept(this));
            sb.append(" <= ");
            sb.append(leExpr.getRight().accept(this) +")");
        }
        return sb.toString();
    }

    public String visit(IdExpr idExpr){
        return idExpr.getId();
    }

    public String visit(GtExpr gtExpr){
        StringBuilder sb = new StringBuilder();
        if(gtExpr.getLeft().getReturnType().equals(SymbolTable.Type.STRING) && gtExpr.getRight().getReturnType().equals(SymbolTable.Type.STRING)){
            sb.append("(strcmp(" +gtExpr.getLeft().accept(this)+", ");
            sb.append(gtExpr.getRight().accept(this) +")>0)");
        }
        else{
            sb.append("(" +gtExpr.getLeft().accept(this));
            sb.append(" > ");
            sb.append(gtExpr.getRight().accept(this) +")");
        }
        return sb.toString();

    }

    public String visit(GeExpr geExpr){
        StringBuilder sb = new StringBuilder();
        if(geExpr.getLeft().getReturnType().equals(SymbolTable.Type.STRING) && geExpr.getRight().getReturnType().equals(SymbolTable.Type.STRING)){
            sb.append("(strcmp(" + geExpr.getLeft().accept(this)+", ");
            sb.append(geExpr.getRight().accept(this) +")>=0)");
        }
        else{
            sb.append("(" +geExpr.getLeft().accept(this));
            sb.append(" >= ");
            sb.append(geExpr.getRight().accept(this) +")");
        }

        return sb.toString();
    }

    public String visit(FalseExpr falseExpr){
        return "false";
    }

    public String visit(EqExpr eqExpr){
        StringBuilder sb = new StringBuilder();
        if(eqExpr.getLeft().getReturnType().equals(SymbolTable.Type.STRING) && eqExpr.getRight().getReturnType().equals(SymbolTable.Type.STRING)){
            sb.append("(strcmp(" +eqExpr.getLeft().accept(this)+", ");
            sb.append(eqExpr.getRight().accept(this) +")==0)");
        }
        else{
        sb.append("(" +eqExpr.getLeft().accept(this));
        sb.append(" == ");
        sb.append(eqExpr.getRight().accept(this) +")");
        }
        return sb.toString();
    }

    public String visit(DivExpr divExpr){
        StringBuilder sb = new StringBuilder();
        sb.append("("+divExpr.getLeft().accept(this));
        sb.append(" / ");
        sb.append(divExpr.getRight().accept(this)+")");
        return sb.toString();}

    public String visit(NopNode nopNode){return "";}

    public String visit(FunctionCallStatement functionCallStatement){
        StringBuilder sb = new StringBuilder();
        sb.append(functionCallStatement.getId() + "(");
        for (ExprNode x : functionCallStatement.getExprs().getExpressionNodes())
        {
            sb.append(x.accept(this));
            sb.append(", ");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        sb.append(")");
        return sb.toString();
    }

    public String visit(AssignExprToId assignExprToId){
        StringBuilder sb = new StringBuilder();
        SemanticSym sm = getSym(assignExprToId.getId());
        if (sm!=null && sm.getTypeNode().getType().toLowerCase().equals("string")){
            sb.append("strcpy(");
            sb.append(assignExprToId.getId() +", ");
            if (assignExprToId.getExpr() instanceof StringConstExpr) {
                sb.append("\"" + assignExprToId.getExpr().accept(this) + "\"");
            }
            else
                sb.append(assignExprToId.getExpr().accept(this));
            sb.append(")");
        }
        else{
        sb.append(assignExprToId.getId());
        sb.append(" = ");
        sb.append(assignExprToId.getExpr().accept(this));
        }
        return sb.toString();
    }


}