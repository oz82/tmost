package test.morphology;

import morphology.Analyzer;
import morphology.MorphoAnalysis;

import java.io.*;
import java.util.ArrayList;

/*
 * Copyright (C) 2021 Ozkan Aslan euzkan@gmail.com.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */

import morphology.Analyzer;
import morphology.MorphoAnalysis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Ozkan Aslan euzkan@gmail.com
 */

public class TestAnalysisEachWordInSentence {
    private static ArrayList<String> sentence;
    private static int sortMode = 1;

    public static void main(String[] args) {
        Analyzer analyzer = new Analyzer(sortMode);
        readSentence("/home/oz/Documents/test/sentences.txt");

        ArrayList<String> problematicToken = new ArrayList();
        ArrayList<String> analyses = new ArrayList();
        int countToken = 0, countAnalysedToken = 0, countSentence = 0;
        long start = System.currentTimeMillis();
        for (String sent : sentence) {
            analyses.add("\nCümle: " + sent);
            String[] token = sent.split(" ");
            int nt = 0;
            for (String t : token) {
                nt++;
                MorphoAnalysis[] analysis = null;
                try {
                    analysis = analyzer.getAnalysis(t);
                } catch (Exception e) {
                    //
                }
                analyses.add(nt + ") " + t + ":");
                if (analysis.length == 0) {
                    analyses.add("<UNKNOWN>");
                    problematicToken.add(t);
                }
                if (analysis != null && analysis.length > 0) {
                    for (MorphoAnalysis a : analysis) {
                        analyses.add(a.getMorphemesLexical() + "-" + a.getMorphemesTag());
                    }

                    countAnalysedToken++;
                }
                countToken++;
            }
            System.out.println(++countSentence);
        }
        long elapsedTimeMillis = System.currentTimeMillis() - start;

        try {
            FileWriter fstream = new FileWriter("/home/oz/Documents/test/morphological_analyses.txt");
            BufferedWriter out = new BufferedWriter(fstream);

            for (String s : analyses) {
                out.write(s + "\n");
            }
            out.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("#token:\t\t" + countToken);
        System.out.println("#analysed token:\t\t" + countAnalysedToken);
        System.out.println("#sentence:\t\t" + countSentence);
        System.out.println("elapsed time (ms):\t\t" + elapsedTimeMillis);
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
