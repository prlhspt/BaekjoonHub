import java.util.*;

class Solution {
    
    class Info {
        private String parent;
        private int money;
        
        public Info(String parent, int money) {
            this.parent = parent;
            this.money = money;
        }
        
        @Override
        public String toString() {
            return "parent: " + parent + ", money: " + money + "\n";
        }
    }
    
    public void share(Map<String, Info> map, String seller, int money) {
        
        Info info = map.get(seller);
        
        info.money += money;
        money = (int) (money * 0.1);
        info.money -= money;
        
        if (map.get(seller).parent.equals("-") || money <= 0) {
            return;
        } 
            
        share(map, info.parent, money);
        
    }
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        
        int[] answer = new int[enroll.length];

        Map<String, Info> map = new HashMap<>();
        
        for (int i = 0; i < enroll.length; i++) {
            map.put(enroll[i], new Info(referral[i], 0));
        }
        
        for (int i = 0; i < seller.length; i++) {
            share(map, seller[i], amount[i]*100);
        }
        
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = map.get(enroll[i]).money;
        }
        
        return answer;
    }
}