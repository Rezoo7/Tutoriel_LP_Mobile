package com.example.tutoriel_lp;

/**
 * Created by lanoix-a on 06/11/2017.
 */

public class ChiffreCesar {

    static char[] ALPHABET = {' ',
                              'A','B','C','D','E',
                              'F','G','H','I','J',
                              'K','L','M','N','O',
                              'P','Q','R','S','T',
                              'U','V','W','X','Y','Z'};

    public static String chiffrement(String chaine, int decalage) {

        char[] chInitial = chaine.toUpperCase().toCharArray();
        char[] chResultat = new char[chInitial.length];

        for (int i = 0; i < chInitial.length; i++) {
            int posInitiale = position(chInitial[i]);
            if (posInitiale == -1) {
                chResultat[i] = chInitial[i];
            }
            else {
                int npos = (posInitiale + decalage) % 26;
                if (npos == 0) {
                    npos = 26;
                }
                chResultat[i] = ALPHABET[npos];
            }
        }
        return new String(chResultat);
    }

    private static int position(char c)  {
        for(int i = 1; i < ALPHABET.length; i++)   {
            if(ALPHABET[i] == c) {
                return i;
            }
        }
        return -1;
    }
}
