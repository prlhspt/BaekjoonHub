// 위상 정렬 느낌으로 각자 나한테 오는 노드 개수가 0개인 것부터 시작해서, 퍼져나가면서 -1 해서 계산하면 될 듯
// 계산은 맨 끝에서부터 진행하는게 맞음 그래야 n번 탐색으로 끝낼 수 있음

import java.util.*;

class Solution {
    
    boolean[] visited;
    String[] Enroll;
    String[] Seller;
    String[] Amount;
    
    Map<String, String> map;
    Map<String, Integer> moneyMap;
    
    public void dfs(String start, int money) {
        
        if (money <= 0) {
            return;
        }
        
        String parent = map.get(start);
        
        if (parent.equals("")) {
            int rest = money * 10 / 100;
            money -= rest;
            moneyMap.put("center", moneyMap.get("center") + rest);
            moneyMap.put(start, moneyMap.get(start) + money);
            return;
            
        } else {
            int rest = money * 10 / 100;
            money -= rest;
            moneyMap.put(start, moneyMap.get(start) + money);
            dfs(parent, rest);
        }
        
    }
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        
        Enroll = enroll;
        Seller = seller;
        
        int[] result = new int[enroll.length];
        
        map = new HashMap<>();
        moneyMap = new HashMap<>();
        
        visited = new boolean[enroll.length];
        
        moneyMap.put("center", 0);
        
        for (int i = 0; i < enroll.length; i++) {
            moneyMap.put(enroll[i], 0);
            if (!referral[i].equals("-")) {
                map.put(enroll[i], referral[i]);
            } else {
                map.put(enroll[i], "");
            }
        }
        
        for (int i = 0; i < seller.length; i++) {
            int money = amount[i] * 100;
            dfs(seller[i], money);
        }
        
        for (int i = 0; i < enroll.length; i++) {
            result[i] = moneyMap.get(enroll[i]);
        }
        
        return result;
        
        }
}