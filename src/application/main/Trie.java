package main;

public class Trie {
   final private TrieNode firstChar;

    public Trie() {
        firstChar = new TrieNode();
    }

    public void insert(Word dummie) {
        TrieNode cur = firstChar;
        String word=dummie.getTarget();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.child[c - 'a'] == null) {
                cur.child[c - 'a'] = new TrieNode();

            }
            cur = cur.child[c - 'a'];
        }
        cur.wordExisted = true;
        cur.meaning=dummie.getExplain();
    }

    public void search(String word)
    {
        TrieNode cur = firstChar;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.child[c - 'a'] == null) {
                System.out.println(word + " not found!!");
                return;
            }
            cur = cur.child[c - 'a'];
        }
        if (cur.wordExisted) {
            System.out.println(word + " "+cur.meaning);
        } else {
            System.out.println(word + " not found\n---> Suggestions:");
            wordSatisfied(word, cur);
        }
    }

    public void wordSatisfied(String word, main.TrieNode cur) {
        if (cur.wordExisted) {
            System.out.println(word + " "+cur.meaning);
        }
        for (int i = 0; i < 26; i++) {
            if (cur.child[i] != null) {
                wordSatisfied(word + (char) (i + 'a'), cur.child[i]);
            }
        }
    }
}
