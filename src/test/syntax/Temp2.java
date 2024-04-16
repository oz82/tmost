package test.syntax;

import morphology.Analyzer;
import syntax.MorphoAnalysisMap;
import syntax.SyntaxAnalysis;
import syntax.Unifier;
import syntax.function.Function;
import syntax.setting.Settings;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;

public class Temp2 {
    public static void main(String[] args) {
        Analyzer analyzer = new Analyzer(1);
        String path = "/home/oz/Desktop/github/python/jupyter-notebook-files/aut/_files/syntax_tree_gold_METU_numTree[11,x].txt";
        try {
            FileInputStream fstream = new FileInputStream(path);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String strLine;

            boolean sentMode = false;
            String sentence = "", referenceSyntaxTree = "";
            while ((strLine = br.readLine()) != null) {
                if (!strLine.equals("") && !sentMode) {
                    sentMode = true;
                    sentence = strLine;
                    if (sentence.equals("Kızgın güneş kentin üstüne çökmüştü")) {
                        sentence = sentence;
                    }
                    continue;
                }
                if (sentMode) {
                    referenceSyntaxTree = strLine;
                    ArrayList<String> tokenList = new ArrayList<>(Arrays.asList(sentence.split(" ")));
                    MorphoAnalysisMap mam = new MorphoAnalysisMap(analyzer, tokenList);

                    ArrayList<MorphoAnalysisMap> mamList = new ArrayList<>();
                    mamList.add(mam);
                    SyntaxAnalysis sa = new SyntaxAnalysis(analyzer, mamList, 12, 200000);

                    Settings.printCode = 0;
                    String chunking = "";
                    for (Function f : sa.getTreeList()) {
                        if (referenceSyntaxTree.equals(f.toString())) {
                            Settings.printCode = 3;
                            chunking = f.toString();
                            Settings.printCode = 1;
                        }
                    }
                    Settings.printCode = 0;
                    if (chunking.isEmpty()) {
                        System.out.println(sentence + "\n");
                        for (Function f : sa.getTreeList()) {
                            System.out.println(f.toString());
                        }
                        System.out.println("\n\n");
                    }
                    sentMode = false;
                }
            }
            in.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}