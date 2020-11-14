package ko.maeng.base.java11.processes;

import java.util.Optional;

public class ProcessTest3 {

  public static void main(String[] args) {
    Optional<ProcessHandle> first =
        ProcessHandle.allProcesses().findFirst();
    first.ifPresent(proc -> proc.children().forEach(child -> System.out
        .println("PID: [ " + child.pid() + " ], Cmd: [ " + child.info().command() + " ]")));
  }
}
