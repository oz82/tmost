package test.syntax;

import morphology.Analyzer;
import morphology.MorphoAnalysis;
import syntax.MorphoAnalysisMap;
import syntax.SyntaxAnalysis;
import syntax.Unifier;
import syntax.function.Function;
import syntax.setting.Settings;
import syntax.structure.Clause;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Temp3 {
    public static void main(String[] args) {
        Analyzer analyzer = new Analyzer(1);
        String path = "C:\\Users\\oz\\Desktop\\_github\\jupyter-notebook-files\\aut\\_files\\syntax_tree_gold_METU_numTree[11,x]_v2.txt";
        try {
            FileInputStream fstream = new FileInputStream(path);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String strLine;

            int count = 0;
            boolean sentMode = false;
            String sentence = "", referenceSyntaxTree = "";
            while ((strLine = br.readLine()) != null) {
                if (!strLine.equals("") && !sentMode) {
                    sentMode = true;
                    sentence = strLine;
                    if (sentence.startsWith("*")) sentence = sentence.substring(1);
                    continue;
                }
                if (sentMode) {
                    referenceSyntaxTree = strLine;
                    ArrayList<String> tokenList = new ArrayList<>(Arrays.asList(sentence.split(" ")));
                    MorphoAnalysisMap mam = new MorphoAnalysisMap(analyzer, tokenList);

                    ArrayList<MorphoAnalysisMap> mamList = new ArrayList<>();
                    mamList.add(mam);
                    //SyntaxAnalysis sas = new SyntaxAnalysis(analyzer, mamList, 12, 200000);
                    SyntaxAnalysis sa = Unifier.unify(analyzer, sentence, 12, 200000, 10000);

                    Settings.printCode = 0;
                    String chunking = "";
                    for (Function f : sa.getTreeList()) {
                        String syntaxTree = f.toString().replaceAll(",", "");
                        if (referenceSyntaxTree.equals(syntaxTree)) {
                            System.out.println();
                            Clause c = (Clause) f;
                            MorphoAnalysis[] referencePath = c.getPath();
                            MorphoAnalysis[][] maArr = mam.getMap();

                            for (int i = 0; i < referencePath.length; i++) {
                                MorphoAnalysis ma = referencePath[i];
                                MorphoAnalysis[] analysis = analyzer.getAnalysis(sa.getMorphoAnalysisMapList().get(0).getToken()[i]);
                                if (analysis.length > 1) {
                                    System.out.println("Bağlam: \"" + sentence + "\"");
                                    System.out.println("Yukarıda verilen cümle bağlamında \"" + sa.getMorphoAnalysisMapList().get(0).getToken()[i] + "\" kelimesi için şu olası morfolojik çözümlemelerden hangisinin geçerli olduğunu bul:");
                                    for (int j = 0; j < analysis.length; j++) {
                                        System.out.println(analysis[j].toStringShort());
                                    }
                                    System.out.println(ma.toStringShort());
                                    count++;
                                    System.out.println();
                                }
                            }
                            //System.out.println();
                        }
                    }
                    Settings.printCode = 0;
                    sentMode = false;
                }
            }
            System.out.println(count);
            in.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}