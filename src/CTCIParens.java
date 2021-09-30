import java.util.*;

public class CTCIParens {
    public static void main(String[] args) {
        try {
            CTCIParens obj = new CTCIParens();
            obj.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run(String[] args) {
        int numberOfParenthesis = 3;
        System.out.println(findParensCombos(numberOfParenthesis, null, null, new ArrayList<Parenthesis>(), -1, new ArrayList<String>(),new HashSet<>()));
    }

    public ArrayList<String> findParensCombos(int numOfParens, Parenthesis parent, Parenthesis placeToAdd, ArrayList<Parenthesis> placesToInsert, int lastIndex, ArrayList<String> holdCombos, HashSet<String> holdAddedStrings) {
        numOfParens--;
        if (parent == null || placeToAdd == null) {
            parent = new Parenthesis();
            placeToAdd = parent;
        }
        if (lastIndex == -1) {
            placesToInsert.add(parent);
            lastIndex++;
        }
        Parenthesis parenToAdd = new Parenthesis();
        placeToAdd.addChild(parenToAdd);
        placesToInsert.add(parenToAdd);
        lastIndex++;
        if (numOfParens == 0) {
            String createdString = createParenString(parent, parent);
            if (!holdAddedStrings.contains(createdString)) {
                holdCombos.add(createParenString(parent, parent));
                holdAddedStrings.add(createdString);
            }
        }
        if (numOfParens > 0) {
            for (int x = 0; x <= lastIndex; x++) {
                findParensCombos(numOfParens, parent, placesToInsert.get(x), placesToInsert, lastIndex, holdCombos, holdAddedStrings);
            }
        }
        placeToAdd.removeChild();
        placesToInsert.remove(lastIndex);
        return holdCombos;
    }

    public String createParenString(Parenthesis parent, Parenthesis currParen) {
        if (currParen.getNumOfChildren() <= 0) {
            return "()";
        }
        String currString = "(";
        for (int x = 0; x < currParen.getNumOfChildren(); x++) {
            currString += createParenString(parent, currParen.returnChild(x));
        }
        currString += ")";
        if (parent == currParen) {
            return currString.substring(1, currString.length() - 1);
        } else {
            return currString;
        }
    }
}

class Parenthesis {
    int sizeOfList = 0;
    public ArrayList<Parenthesis> holdChildren;

    public Parenthesis() {
        holdChildren = new ArrayList<Parenthesis>();
    }

    public void addChild(Parenthesis parenthesis) {
        holdChildren.add(parenthesis);
        sizeOfList++;
    }

    public int getNumOfChildren() {
        return sizeOfList;
    }

    public Parenthesis returnChild(int indexOfChild) {
        return holdChildren.get(indexOfChild);
    }

    public Parenthesis removeAndReturnChild() {
        return holdChildren.remove(0);
    }

    public void removeChild() {
        holdChildren.remove(sizeOfList - 1);
        sizeOfList--;
    }
}