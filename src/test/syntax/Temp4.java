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

public class Temp4 {
    public static void main(String[] args) {
        String keyword = "oyuncu";
        Analyzer analyzer = new Analyzer(1);
        String path = "/home/oz/Desktop/TNT/clean/Turkish_News_TimeLine-sent-cleaned_20_64_5000_numTree[1].txt";
        try {
            FileInputStream fstream = new FileInputStream(path);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String sentence;

            while ((sentence = br.readLine()) != null) {
                String[] tokens = sentence.split(" ");
                if (Arrays.asList(tokens).contains(keyword)) {
                    SyntaxAnalysis sa = Unifier.unify(analyzer, sentence,1, 5000, 64);
                    Clause clause = (Clause) sa.getTreeList().get(0);
                    MorphoAnalysis[] maArr = clause.getPath();
                    for (int i = 0; i < maArr.length; i++) {
                        if (maArr[i].getStem().getSurface().equals(keyword)) {
                            System.out.println(maArr[i].getPos().getMajor() + "\t" + sentence);
                            break;
                        }
                    }
                }
            }
            in.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}