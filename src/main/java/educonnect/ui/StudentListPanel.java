package educonnect.ui;

import educonnect.model.student.Student;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

/**
 * Panel containing the list of students.
 */
public class StudentListPanel extends UiPart<Region> {
    private static final String FXML = "StudentListPanel.fxml";

    @FXML
    private ListView<Student> studentListView;
    /**
     * Creates a {@code StudentListPanel} with the given {@code ObservableList},
     * and the default value of {@code showTimetable} option.
     */
    public StudentListPanel(ObservableList<Student> studentList) {
        super(FXML);
        studentListView.setItems(studentList);
        studentListView.setCellFactory(listView -> new StudentListViewCell());
    }

    public void showTimetable() {
        studentListView.setCellFactory(listView -> new StudentListViewCell(true));
    }

    public void hideTimetable() {
        studentListView.setCellFactory(listView -> new StudentListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Student} using a {@code StudentCard}.
     */
    class StudentListViewCell extends ListCell<Student> {
        private boolean showTimetable;

        public StudentListViewCell() {
            this.showTimetable = false;
        }

        public StudentListViewCell(boolean showTimetable) {
            this.showTimetable = showTimetable;
        }

        @Override
        protected void updateItem(Student student, boolean empty) {
            super.updateItem(student, empty);

            if (empty || student == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new StudentCard(student, getIndex() + 1, showTimetable).getRoot());
            }
        }
    }
}
