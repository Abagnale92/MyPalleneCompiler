import Semantic.StringTable;
import Syntax.ProgramNode;
import Visitor.CodeGeneratorVisitor;
import Visitor.SemanticVisitor;
import Visitor.SyntaxVisitor;
import Visitor.TypeVisitor;
import java_cup.runtime.ComplexSymbolFactory;

import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ParserTest {
    public final static StringTable stringTable= new StringTable();
    public static void main(String[] args) throws Exception {
        String filePath = args[0];
        ComplexSymbolFactory sf = new ComplexSymbolFactory();
        Parser parser  = new Parser((new Lexer(sf,new FileReader(filePath), stringTable)), sf);
        ProgramNode program;
        program = (ProgramNode) parser.parse().value;
        SyntaxVisitor visitor = new SyntaxVisitor();
        visitor.appendRoot(visitor.visit(program));
        visitor.toXml( "Paoli_Abagnale_4"+ ".xml");
        SemanticVisitor semanticVisitor = new SemanticVisitor();
        ProgramNode program2;
        program2 = (ProgramNode) semanticVisitor.visit(program);
       //try {
            TypeVisitor typeVisitor = new TypeVisitor(program2.getScope());
            typeVisitor.visit(program2);
        //} catch (Exception n){System.err.println("Compilation error(s) during Type Checking");}
        CodeGeneratorVisitor codeGeneratorVisitor = new CodeGeneratorVisitor(program2.getScope());
        String generatedCode= codeGeneratorVisitor.visit(program2);
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath+".c"));
        writer.write(generatedCode);
        writer.close();

        //System.out.println(generatedCode);
    }
}