// Hana Clements
// CS&143
// Week 7 HW #4: HTML Checker

package edu.hanalways.cs143.StacksAndQueues.HTMLChecker;

import java.util.*;

public class HTMLManager {
  private Queue<HTMLTag> tags;

  HTMLManager(Queue<HTMLTag> tags) {
    if (tags == null) throw new IllegalArgumentException("invalid Queue provided");
    this.tags = tags;
  }

  // pre: none
  // post: returns tags stored in the HTMLManager instance
  public Queue<HTMLTag> getTags() {
    return this.tags;
  }

  // pre: none
  // post: returns a trimmed, formatted string of the instance's tags
  //    doesn't change the original state of the queue
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < tags.size(); i++) {
      HTMLTag tag = tags.remove();
      sb.append(tag.toString().trim());
      tags.add(tag);
    }

    return sb.toString();
  }

  // pre: an instance of a HTMLManager with tags added to the queue
  // post: the instance's queue in with valid HTML tag structure
  //    all given open tags have respective closing tabs
  //    any stray open tags get their respective close tags
  //    any stray close tags are removed
  //    comments left alone
  public void fixHTML() {
    Stack<HTMLTag> s = new Stack<>();

    int size = tags.size();
    for (int i = 0; i < size; i++) {
      HTMLTag tag = tags.remove();

      // opening tags are added to the output and to the stack
      if (tag.isOpening()) {
        s.push(tag);
        tags.add(tag);
      }

      // check to see if the closing tag matches the opening tags
      // if the opening tag does not match the closing tag, HTML writer made an error
      // fix the error by inserting a matching tag to the output
      // pop the open tag off the stack
      // check to see if the closing tag matches the opening tags
      else if (tag.isClosing()) {
        if (!s.isEmpty()) {
          HTMLTag closer = s.peek();

          // if the opening tag matches the closing tag, pop it off the stack
          if (closer.matches(tag)) {
            s.pop();
            tags.add(tag);
          }
        }
      }

      // NOTE: self-closing tags or comments do not need to be added onto the stack
      else {
        tags.add(tag);
      }
    }

    // if there are any leftover open tags, just close them out
    while (!s.isEmpty()) {
      HTMLTag tag = s.pop();
      tags.add(tag.getMatching());
    }
  }
}

//6:23:53 PM: Executing ':edu.hanalways.cs143.StacksAndQueues.HTMLChecker.HTMLChecker.main()'…
//
//> Task :compileJava
//> Task :processResources UP-TO-DATE
//> Task :classes
//
//> Task :edu.hanalways.cs143.StacksAndQueues.HTMLChecker.HTMLChecker.main()
//===============================
//Processing HTMLChecker/tests/test3.html...
//===============================
//HTML: <br /></p></p>
//Checking HTML for errors...
//HTML after fix: <br />
//----> Result matches Expected Output!
//
//===============================
//Processing HTMLChecker/tests/test2.html...
//===============================
//HTML: <a><a><a></a>
//Checking HTML for errors...
//HTML after fix: <a><a><a></a></a></a>
//----> Result matches Expected Output!
//
//===============================
//Processing HTMLChecker/tests/test5.html...
//===============================
//HTML: <div><h1></h1><div><img /><p><br /><br /><br /></div></div></table>
//Checking HTML for errors...
//HTML after fix: <div><h1></h1><div><img /><p><br /><br /><br /></p></div></div>
//----> Result matches Expected Output!
//
//===============================
//Processing HTMLChecker/tests/test4.html...
//===============================
//HTML: <div><div><ul><li></li><li></li><li></ul></div>
//Checking HTML for errors...
//HTML after fix: <div><div><ul><li></li><li></li><li></li></ul></div></div>
//----> Result matches Expected Output!
//
//===============================
//Processing HTMLChecker/tests/test1.html...
//===============================
//HTML: <b><i><br /></b></i>
//Checking HTML for errors...
//HTML after fix: <b><i><br /></i></b>
//----> Result matches Expected Output!
//
//===============================
//        All tests passed!
//===============================
//
//BUILD SUCCESSFUL in 83ms
//3 actionable tasks: 2 executed, 1 up-to-date
//Consider enabling configuration cache to speed up this build: https://docs.gradle.org/9.6.0/userguide/configuration_cache_enabling.html
//6:23:53 PM: Execution finished ':edu.hanalways.cs143.StacksAndQueues.HTMLChecker.HTMLChecker.main()'.
