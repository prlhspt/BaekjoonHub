import java.util.*;

/**
 실행시간이 가장 짧은 것부터 처리하는것이 가장 빨리 처리된다.
 */

class Disk {
    private int startTime;
    private int operationTime;

    public Disk(int startTime, int endTime) {
        this.startTime = startTime;
        this.operationTime = endTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getOperationTime() {
        return operationTime;
    }
}

class Solution {
    public int solution(int[][] jobs) {
        if (jobs.length == 1) {
            return jobs[0][1];
        }

        int answer = 0;
        int time = 0;
        Disk cDisk = null;
        Disk tDisk = null;

        Queue<Disk> remainTask = new PriorityQueue<>((o1, o2) -> {
            if (o1.getStartTime() == o2.getStartTime()) {
                return o1.getOperationTime() < o2.getOperationTime() ? -1
                        : o1.getOperationTime() == o2.getOperationTime() ? 0 : 1;
            } else {
                return o1.getStartTime() < o2.getStartTime() ? -1
                        : o1.getStartTime() == o2.getStartTime() ? 0 : 1;
            }
        });

        Queue<Disk> tempTask = new PriorityQueue<>((o1, o2) -> {
            if (o1.getOperationTime() == o2.getOperationTime()) {
                return o1.getStartTime() < o2.getStartTime() ? -1
                        : o1.getStartTime() == o2.getStartTime() ? 0 : 1;
            } else {
                return o1.getOperationTime() < o2.getOperationTime() ? -1
                        : o1.getOperationTime() == o2.getOperationTime() ? 0 : 1;
            }
        });

        for (int i = 0; i < jobs.length; i++) {
            remainTask.offer(new Disk(jobs[i][0], jobs[i][1]));
        }

        while (!remainTask.isEmpty() || !tempTask.isEmpty()) {
            cDisk = remainTask.poll();

            if ((cDisk != null && cDisk.getStartTime() >= time) && tempTask.isEmpty()) {
                if (time <= cDisk.getStartTime()) {
                    time = (cDisk.getStartTime() + cDisk.getOperationTime());
                    answer += cDisk.getOperationTime();
                }
            } else {
                while (cDisk != null && cDisk.getStartTime() < time) {
                    tempTask.offer(cDisk);
                    cDisk = remainTask.poll();
                }

                tDisk = tempTask.poll();
                time += tDisk.getOperationTime();
                answer += (time - tDisk.getStartTime());

                if (cDisk != null) {
                    remainTask.offer(cDisk);
                    }

            }
        }
        return answer / jobs.length;
    }
}