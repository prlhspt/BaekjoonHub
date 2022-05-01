/*
fees
기본 시간 : 180
기본 요금 : 5000
단위 시간 : 10
단위 요금 : 600

records
시간 : 05:34
차량 번호 : 5961
동작 내용 : IN

기본 시간 안넘으면 기본 요금
기본 시간 넘기면 [(총 시간 - 기본 시간) / 10 ]올림 * 단위 요금

결과 값은 차량 번호 작은 순으로

*/

import java.util.*;

class Solution {
    
    class Car {
        int inputTime;
        int allTime;
        int fee;
        
        public Car(int inputTime) {
            this.inputTime = inputTime;
        }
        
    }
    
    public int[] solution(int[] fees, String[] records) {
        
        int defaultTime = fees[0];
        int defaultFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];
        
        // 어짜피 in이 있어야 out이 있음, 시간을 분으로 해서 저장한다음 그 차이만큼 계산하면 될 듯?
        // in 시간이랑 총 시간이 같이 저장되어 있는게 유리함
        Map<String, Car> map = new HashMap<>();
        
        Set<String> names = new TreeSet<>();
        
        for (int i = 0; i < records.length; i++) {
                        
            String[] split = records[i].split(" ");
            String time = split[0];
            String carNum = split[1];
            String action = split[2];
            
            names.add(carNum);
            
            if (action.equals("IN")) {
                String[] splitTime = time.split(":");
                int minute = Integer.parseInt(splitTime[0]) * 60 + Integer.parseInt(splitTime[1]);
                
                if (map.containsKey(carNum)) {
                    map.get(carNum).inputTime = minute;
                } else {
                    map.put(carNum, new Car(minute));
                }
                    
            } else {
                String[] splitTime = time.split(":");
                int minute = Integer.parseInt(splitTime[0]) * 60 + Integer.parseInt(splitTime[1]);
                map.get(carNum).allTime += minute - map.get(carNum).inputTime;
                map.get(carNum).inputTime = -1;
            }
        }
        
        for (Map.Entry<String, Car> e : map.entrySet()) {
            
            Car value = e.getValue();
            
            if (value.inputTime != -1) {
                
                int minute = 23 * 60 + 59;
                value.allTime += (minute - value.inputTime);
                value.inputTime = 0;
            }
            
            if (value.allTime <= defaultTime) {
                value.fee = defaultFee;
            } else {
                
                value.fee = defaultFee + (int) Math.ceil(((double) value.allTime - defaultTime) / unitTime) * unitFee;
            }            
            
        }
        
        int[] answer = new int[names.size()];
        
        Iterator<String> it = names.iterator();
        int idx = 0;
        while (it.hasNext()) {
            String name = it.next();
            answer[idx] = map.get(name).fee;
            idx++;
        }
        
        
        return answer;
    }
}