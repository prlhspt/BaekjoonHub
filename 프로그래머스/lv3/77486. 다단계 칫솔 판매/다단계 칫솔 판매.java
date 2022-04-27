import java.util.*;

class Solution {
    
    class Person {
        String name;
        Person parent;
        int money;
        
        public Person(String name, Person parent, int money) {
            this.name = name;
            this.parent = parent;
            this.money = money;
        }
        
        public void getReward(int pureMoney) {
            if (pureMoney <= 0) return;
            
            int rest = (int) (pureMoney * 0.1);
            money += (pureMoney - rest);
            if (parent != null) 
                this.parent.getReward(rest);
        }
        
    }
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        
        int[] answer = new int[enroll.length];
        
        Map<String, Person> personMap = new HashMap<>();
        
        for (int i = 0; i < enroll.length; i++) 
            personMap.put(enroll[i], new Person(enroll[i], null, 0));
        
        for (int i = 0; i < enroll.length; i++)
            if (!referral[i].equals("-")) 
                personMap.get(enroll[i]).parent = personMap.get(referral[i]);
        
        for (int i = 0; i < seller.length; i++)
            personMap.get(seller[i]).getReward(amount[i] * 100);

        for (int i = 0; i < enroll.length; i++) {
            answer[i] = personMap.get(enroll[i]).money;
        }
        
        return answer;
            
    }
}