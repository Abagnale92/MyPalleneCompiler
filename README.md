**Compilatore MyPallene --> C (ISO 98+)**

`Lo sviluppo del progetto è stato eseguito dividendo le responsabilità di funzionamento in quattro classi fondamentali:`

    -- SyntaxVisitor
            Il SyntaxVisitor si occupa di creare l'albero sintattico a partire da un codice sorgente MyPallene precedentemente validato dal parsing di JavaCUP
    --SemanticVisitor
            Il SemanticVisitor si occupa della fase di Scope Checking. Si assicura che le variabili siano dichiarate prima di essere utilizzate.
            Questo vale per tutti i tipo: int, float, bool, string e relativi array.
            L'interfaccia Scoped viene implementata dalle classi che definiscono un nuovo scope (Nel caso di MyPallane, i nodi FOR-LOCAL-GLOBAL-FUNZIONE)
            La classe SemanticSym rappresenta un simbolo semantico, presenta due campi:
            l'ID della variabile a cui è associato, ed il valore, che indica se è un array, una funzione, una stringa o una variabile semplice.
            La classe SymbolTable rappresenta una tabella dei simboli. Contiene i tipi validi del linguaggio, ed una HashMap statica, utilizzata per avere un riferimento alle funzioni con relativi parametri
    --TypeVisitor
            Il TypeVisitor si occupa di verificare la validità dei tipi nelle operazioni.
            Utilizza le tabelle dei simboli restituite dall'analisi semantica.
    -CodeGeneratorVisitor
            Il CodeGeneratorVisitor si occupa della traduzione del codice MyPallene in codice C.
            Inoltre, gli sono demandate la congruenza di array e stringhe con il loro utilizzo
            Fa utilizzo di alcune librerie fondamentali del c (stdio.h, string.h, math.h) inoltre, non esistendo il tipo booleano in C, effettua una typedef ad int (typedef int bool)
```
            
Modifiche Effettuate:
    La dichiarazione di tipi array, onde evitare ridondanza grammaticale ({}), viene effettuata a questo modo:
        array:int={}:int;
        La gestione delle stringhe fa uso delle funzioni (strcpy, strcmp)
        Gli array sono allocati staticamente utilizzando una dimensione fittizia pari a 50
        
            
            
```
