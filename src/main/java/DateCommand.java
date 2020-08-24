public class DateCommand extends Command{
    private String description;

    DateCommand(String description) {
        this.description = description;
    }

    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        boolean dateExists = false;
        for (Task i : tasklist.getList()) {
            if (i instanceof Deadline) {
                if(((Deadline) i).hasDate(description)) {
                    System.out.println(i);
                    dateExists = true;
                }
            } else if (i instanceof Event) {
                if (((Event) i).hasDate(description)) {
                    System.out.println(i);
                    dateExists = true;
                }
            }
        }
        if (!dateExists) {
            throw new DukeException("No events/deadlines with this date!");
        }
    }
}
