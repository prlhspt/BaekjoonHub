import java.util.Arrays;

class Solution {

    public String makeTimeString(int hour, int minute) {
        if (minute == -1) {
            hour--;
            minute = 59;
        }

        StringBuilder sb = new StringBuilder();

        if (hour < 10) {
            sb.append("0");
        }
        sb.append(hour);
        sb.append(":");

        if (minute < 10) {
            sb.append("0");
        }
        sb.append(minute);

        return sb.toString();

    }

    public String solution(int n, int t, int m, String[] timetable) {

        int[] table = new int[timetable.length];
        for (int i = 0; i < timetable.length; i++) {
            String[] split = timetable[i].split(":");
            table[i] =  Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
        }

        Arrays.sort(table);

        int tableLength = table.length;

        int time = 540;
        int lastTime = 540 + (n - 1) * t;

        boolean fullFlag = false;
        int index = 0;
        for (int i = 0; i < n; i++) {
            fullFlag = false;
            int count = 0;

            while (index < tableLength && time >= table[index] && count < m) {
                index++;
                count++;
            }

            if (count == m) {
                fullFlag = true;
            }
            time += t;
        }

        if (index == 0) {
            int hour = lastTime / 60;
            int minute = lastTime % 60;
            String result = makeTimeString(hour, minute);
            return result;
        }

        if ((table[table.length - 1] <= 540 && tableLength < m)) {
            return "09:00";
        }

        String result;

        if (fullFlag) {
            int lastCrew = table[index - 1];
            int hour = lastCrew / 60;
            int minute = lastCrew % 60 - 1;
            result = makeTimeString(hour, minute);
        } else {
            int hour = lastTime / 60;
            int minute = lastTime % 60;
            result = makeTimeString(hour, minute);
        }

        return result;
    }
}