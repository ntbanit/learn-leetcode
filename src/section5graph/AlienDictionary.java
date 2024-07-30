package section5graph;

import java.util.*;
class AlienLanguageOrder {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) return "";

        Map<Character, List<Character>> adjList = new HashMap<>();
        int[] indegree = new int[26]; // a -> 0, b -> 1 ...
        for (int i = 1; i < words.length; i++) {
            String prev = words[i - 1];
            String curr = words[i];
            if ((prev.length() == curr.length() + 1) && prev.substring(0, curr.length()).equals(curr)) // [abc, ab], s.length < t.length
                return "";
            for (int k = 0; k < Math.min(prev.length(), curr.length()); k++) {
                char c1 = prev.charAt(k);
                char c2 = curr.charAt(k);
                if (c1 != c2) {
                    if (!adjList.containsKey(c1)) {
                        adjList.put(c1, new ArrayList<>());
                    }
                    adjList.get(c1).add(c2);
                    indegree[c2 - 'a']++;
                    break;
                }
            }
        }

        Set<Character> uniqueCh = new HashSet<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                uniqueCh.add(c);
            }
        }

        PriorityQueue<Character> minHeap = new PriorityQueue<>();
        for (char c : uniqueCh) {
            if (indegree[c - 'a'] == 0)
                minHeap.add(c);
        }

        String topSort = "";
        while (!minHeap.isEmpty()) {
            char currCh = minHeap.poll();
            topSort += currCh;
            if (adjList.get(currCh) == null) continue;
            for (char neihbor : adjList.get(currCh)) {
                indegree[neihbor - 'a']--;
                if (indegree[neihbor - 'a'] == 0) {
                    minHeap.add(neihbor);
                }
            }
        }

        return topSort.length() == uniqueCh.size() ? topSort : "";
    }
}
// TC: O(n * m), SC: O(n * m)

public class AlienDictionary {
    private Map<Character, List<Character>> adjMap;
    private Map<Character, Integer> inDegreeMap;

    private void preProcess(char before){
        if (this.adjMap.get(before) == null) {
            this.adjMap.put(before, new ArrayList<>());
        }
        if (this.inDegreeMap.get(before) == null) {
            this.inDegreeMap.put(before, 0);
        }
    }

    private boolean preProcess(String first, String second) {
        int minLen = Math.min(first.length(), second.length());
        int k;
        for (k = 0; k < minLen; k++) {
            char before = first.charAt(k);
            char after = second.charAt(k);
            this.preProcess(before);

            if (before == after) continue;
            this.adjMap.get(before).add(after);
            if (this.inDegreeMap.get(after) == null) {
                this.inDegreeMap.put(after, 1);
            } else {
                this.inDegreeMap.put(after, this.inDegreeMap.get(after) + 1);
            }
            break;
        }
        if(k > 0 && k == minLen) return false;

        for(int i = k; i < first.length(); i++){
            char before = first.charAt(i);
            this.preProcess(before);
        }
        for(int i = k; i < second.length(); i++){
            char before = second.charAt(i);
            this.preProcess(before);
        }
        return true;
    }


    public String foreignDictionary(String[] words) {
        if(words == null || words.length == 0) return "";

        this.adjMap = new TreeMap<>();
        this.inDegreeMap = new TreeMap<>();

        if(words.length == 1){
            this.preProcess(words[0], "");
        }

        for (int i = 1; i < words.length; i++) {
            for (int j = 0; j < i; j++) {
                if(!this.preProcess(words[j], words[i])) return "";
            }
        }
//        System.out.println("debug map");
//        for(Character key : this.adjMap.keySet()){
//            System.out.print("key = "+key);
//            System.out.println(" afterList = "+this.adjMap.get(key));
//        }
//        for(Character key : this.inDegreeMap.keySet()){
//            System.out.println("key = "+key +" ; inDegreeMap = "+ this.inDegreeMap.get(key));
//        }

        String result = "";
        Queue<Character> queue = new LinkedList<>();
        for (Character ch : this.inDegreeMap.keySet()) {
            if (this.inDegreeMap.get(ch) == 0) {
                result += ch + "";
                queue.add(ch);
            }
        }

        while (!queue.isEmpty()) {
            Character front = queue.remove();
            List<Character> neighbors = this.adjMap.get(front);
            if (neighbors == null) continue;
            for (Character neighbor : neighbors) {
                int inDegree = this.inDegreeMap.get(neighbor) - 1;
                this.inDegreeMap.put(neighbor, inDegree);
                if (inDegree == 0) {
                    queue.add(neighbor);
                    result += neighbor + "";
                }
            }
        }

        if (result.length() != inDegreeMap.size()) return "";

        return result;
    }

    public static void main(String[] args) {
        String[][] testCases = {
                {"wrt", "wrf", "er", "ett", "rftt"}
                , {"z", "x"}
                , {"z", "x", "z"}
                 , {"abc", "ab"}
                , {"apple"}
//                , {"apple", "ankle","orange"}
        };
        for (String[] words : testCases) {
            String dictionary = new AlienDictionary().foreignDictionary(words);
            System.out.println(dictionary);
        }
    }
}
