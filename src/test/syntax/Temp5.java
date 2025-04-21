package test.syntax;

import morphology.Analyzer;
import morphology.MorphoAnalysis;
import syntax.SyntaxAnalysis;
import syntax.Unifier;
import syntax.structure.Clause;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Temp5 {
    public static void main(String[] args) {
        Analyzer analyzer = new Analyzer(1);
        String path = "/home/oz/Desktop/github/python/jupyter-notebook/aut/_files/AUT_v2_sentences.txt";
        try {
            FileInputStream fstream = new FileInputStream(path);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String sentence;
            int n = 0;
            while ((sentence = br.readLine()) != null) {
                SyntaxAnalysis sa = Unifier.unify(analyzer, sentence, 1, 200000, 10000);
                if (sa == null) n = 0;
                else n = sa.getNumTree();
                System.out.println(n + "\t" + sentence);
            }
            in.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}