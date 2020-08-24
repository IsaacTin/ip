import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
        try {
            tasks = new TaskList(storage.readFile());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        String fullCommand = "";
        while (!fullCommand.equals("bye")) {
            try {
                Command command = parser.findCommand(fullCommand);
                command.execute(this.tasks, this.ui, this.storage);
                storage.save(tasks.getList());
                fullCommand = ui.readCommand();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
                fullCommand = ui.readCommand();
            }
        }
        ui.showExit();
    }
    public static void main(String[] args) {
        new Duke().run();
    }
}


