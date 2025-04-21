package test.syntax;

import morphology.Analyzer;
import syntax.SyntaxAnalysis;
import syntax.Unifier;
import syntax.function.Function;
import syntax.setting.Settings;
import syntax.structure.Clause;

public class ChunkingCheck {
    public static void main(String[] args) {
        SyntaxAnalysis sa = null;
        Analyzer analyzer = new Analyzer(1);
        String sentence = "Evlilikten sonra edinilen malların eşit paylaşımını öngörüyor";
        String tree = "[Clause [Subject null] [Complement [CasedNoun [NounPhrase [GenitiveNoun [NounPhrase [Participle [Clause [Subject null] [Complement null] [Adjunct [SuperAdverb [Complement [CasedNoun [BaseNoun evliliK] [Suffix NDAn~ABL]]] [BaseAdverb [BaseAdverb sonra]]]] [Verb [BaseVerb edin]] POS NULL NULL NULL] [Suffix YAn~Adj+PAR+CONTI]] [PluralNoun [BaseNoun mal] [Suffix lAr~PLU]]] [Suffix NHn~GEN]] [PossessiveNoun [NounPhrase [SuperAdjective [Complement null] [BaseAdjective [BaseAdjective eşit]]] [BaseNoun paylaşım]] [Suffix SH~POS3S]]] [Suffix NH~ACC]]] [Adjunct null] [Verb [BaseVerb öngör]] POS NULL CONTI PER3S]";
        try {
            sa = Unifier.unify(analyzer, sentence, 1, 100000, 1024);
        } catch (Exception e) {

        }

        for (Function f : sa.getTreeList()) {
            Clause clause = (Clause) f;
            if (clause.toString().equals(tree)) {
                Settings.printCode = 4;
                System.out.println(clause.toString());
            }
        }
    }
}