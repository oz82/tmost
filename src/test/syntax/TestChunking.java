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
package test.syntax;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

import morphology.Analyzer;
import syntax.MorphoAnalysisMap;
import syntax.SyntaxAnalysis;
import syntax.function.Function;
import syntax.setting.Settings;

/**
 *
 * @author Ozkan Aslan euzkan@gmail.com
 */
public class TestChunking {

    public static void main(String[] args) throws InterruptedException {

        Analyzer analyzer = new Analyzer(1);
        Scanner scan = new Scanner(System.in, "UTF-8");
        String input;
        do {
            System.out.print("sentence: ");
            input = scan.nextLine();
            System.out.println("");
            ArrayList<String> tokenList = new ArrayList<>(Arrays.asList(input.split(" ")));
            MorphoAnalysisMap mam = new MorphoAnalysisMap(analyzer, tokenList);

            long start = System.currentTimeMillis();
            ArrayList<MorphoAnalysisMap> mamList = new ArrayList<>();
            mamList.add(mam);
            SyntaxAnalysis sa = new SyntaxAnalysis(analyzer, mamList, 1, 200000);
            long elapsedTimeMillis = System.currentTimeMillis() - start;

            TreeSet<String> set = new TreeSet<>();
            for (Function f : sa.getTreeList()) {
                Settings.printCode = 4;
                //System.out.println(f.toString());
                set.add(f.toString());
                Settings.printCode = 0;
            }

            for (String s : set) {
                System.out.println(s);
            }

            System.out.println("");
            System.out.println("number of analysis path:\t" + mam.getCartesianProduct());
            System.out.println("number of syntax tree..:\t" + sa.getNumTree());
            System.out.println("elapsed time...........:\t" + elapsedTimeMillis);
            System.out.println("");
        } while (!input.equals(""));

    }
}
