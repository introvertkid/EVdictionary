package main;

public class Trie {
    final private TrieNode firstChar;

    public Trie() {
        firstChar = new TrieNode();
    }

    public void insert(Word dummie) {
        TrieNode cur = firstChar;
        String word = dummie.getTarget();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.child[c - 'a'] == null) {
                cur.child[c - 'a'] = new TrieNode();
            }
            cur = cur.child[c - 'a'];
        }
        cur.wordExisted = true;
        cur.meaning = dummie.getExplain();
    }

    public TrieNode search(String word)
    {
        TrieNode cur = firstChar;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.child[c - 'a'] == null) {
                return null;
            }
            cur = cur.child[c - 'a'];
        }
        return cur;
    }

    public static void wordSatisfied(String word, main.TrieNode cur) {
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
