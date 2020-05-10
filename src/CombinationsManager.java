import java.util.Arrays;

public class CombinationsManager {

    private char[] symbolsSet; //Set of symbols for possible password
    private char[] currentCombination; //Current combination

    public CombinationsManager(char[] characterSet, int guessLength) {
        Arrays.sort(characterSet);
        symbolsSet = characterSet;
        currentCombination = new char[guessLength];
        Arrays.fill(currentCombination, symbolsSet[0]);
    }

    //generates combinations from symbols set
    public void nextCombination() {
        int index = currentCombination.length - 1;
        while (index >= 0) {
            if (currentCombination[index] == symbolsSet[symbolsSet.length - 1]) {
                if (index == 0) {
                    int newLength = currentCombination.length + 1;
                    currentCombination = new char[newLength];
                    Arrays.fill(currentCombination, symbolsSet[0]);
                    break;
                } else {
                    currentCombination[index] = symbolsSet[0];
                    index--;
                }
            } else {
                currentCombination[index] = symbolsSet[Arrays.binarySearch(symbolsSet, currentCombination[index]) + 1];
                break;
            }
        }
    }

    public String getCombination() {
        return String.valueOf(currentCombination);
    }
}
