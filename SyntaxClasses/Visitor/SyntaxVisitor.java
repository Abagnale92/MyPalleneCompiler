package Visitor;

import Expressions.*;
import Statements.*;
import Syntax.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Collections;

public class SyntaxVisitor implements Visitor<Element> {
    private Document xmlDocument;
    public SyntaxVisitor() {
        super();
        this.createDocument();
    }
    private void createDocument() {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            this.xmlDocument = docBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
    public void toXml(String fileName) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(this.xmlDocument);
            StreamResult result = new StreamResult(new File("./"+fileName));
         //   System.out.println(fileName + ".xml saved in: " + path);
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
    public void appendRoot(Element el) {
        this.xmlDocument.appendChild(el);
    }

    public Element visit(AndExpr expr){
        Element el = this.xmlDocument.createElement("AndExpr");
        el.appendChild(expr.getLeft().accept(this));
        el.appendChild(expr.getRight().accept(this));
        return el;
    }

    public Element visit(ArrayConst expr){
        Element el = this.xmlDocument.createElement("ArrayConst");
        el.appendChild(expr.getTypeNode().accept(this));
        return el;
    }


    public Element visit(ParDecls parDecls) {
        Element el = this.xmlDocument.createElement("ParamDecOp");
        Collections.reverse(parDecls.getParDecNodes());
        for (ParDecNode x : parDecls.getParDecNodes()){
            el.appendChild(x.accept(this));
        }
        return el;
    }

    public Element visit (TypeNode types){
        Element el = this.xmlDocument.createElement("TypeNode");
        el.setAttribute("Type", types.getType());
        if (types.getTypesProduction()!=null) {
            el.appendChild(types.getTypesProduction().accept(this));
        }
        return el;
    }

    public Element visit(DivExpr expr) {
        Element el = this.xmlDocument.createElement("DivExpr");
        el.appendChild(expr.getLeft().accept(this));
        el.appendChild(expr.getRight().accept(this));
        return el;
    }


    public Element visit(NopNode nopNode) {
        Element el = this.xmlDocument.createElement("NOP");
        return el;

    }

    public Element visit(FunctionCallStatement functionCallStatement) {
        Element el = this.xmlDocument.createElement("FunctionCallStatement");
        el.setAttribute("FunctionCallStatement", functionCallStatement.getId());
        el.appendChild(functionCallStatement.getExprs().accept(this));
        return el;
    }


    public Element visit(AssignExprToId assignExprToId) {
        Element el = this.xmlDocument.createElement("AssignExprToId");
        el.appendChild(assignExprToId.getExpr().accept(this));
        el.setAttribute("ID_ASSIGN", assignExprToId.getId() );
        return el;
    }

    public Element visit(ArrayAccess expr){
        Element el = this.xmlDocument.createElement("ArrayAccess");
        el.appendChild(expr.getExpr1().accept(this));
        el.appendChild(expr.getExpr2().accept(this));
        return el;
    }

    public Element visit(EqExpr expr){
        Element el = this.xmlDocument.createElement("EqExpr");
        el.appendChild(expr.getLeft().accept(this));
        el.appendChild(expr.getRight().accept(this));
        return el;
    }
    public Element visit(Exprs expr){
        Element el = this.xmlDocument.createElement("Exprs");
        Collections.reverse(expr.getExpressionNodes());
        for (ExprNode x: expr.getExpressionNodes()) {
            el.appendChild(x.accept(this));
        }
        return el;

    }
    public Element visit(FalseExpr expr){
        Element el = this.xmlDocument.createElement("FalseExpr");
        return el;
    }

    public Element visit(FloatConstExpr expr){
        Element el = this.xmlDocument.createElement("FLOAT_CONST");
        el.setAttribute("FLOAT_CONST", String.valueOf(expr.getValue()));
        return el;
    }
    public Element visit(GeExpr expr) {
        Element el = this.xmlDocument.createElement("GeExpr");
        el.appendChild(expr.getLeft().accept(this));
        el.appendChild(expr.getRight().accept(this));
        return el;
    }
    public Element visit(GtExpr expr){
        Element el = this.xmlDocument.createElement("GtExpr");
        el.appendChild(expr.getLeft().accept(this));
        el.appendChild(expr.getRight().accept(this));
        return el;
    }
    public Element visit(IdExpr expr){
        Element el = this.xmlDocument.createElement("ID");
        el.setAttribute("ID", expr.getId());
        return el;
    }
    public Element visit(FunctionCallExpr expr){
        Element el = this.xmlDocument.createElement("FunctionCallExpr");
        el.setAttribute("FunctionName", expr.getId());
        Collections.reverse(expr.getExprs().getExpressionNodes());
        for (ExprNode x: expr.getExprs().getExpressionNodes()) {
            el.appendChild(x.accept(this));
        }
        return el;
    }
    public Element visit(IntConstExpr expr){
        Element el = this.xmlDocument.createElement("INT_CONST");
        el.setAttribute("INT_CONST", String.valueOf(expr.getValue()));
        return el;
    }

    public Element visit(LeExpr expr){
        Element el = this.xmlDocument.createElement("LeExpr");
        el.appendChild(expr.getLeft().accept(this));
        el.appendChild(expr.getRight().accept(this));
        return el;
    }
    public Element visit(LtExpr expr){
        Element el = this.xmlDocument.createElement("LtExpr");
        el.appendChild(expr.getLeft().accept(this));
        el.appendChild(expr.getRight().accept(this));
        return el;
    }
    public Element visit(MinusExpr expr){
        Element el = this.xmlDocument.createElement("MinusExpr");
        el.appendChild(expr.getLeft().accept(this));
        el.appendChild(expr.getRight().accept(this));
        return el;
    }
    public Element visit(NeExpr expr){
        Element el = this.xmlDocument.createElement("NeExpr");
        el.appendChild(expr.getLeft().accept(this));
        el.appendChild(expr.getRight().accept(this));
        return el;
    }
    public Element visit(NilConst expr){
        Element el = this.xmlDocument.createElement("NIL_CONST");
        return el;
    }

    public Element visit(OrExpr expr){
        Element el = this.xmlDocument.createElement("OrExpr");
        el.appendChild(expr.getLeft().accept(this));
        el.appendChild(expr.getRight().accept(this));
        return el;
    }
    public Element visit(PlusExpr expr) {
        Element el = this.xmlDocument.createElement("PlusExpr");
        el.appendChild(expr.getLeft().accept(this));
        el.appendChild(expr.getRight().accept(this));
        return el;
    }

    public Element visit(StringConstExpr expr){
        Element el = this.xmlDocument.createElement("STRING_CONST");
        el.setAttribute("String", expr.getValue());
        return el;
    }

    public Element visit(TimesExpr expr){
        Element el = this.xmlDocument.createElement("TimesExpr");
        el.appendChild(expr.getLeft().accept(this));
        el.appendChild(expr.getRight().accept(this));
        return el;
    }
    public Element visit(UnaryExpr expr) {
        Element el = this.xmlDocument.createElement("UnaryOP");
        el.setAttribute("OP", expr.getUnaryOp());
        el.appendChild(expr.getExpression().accept(this));
        return el;
    }

    @Override
    public Element visit(TrueExpr trueExpr) {
        Element el = this.xmlDocument.createElement("True");
        return el;

    }

    public Element visit (DefFun function){
        Element el = this.xmlDocument.createElement("FunDefinitionOp");
        el.setAttribute("FunctionName", function.getId());
        el.appendChild(function.getType().accept(this));
        if (function.getParams()!=null)
            el.appendChild(function.getParams().accept(this));
        el.appendChild(function.getStatements().accept(this));
        return el;
    }

    public Element visit (GlobalNode global){
        Element el = this.xmlDocument.createElement("GlobalNode");
        el.appendChild(global.getDeclarations().accept(this));
        return el;
    }

    public Element visit (ParDecNode pardec){
        Element el = this.xmlDocument.createElement("Param");
        el.setAttribute(pardec.getId(),pardec.getType().getType());
        return el;
    }

    public Element visit (Statements statements){
        Element el = this.xmlDocument.createElement("BodyOp");
        Collections.reverse(statements.getStatements());
        for (StatNode x: statements.getStatements()){
            el.appendChild(x.accept(this));
        }
        return el;
    }
    public Element visit (IfThenNode ifthen){
        Element el = this.xmlDocument.createElement("IfThenOp");
        el.appendChild(ifthen.getIfExpr().accept(this));
        el.appendChild(ifthen.getThenStatements().accept(this));
        return el;
    }

    public Element visit (IfThenElseNode ifThenElse){
        Element el = this.xmlDocument.createElement("IfThenElseOp");
        el.appendChild(ifThenElse.getIfExpr().accept(this));
        el.appendChild(ifThenElse.getThenStatements().accept(this));
        el.appendChild(ifThenElse.getElseStatements().accept(this));
        return el;
    }

    public Element visit (ForNode forNode){
        Element el = this.xmlDocument.createElement("For");
        el.appendChild(forNode.getAssExpr().accept(this));
        el.appendChild(forNode.getCondexpr().accept(this));
        el.appendChild(forNode.getStatements().accept(this));
        return el;
    }

    public Element visit (VarDecNode varDec) {
        Element el = this.xmlDocument.createElement("VarDeclarationNode");
            if (varDec.getVarInitNode()!=null) {
                el.setAttribute(varDec.getId(), varDec.getDecType().getType());
                el.appendChild(varDec.getVarInitNode().accept(this));
            }
            else{
                el.setAttribute(varDec.getId(), varDec.getDecType().getType());
            }
        return el;
    }

    public Element visit (VarInitNode varInit) {
        Element el = this.xmlDocument.createElement("VarInitNode");
        el.appendChild(varInit.getExpr().accept(this));
        return el;
    }

    public Element visit (ProgramNode program) {
        Element el = this.xmlDocument.createElement("ProgramOP");
        el.appendChild(program.getGlobal().accept(this));
        el.appendChild(program.getFunctions().accept(this));
        return el;
    }

    public Element visit (Types types){
        Element el = this.xmlDocument.createElement("Types");
        Collections.reverse(types.getTypes());
        for (TypeNode x : types.getTypes()) {
            el.appendChild(x.accept(this));
        }
        return el;
    }

    public Element visit (Vars vars){
        Element el = this.xmlDocument.createElement("Vars");
        for (IdExpr x : vars.getIds()) {
            el.appendChild(x.accept(this));
        }
        return el;
    }

    public Element visit (WhileNode whileNode){
        Element el = this.xmlDocument.createElement("WhileNode");
        el.appendChild(whileNode.getExpression().accept(this));
        el.appendChild(whileNode.getStatements().accept(this));
        return el;
    }
    public Element visit (VarDecls varDecls){
        Element el = this.xmlDocument.createElement("VarDeclarations");
        for (VarDecNode x : varDecls.getVarNodes())
        {
            el.appendChild(x.accept(this));
        }
        return el;
    }

    public Element visit (LocalNode localNode){
        Element el = this.xmlDocument.createElement("LocalNode");
        el.appendChild(localNode.getVarDecs().accept(this));
        el.appendChild(localNode.getStatements().accept(this));
        return el;

    }

    public Element visit (ArrayAssign arrayAssign){
        Element el = this.xmlDocument.createElement("ArrayAssign");
        el.appendChild(arrayAssign.getExpr1().accept(this));
        el.appendChild(arrayAssign.getExpr2().accept(this));
        el.appendChild(arrayAssign.getExpr3().accept(this));
        return el;
        }

    public Element visit (ReadNode readNode){
        Element el = this.xmlDocument.createElement("ReadNode");
        el.appendChild(readNode.getVars().accept(this));
        return el;
    }

    public Element visit (WriteNode writeNode){
        Element el = this.xmlDocument.createElement("WriteNode");
        el.appendChild(writeNode.getExprs().accept(this));
        return el;
    }

    public Element visit (ReturnNode returnNode){
        Element el = this.xmlDocument.createElement("ReturnNode");
        el.appendChild(returnNode.getExpression().accept(this));
        return el;
    }

    public Element visit (FunctionsNode functionsNode){
        Element el = this.xmlDocument.createElement("Functions");
        Collections.reverse(functionsNode.getFunctions());
        for (DefFun x : functionsNode.getFunctions())
        {
            el.appendChild(x.accept(this));
        }
        return el;
    }
}
