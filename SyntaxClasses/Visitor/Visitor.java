package Visitor;

import Expressions.*;
import Statements.*;
import Syntax.*;

public interface Visitor <T> {

     T visit (PlusExpr plus);
     T visit(MinusExpr minus);
     T visit (DefFun function);
     T visit (GlobalNode global);
     T visit (ParDecNode pardec);
     T visit (TypeNode types);
     T visit (Statements statements);
     T visit (IfThenNode ifthen);
     T visit (IfThenElseNode ifThenElse);
     T visit (Exprs exprs);
     T visit (ForNode forNode);
     T visit (VarDecNode varDec);
     T visit (VarInitNode varInit) ;
     T visit (ProgramNode program) ;
     T visit (IntConstExpr intConstExpr);
     T visit (StringConstExpr StringConstExpr);
     T visit (Types types);
     T visit (ArrayAccess arrayAccess);
     T visit (FunctionCallExpr functionCallExpr);
     T visit (FloatConstExpr floatConstExpr);
     T visit (Vars vars);
     T visit (WhileNode whileNode);
     T visit (LocalNode localNode);
     T visit (ArrayAssign arrayAssign);
     T visit (ReadNode readNode);
     T visit (WriteNode writeNode);
     T visit (ReturnNode returnNode);
     T visit (FunctionsNode functionsNode);
    T visit(AndExpr AndExpr);
    T visit(ArrayConst blparExpr);

    T visit(ParDecls parDecls);

    T visit(VarDecls varDecls);

    T visit(UnaryExpr unaryExpr);

    T visit(TrueExpr trueExpr);

    T visit(TimesExpr timesExpr);

    T visit(OrExpr orExpr);

    T visit(NilConst nilConst);

    T visit(NeExpr neExpr);

    T visit(LtExpr ltExpr);

    T visit(LeExpr leExpr);

    T visit(IdExpr idExpr);

    T visit(GtExpr gtExpr);

    T visit(GeExpr geExpr);

    T visit(FalseExpr falseExpr);

    T visit(EqExpr eqExpr);

    T visit(DivExpr divExpr);

    T visit(NopNode nopNode);

    T visit(FunctionCallStatement functionCallStatement);

    T visit(AssignExprToId assignExprToId);


}