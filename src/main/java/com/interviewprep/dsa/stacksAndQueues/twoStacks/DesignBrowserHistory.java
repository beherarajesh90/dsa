package com.interviewprep.dsa.stacksAndQueues.twoStacks;

import java.util.ArrayList;
import java.util.List;

public class DesignBrowserHistory {

}
//using single stack - efficient approach
class BrowserHistory {

    List<String> stack;
    int cur;

    public BrowserHistory(String homepage) {
        stack = new ArrayList<>();
        stack.add(homepage);
        cur = 0;
    }

    public void visit(String url) {
        //remove forward history
        while(stack.size()-1 > cur){
            stack.remove(stack.size() - 1);
        }
        stack.add(url);
        cur++;
    }

    public String back(int steps) {
        cur = Math.max(0, cur - steps);
        return stack.get(cur);
    }

    public String forward(int steps) {
        cur = Math.min(stack.size()-1, cur + steps);
        return stack.get(cur);
    }
}

//using two stacks - less efficient approach
// class BrowserHistory {

//     Stack<String> history;
//     Stack<String> fwdHistory;
//     String homePage;

//     public BrowserHistory(String homepage) {
//         history = new Stack<>();
//         history.push(homepage);
//         homePage = homepage;
//         fwdHistory = new Stack<>();
//     }

//     public void visit(String url) {
//         fwdHistory.clear();
//         history.push(url);
//     }

//     public String back(int steps) {
//         while(history.size() !=1 && steps-- > 0){
//             fwdHistory.push(history.pop());
//         }
//         return history.peek();
//     }

//     public String forward(int steps) {
//         while(!fwdHistory.isEmpty() && steps-- > 0){
//             history.push(fwdHistory.pop());
//         }
//         return history.peek();
//     }
// }