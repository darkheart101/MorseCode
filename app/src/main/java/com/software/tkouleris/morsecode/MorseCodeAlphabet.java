package com.software.tkouleris.morsecode;

import java.util.HashMap;
import java.util.Map;

public class MorseCodeAlphabet {
    private Map<String, String> MorseCodeAlphabetMap = new HashMap<String, String>();

    MorseCodeAlphabet(){
        // Initialize Alphabet
        MorseCodeAlphabetMap.put("A",".-");
        MorseCodeAlphabetMap.put("B","-...");
        MorseCodeAlphabetMap.put("C","-.-.");
        MorseCodeAlphabetMap.put("D","-..");
        MorseCodeAlphabetMap.put("E",".");
        MorseCodeAlphabetMap.put("F","..-.");
        MorseCodeAlphabetMap.put("G","--.");
        MorseCodeAlphabetMap.put("H","....");
        MorseCodeAlphabetMap.put("I","..");
        MorseCodeAlphabetMap.put("J",".---");
        MorseCodeAlphabetMap.put("K","-.-");
        MorseCodeAlphabetMap.put("L",".-..");
        MorseCodeAlphabetMap.put("M","--");
        MorseCodeAlphabetMap.put("N","-.");
        MorseCodeAlphabetMap.put("O","---");
        MorseCodeAlphabetMap.put("P",".--.");
        MorseCodeAlphabetMap.put("Q","--.-");
        MorseCodeAlphabetMap.put("R",".-.");
        MorseCodeAlphabetMap.put("S","...");
        MorseCodeAlphabetMap.put("T","-");
        MorseCodeAlphabetMap.put("U","..-");
        MorseCodeAlphabetMap.put("V","...-");
        MorseCodeAlphabetMap.put("W",".--");
        MorseCodeAlphabetMap.put("X","-..-");
        MorseCodeAlphabetMap.put("Y","-.--");
        MorseCodeAlphabetMap.put("Z","--..");
    }

    public String getMorseCoeLetter(String plainLetter){
        return MorseCodeAlphabetMap.get(plainLetter);
    }
}
