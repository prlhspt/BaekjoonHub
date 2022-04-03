import java.util.*;
import java.util.concurrent.*;

class Solution {

    class Doc implements Comparable<Doc> {
        private int index;
        private int importance;

        public int getIndex() {
            return index;
        }

        public int getImportance() {
            return importance;
        }

        public Doc(int index, int importance) {
            this.index = index;
            this.importance = importance;
        }

        @Override
        public int compareTo(Doc o) {
            if (this.importance == o.importance) {
                if (this.index < o.index) {
                    return 1;
                } else {
                    return -1;
                }
            } else if (this.importance > o.importance) {
                return 1;
            } else {
                return -1;
            }
        }
    }


        public int solution(int[] priorities, int location) {

             Queue<Doc> docQueue = new ArrayBlockingQueue<>(priorities.length);

            for (int i = 0; i < priorities.length; i++) {
                Doc doc = new Doc(i, priorities[i]);
                docQueue.offer(doc);
            }

            // importance 가 제일 높은 값
            Doc maxImportanceDoc = Collections.max(docQueue);

            int count = 0;
            while (true) {
                Doc poll = docQueue.poll();

                if (poll.getImportance() == maxImportanceDoc.getImportance()) {
                    if (poll.getIndex() == location) {
                        count++;
                        return count;
                    } else {
                        maxImportanceDoc = Collections.max(docQueue);
                        count++;
                        continue;
                    }
                }
                docQueue.offer(poll);
            }
        }
    }