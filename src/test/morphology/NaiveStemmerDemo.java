package test.morphology;

import morphology.MorphoAnalysis;
import stemming.NaiveStemmer;

import java.util.Scanner;

public class NaiveStemmerDemo {
    public static void main(String[] args) {
        NaiveStemmer.init("G:\\My Drive\\_sync\\_code_archive\\java\\corlin\\_lang\\GiTuCo.filt2.uniq.shuf.stemF.format_F.frq");
        interaction();
    }

    public static void interaction() {
        Scanner scan = new Scanner(System.in, "UTF-8");
        String input;
        do {
            System.out.print("word: ");
            input = scan.nextLine();
            System.out.println("");
            Object[] result = NaiveStemmer.morphoDisam(input);
            MorphoAnalysis[] analyses = (MorphoAnalysis[]) result[0];
            int fi = (Integer) result[1];
            System.out.println(analyses[fi]);
        } while (!input.equals(""));
    }
}