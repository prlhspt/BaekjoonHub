/* 
알파벳 한개씩, words에 있는 단어로만
begin = hit, target = cog
hit -> hot -> dot -> dog -> cog

*/

import java.util.*; 

class Solution {

    boolean[] visited;
    int result = Integer.MAX_VALUE;
    // 단어가 하나만 다른지 확인해보기
    void dfs(String[][] splitWords, String[] word, String target, int index) {

        if (String.join("", word).equals(target)) {
            result = Math.min(result, index);
            return;
        }

        //hit -> hot -> dot -> dog -> cog
        for (int i = 0; i < splitWords.length; i++) {
            int wordCount = 0;
            if (!visited[i]) {
                for (int j = 0; j < splitWords[i].length; j++) {
                    if (splitWords[i][j].equals(word[j])) {
                        wordCount++;
                    }
                }

                if (wordCount == word.length - 1) {
                    visited[i] = true;
                    dfs(splitWords, splitWords[i], target, index + 1);
                    visited[i] = false;
                }
            }
        }
    }

    public int solution(String begin, String target, String[] words) {

        visited = new boolean[words.length];

        String[][] splitWords = new String[words.length][begin.length()];

        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                splitWords[i][j] = words[i].split("")[j];
            }
        }

        dfs(splitWords, begin.split(""), target, 0);

        if (result == Integer.MAX_VALUE) {
            return 0;
        } else {
            return result;
        }
    }
}