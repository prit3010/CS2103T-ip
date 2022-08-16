import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    private final static String UNDERLINE = "_________________________________";
    private ArrayList<Task> taskList = new ArrayList<>();
    boolean inProcess = true;

    private String greet(String res) {
        if (res.toLowerCase().equals("bye")) {

            inProcess = false;
            return "Bye. Hope to see you again soon!";

        } else if(res.toLowerCase().equals("list")) {

            String log = "Tasks that you have:";
            for (int i = 0; i < taskList.size(); i++) {
                log += String.format("\n %d. %s", i + 1, this.taskList.get(i));
            }
            return log;

        } else if(res.toLowerCase().startsWith("mark")){

            int taskNumber = Integer.valueOf(res.substring("mark ".length()));
            return this.handleMark(taskNumber);

        } else if(res.toLowerCase().startsWith("unmark")){

            int taskNumber = Integer.valueOf(res.substring("unmark ".length()));
            return this.handleUnmark(taskNumber);

        } else if (res.toLowerCase().startsWith("todo")){

            String taskdes = res.substring("todo ".length());
            return this.handleAdd(new ToDo(taskdes));

        } else if(res.toLowerCase().startsWith("deadline")){

            int endPointer = res.indexOf('/');
            String taskdes = res.substring("deadline ".length(),endPointer);
            String by = res.substring(endPointer + 3);
            return this.handleAdd(new Deadline(taskdes,by));

        } else if(res.toLowerCase().startsWith("event")){

            int endPointer = res.indexOf('/');
            String taskdes = res.substring("event ".length(),endPointer);
            String at = res.substring(endPointer + 3);
            return this.handleAdd(new Event(taskdes,at));

        } else {

            this.taskList.add(new Task(res));
            return String.format("added: %s",res);

        }
    }

    private String handleMark(int num){
        Task task = this.taskList.get(num - 1);
        task.markAsDone();
        return String.format("Nice! I've marked this task as done: \n %s",task);
    }

    private String handleUnmark(int num){
        Task task = this.taskList.get(num - 1);
        task.unmarkAsDone();
        return String.format("Ok! I have marked this task as not done yet: \n %s",task);
    }

    private String handleAdd(Task task){
        this.taskList.add(task);
        return String.format("Got it. I've added this task: \n %s \n  Now you have %d tasks in the list.", task ,this.taskList.size());
    }


    private void run() {
        Scanner sc= new Scanner(System.in);
        System.out.println(UNDERLINE);
        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");
        System.out.println(UNDERLINE);
        while(inProcess) {
            String a= sc.nextLine();
            System.out.println(UNDERLINE + "\n" + greet(a) + "\n" + UNDERLINE);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        new Duke().run();
        }
    }

