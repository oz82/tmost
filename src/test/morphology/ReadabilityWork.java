package test.morphology;

import lang_specific.LowerCase;
import morphology.Analyzer;
import morphology.MorphoAnalysis;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class ReadabilityWork {

    private static ArrayList<String> sentence, result;
    private static int sortMode = 1;

    public static void main(String[] args) {
        Analyzer analyzer = new Analyzer(sortMode);
        readSentence("/home/oz/Documents/ts-sentences-50k.txt");
        result = new ArrayList<>();

        HashSet<String> tokenSet = new HashSet<>();
        for (String sent : sentence) {
            String[] token = sent.split(" ");
            for (String t : token) {
                tokenSet.add(LowerCase.getLowercase(t));
            }
        }

        for (String token : tokenSet) {
            MorphoAnalysis[] analyses = null;
            try {
                analyses = analyzer.getAnalysis(token);
            } catch (Exception e) {
                //
            }
            if (analyses.length == 0) {
                result.add(token + "\t" + "<UNKNOWN>");
            }
            if (analyses != null && analyses.length > 0) {
                String temp = "";
                for (MorphoAnalysis ma : analyses) {
                    temp += ma.getMorphemesLexical() + "~" + ma.getMorphemesTag() + ",";
                }
                temp = temp.substring(0, temp.length() - 1);
                result.add(token + "\t" + temp);
            }

        }

        try {
            FileWriter fstream = new FileWriter("/home/oz/Documents/ts-sentences-50k-morpho-analysis.txt");
            BufferedWriter out = new BufferedWriter(fstream);

            for (String s : result) {
                out.write(s + "\n");
            }
            out.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void readSentence(String path) {
        sentence = new ArrayList();

        try {
            FileInputStream fstream = new FileInputStream(path);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String strLine;

            while ((strLine = br.readLine()) != null) {
                sentence.add(strLine);
            }
            in.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
}