package main.java;

import java.util.Stack;

public class AnalogyInfo {
    public static class Pair {
        Pair(int init) {
            this.start = init;
            this.end = init;
        }
        public int start;
        public int end;
    }

    Stack<Pair> analogies;

    AnalogyInfo() {
        this.analogies = new Stack<>();
    }

    public void add(int an) {
        if (this.analogies.size() == 0) {
            this.analogies.push(new Pair(an));
            return;
        }
        Pair last = this.analogies.pop();
        if (last.start - 1 == an) {
            last.start--;
            this.analogies.push(last);
            return;
        }
        this.analogies.push(last);
        this.analogies.push(new Pair(an));
    }
}
