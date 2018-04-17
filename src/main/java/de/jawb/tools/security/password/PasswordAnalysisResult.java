package de.jawb.tools.security.password;

import java.util.HashMap;
import java.util.Map;

public class PasswordAnalysisResult {

    public enum PasswordProperty {
        countAllChars,
        countIgnoredChars,
        countUC,
        countLC,
        countNr,
        countSym,
        countRepeatChars,
        countConsecutiveLC,
        countConsecutiveUC,
        countConsecutiveNr;
    }

    public enum Bonus {
        bonusCharsCount,
        bonusUC,
        bonusLC,
        bonusNr,
        bonusSymbols,
        bonusSymbolsOrNrInTheMiddle,
        bonusMinRequirements,
    }

    public enum Penalty {
        penaltyLettersOnly,
        penaltyNumbersOnly,
        penaltyConsecutiveLC,
        penaltyConsecutiveUC,
        penaltyConsecutiveNr,
        penaltyRepeatChars;
    }

    private final Map<PasswordProperty, Integer> properties = new HashMap<>();
    private final Map<Bonus, Integer>            bonus      = new HashMap<>();
    private final Map<Penalty, Integer>          penalty    = new HashMap<>();

    void setProperty(PasswordProperty key, int value) {
        properties.put(key, value);
    }

    void setBonus(Bonus key, int value) {
        bonus.put(key, value);
    }

    void setPenalty(Penalty key, int value) {
        penalty.put(key, value);
    }

    public int property(PasswordProperty key){
        Integer val = properties.get(key);
        return val != null ? val : 0;
    }

    public int bonus(Bonus key){
        Integer val = bonus.get(key);
        return val != null ? val : 0;
    }

    public int penalty(Penalty key){
        Integer val = penalty.get(key);
        return val != null ? val : 0;
    }

    public int sumBonus(){
        int score = 0;
        for(Integer val : bonus.values()) {
            score += val;
        }
        return score;
    }

    public int sumPenalty(){
        int sum = 0;
        for(Integer val : penalty.values()) {
            sum += val;
        }
        return sum;
    }

    public int score(){
        int score = sumBonus() - sumPenalty();
        return score < 0 ? 0 : Math.min(score, 100);
    }

    @Override
    public String toString() {
        return "PasswordAnalysisResult [\n\tproperties=" + properties + "\n\tbonus=" + bonus + "\n\tpenalty=" + penalty + "\n\tscore=" + score() + "\n]";
    }
}
