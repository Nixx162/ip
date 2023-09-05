package pardiyem.command;

import java.io.IOException;

import pardiyem.storage.Storage;
import pardiyem.task.TaskList;
import pardiyem.ui.Ui;

public class MarkCommand extends Command {

    public MarkCommand(String desc) {
        super(desc);
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        try {
            int i = Integer.parseInt(desc) - 1;
            if (i < 0 || i >= taskList.size()) {
                throw new ArrayIndexOutOfBoundsException(
                    "Whoops, that number is not an index in the list. Please select a valid index");
            }
            ui.showOutput(String.format("%s\n%s", 
                    taskList.getTask(i).markAsDone(), 
                    taskList.getTask(i).toString()));
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Whoops, you need to type in a valid integer");
        }
        storage.save(taskList);
    };
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof MarkCommand) {
            result = this.desc.equals(((MarkCommand) obj).desc);
        }
        return result;
    }
}
