package Semantic;

import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;

import java.util.ArrayList;

public class StringTable {
    private ArrayList<ComplexSymbol> symTable;

    public StringTable(){this.symTable=new ArrayList<ComplexSymbol>();}

    public boolean addSymbol (ComplexSymbol complex){
        if (symTable.stream().anyMatch( complexSymbol -> complexSymbol.equals(complex))) {
            return false;
        }
        else {
            this.symTable.add(complex);
            return true;
        }
    }

    public int getAddr(String lexema) {
        return this.symTable.indexOf(
                this.symTable.stream().filter(l -> l.value.equals(lexema)).findFirst().get());
    }


    public ComplexSymbol getSymbol(int addr) {
        return this.symTable.get(addr);
    }


    public String getLexema(int add) {
        return this.symTable.get(add).value.toString();
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < symTable.size(); i++) {
            sb.append(String.format("Address:%d | Value:%s\n", i, symTable.get(i).value));
        }
        return sb.toString();
    }

}
