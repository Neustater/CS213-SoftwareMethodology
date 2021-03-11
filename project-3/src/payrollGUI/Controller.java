package payrollGUI;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Controller{
    static final int MIN_PAY = 0;
    static final int MIN_HOURS = 0;
    static final int MAX_HOURS = 100;
    static final String[] DEPTS = {"CS","ECE","IT"};
    static final int[] CODES = {1,2,3};

    private boolean validNameInput = false;
    private boolean validDateInput = false;
    private boolean validSalaryInput = false;
    private boolean validRateInput = false;
    private boolean validHoursInput = false;

    Company company = new Company();

    //Data Regarding Current Employee
    String name;
    String department;
    double rate = 0;
    double salary;
    int code;
    int hours = 0;
    Date dateHired;

    @FXML
    private TextArea consoleOutput;

    @FXML
    private ToggleGroup departmentGroup, employeeGroup, roleGroup;

    @FXML
    private RadioButton csButton, itButton, eceButton, parttimeButton, fulltimeButton, managementButton,
            managerButton, deptHeadButton, directorButton;

    @FXML
    private DatePicker dateInput;

    @FXML
    private TextField salaryInput, hoursInput, rateInput, nameInput;

    @FXML
    private Button clearButton, addButton, removeButton, setHoursButton;

    @FXML
    void addEmployee(ActionEvent event) {

        if(fulltimeButton.isSelected()){
            double annualSalary =  Double.valueOf(salaryInput.getText());
            if(csButton.isSelected()){
                Fulltime emp = new Fulltime(name, DEPTS[0], dateHired, annualSalary);
                company.add(emp);
            } else if(eceButton.isSelected()){
                Fulltime emp = new Fulltime(name, DEPTS[1], dateHired, annualSalary);
                company.add(emp);
            } else if(itButton.isSelected()){
                Fulltime emp = new Fulltime(name, DEPTS[2], dateHired, annualSalary);
                company.add(emp);
            }
        } else if(parttimeButton.isSelected()){
            double hourlyRate = Double.valueOf(rateInput.getText());
            if(csButton.isSelected()){
                Parttime emp = new Parttime(name, DEPTS[0], dateHired, hourlyRate);
                company.add(emp);
                emp.setHours(hours);
            } else if(eceButton.isSelected()){
                Parttime emp = new Parttime(name, DEPTS[1], dateHired, hourlyRate);
                company.add(emp);
                emp.setHours(hours);
            } else if(itButton.isSelected()){
                Parttime emp = new Parttime(name, DEPTS[2], dateHired, hourlyRate);
                company.add(emp);
                emp.setHours(hours);
            }
        } else if(managementButton.isSelected()) {
            double annualSalary = Double.valueOf(salaryInput.getText());

            int role = 0;
            if (managerButton.isSelected()) {
                role = 1;
            } else if (deptHeadButton.isSelected()) {
                role = 2;
            } else if (directorButton.isSelected()) {
                role = 3;
            }

            if (csButton.isSelected()) {
                Management emp = new Management(name, DEPTS[0], dateHired, annualSalary, role);
                company.add(emp);
            } else if (eceButton.isSelected()) {
                Management emp = new Management(name, DEPTS[1], dateHired, annualSalary, role);
                company.add(emp);
            } else if (itButton.isSelected()) {
                Management emp = new Management(name, DEPTS[2], dateHired, annualSalary, role);
                company.add(emp);
            }
        }
        consoleOutput.appendText("Employee Added!\n");
    }

    @FXML
    void calculatePayments(ActionEvent event) {
        if (company.isEmpty()) {
            consoleOutput.appendText("Employee database is empty!\n");
        }

        company.processPayments();
        consoleOutput.appendText("Calculation of employee payments is done.\n");
    }

    @FXML
    void clear(ActionEvent event) {
        consoleOutput.clear();
        salaryInput.clear();
        validSalaryInput = false;
        nameInput.clear();
        validNameInput = false;
        hoursInput.clear();
        validHoursInput = false;
        rateInput.clear();
        validRateInput = false;
        dateInput.setValue(null);
        isValidInput();


    }

    @FXML
    void exportFile(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Target File for the Export");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File targetFile;
        try{
            targetFile = chooser.showSaveDialog(stage); //get the reference of the target file
        }catch(Exception e){
            consoleOutput.appendText("An error has occurred selecting the file.\n");
            return;
        }

        if(targetFile == null){
            consoleOutput.appendText("No File Selected: Please Select or Create a new File.\n");
            return;
        }

        company.outputToFile(targetFile, consoleOutput);
    }

    @FXML
    void importFile(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Source File for the Import");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File sourceFile;
        try{
            sourceFile = chooser.showOpenDialog(stage); //get the reference of the source file
        }catch(Exception e){
            consoleOutput.appendText("An error has occurred selecting the file.\n");
            return;
        }

        if(sourceFile == null){
            consoleOutput.appendText("No File Selected: Please Select a File.\n");
            return;
        }
        BufferedReader reader;
        String name;
        String department;
        String dateStr;
        String employeeType;
        Date date;
        double hourlyRate;
        double salary;
        int role;
        StringTokenizer command;

        try {
            reader = new BufferedReader(new FileReader(sourceFile));
            String line = reader.readLine();
            while(line != null) {
                try {
                    command = new StringTokenizer(line, ",");
                    employeeType = command.nextToken();
                    name = command.nextToken();
                    department = command.nextToken();
                    dateStr = command.nextToken();
                    date = new Date(dateStr);
                    Employee emp;
                    if(employeeType.equals("P")){
                        hourlyRate = Double.parseDouble(command.nextToken());
                        emp = new Parttime(name, department, date, hourlyRate);
                        company.add(emp);
                    } else if(employeeType.equals("F")){
                        salary = Double.parseDouble(command.nextToken());
                        emp = new Fulltime(name, department, date, salary);
                        company.add(emp);
                    } else if(employeeType.equals("M")){
                        salary = Double.parseDouble(command.nextToken());
                        role = Integer.parseInt(command.nextToken());
                        emp = new Management(name, department, date, salary, role);
                        company.add(emp);
                    }
                    line = reader.readLine();
                    if (command.hasMoreTokens())
                        consoleOutput.setText("File Format is incorrect");
                }catch(NoSuchElementException e){
                    consoleOutput.setText("File Format is incorrect");
                    break;
                }
            }
            consoleOutput.setText("Database Imported!");

        } catch(IOException exception){
            consoleOutput.setText("Import Error!");
        }

    }

    @FXML
    void print(ActionEvent event) {
        if (company.isEmpty()) {
            consoleOutput.setText("Employee database is empty!\n");
        } else {
            consoleOutput.setText("--Printing earning statements for all employees--\n");
            company.printToController(consoleOutput);
        }
    }

    @FXML
    void printDate(ActionEvent event) {
        if (company.isEmpty()) {
            consoleOutput.setText("Employee database is empty!\n");
        } else {
            consoleOutput.setText("--Printing earning statements by date hired--\n");
            company.sortByDate();
            company.printToController(consoleOutput);
        }
    }

    @FXML
    void printDept(ActionEvent event) {
        if (company.isEmpty()) {
            consoleOutput.setText("Employee database is empty!\n");
        } else {
            consoleOutput.setText("--Printing earning statements by department--\n");
            company.sortByDepartment();
            company.printToController(consoleOutput);
        }
    }

    @FXML
    void setName(KeyEvent event) {
        name = nameInput.getText();
        if(name.equals("")){
            consoleOutput.appendText("Please enter a valid name.\n");
            validNameInput = false;
        }else {
            validNameInput = true;
        }
        isValidInput();
    }

    @FXML
    void setCS(ActionEvent event) {
        department = DEPTS[1];
    }

    @FXML
    void setECE(ActionEvent event) {
        department = DEPTS[2];
    }

    @FXML
    void setIT(ActionEvent event) {
        department = DEPTS[1];
    }

    @FXML
    void setDate(Event event) {
        try {
            LocalDate localDate = dateInput.getValue();
            dateHired = new Date(localDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        }
        catch(NullPointerException e){
            consoleOutput.appendText("Please enter a valid date.\n");
            validDateInput = false;
            isValidInput();
            return;
        }
        if(dateHired.isValid()){
            validDateInput = true;
        }
        else{
            validDateInput = false;
            consoleOutput.appendText("Please enter a valid date.\n");
        }

        isValidInput();
    }

    @FXML
    void setPartTime(ActionEvent event) {
        isParttime();
        isValidInput();
    }

    @FXML
    void setFullTime(ActionEvent event) {
        isFulltime();
        managerButton.setDisable(true);
        deptHeadButton.setDisable(true);
        directorButton.setDisable(true);
        managerButton.setSelected(false);
        deptHeadButton.setSelected(false);
        directorButton.setSelected(false);
        isValidInput();
    }

    @FXML
    void setManagement(ActionEvent event) {
        isFulltime();
        managerButton.setSelected(true);
        managerButton.setDisable(false);
        deptHeadButton.setDisable(false);
        directorButton.setDisable(false);
        isValidInput();
    }

    @FXML
    void setHours(KeyEvent event) {

        try {
            hours = Integer.parseInt(hoursInput.getText());
        }catch (NumberFormatException e) {
            if(hoursInput.getText().equals("")){
                hours = 0;
                validHoursInput = false;
                isValidInput();
                return;
            }
            validHoursInput = false;
            consoleOutput.appendText("Please enter a valid integer value for hours\n");
            isValidInput();
            return;
        }

        if(hours > MAX_HOURS){
            validHoursInput = false;
            consoleOutput.appendText("Invalid Hours: Over " + MAX_HOURS+ "!\n");
        }else if (hours < MIN_HOURS) {
            validHoursInput = false;
            consoleOutput.appendText("Invalid Hours: Negative Value!\n");
        }else {
            validHoursInput = true;
        }
        isValidInput();
    }

    @FXML
    void setRate(KeyEvent event) {
        try {
            rate = Integer.parseInt(rateInput.getText());
        }catch (NumberFormatException e) {
            if(rateInput.getText().equals("")){
                rate = 0;
                validRateInput = false;
                isValidInput();
                return;
            }
            validRateInput = false;
            rate = 0;
            consoleOutput.appendText("Please enter a numeric value for the Pay Rate.\n");
            isValidInput();
            return;
        }

        if (rate < MIN_PAY) {
            validRateInput = false;
            consoleOutput.appendText("Invalid Pay Rate: Negative Value!\n");
        }else {
            validRateInput = true;
        }
        isValidInput();
    }

    @FXML
    void setHoursButton(ActionEvent event) {
        if (company.isEmpty()) {
            consoleOutput.appendText("Employee database is empty!\n");
        }else {

            Employee tempSetter = company.findEmployee(name, department, dateHired);

            if (tempSetter == null) {
                consoleOutput.appendText("Hours could not be set: Employee does not exist\n");
            } else if (!company.setHours(tempSetter)) {
                consoleOutput.appendText("Hours could not be set: Employee is not parttime\n");
            } else {
                Parttime parttimeSetter = (Parttime) tempSetter;
                parttimeSetter.setHours(hours);
                consoleOutput.appendText("Working hours set.\n");
            }
        }

    }

    @FXML
    void setSalary(KeyEvent event) {
        try {
            salary = Double.parseDouble(salaryInput.getText());
        }catch (NumberFormatException e) {
            validSalaryInput = false;
            salary = 0;
            consoleOutput.appendText("Please enter a numeric value for Salary.\n");
            isValidInput();
            return;
        }

        if (salary < MIN_PAY) {
            validSalaryInput = false;
            consoleOutput.appendText("Invalid Salary: Negative Value!\n");
        }else{
            validSalaryInput = true;
        }
        isValidInput();
    }

    @FXML
    void setManager(ActionEvent event) {
        code = CODES[1];
    }

    @FXML
    void setDeptHead(ActionEvent event) {
        code = CODES[2];
    }

    @FXML
    void setDirector(ActionEvent event) {
        code = CODES[3];
    }

    @FXML
    void removeEmployee(ActionEvent event) {
        String dept = null;
        if(csButton.isSelected()){
            dept = DEPTS[0];
        } else if(eceButton.isSelected()){
            dept = DEPTS[1];
        } else if(itButton.isSelected()){
            dept = DEPTS[2];
        }
        Employee tempRemoval = company.findEmployee(name, dept, dateHired);

        if(tempRemoval == null){
            consoleOutput.appendText("Employee doesn't exist.\n");
            return;
        }

        if(tempRemoval instanceof Fulltime){
            if(tempRemoval instanceof Management) {
                if(!managementButton.isSelected()){
                    consoleOutput.appendText("Employee doesn't exist.\n");
                    return;
                }
            } else {
                if (!fulltimeButton.isSelected()) {
                    consoleOutput.appendText("Employee doesn't exist.\n");
                    return;
                }
            }
        } else if(tempRemoval instanceof Parttime) {
            if(!parttimeButton.isSelected()){
                consoleOutput.appendText("Employee doesn't exist.\n");
                return;
            }
        }


        boolean removed = company.remove(tempRemoval);
        if(removed){
            consoleOutput.appendText("Employee removed.\n");
        } else if (company.isEmpty()) {
            consoleOutput.appendText("Employee database is empty!\n");
        } else {
            consoleOutput.appendText("Employee doesn't exist.\n");
        }

    }

    private void isParttime(){
        managerButton.setDisable(true);
        deptHeadButton.setDisable(true);
        directorButton.setDisable(true);
        managerButton.setSelected(false);
        deptHeadButton.setSelected(false);
        directorButton.setSelected(false);

        rateInput.setDisable(false);
        hoursInput.setDisable(false);
        setHoursButton.setDisable(false);

        salaryInput.setDisable(true);
        salaryInput.clear();
    }

    private void isFulltime(){

        rateInput.setDisable(true);
        rateInput.clear();
        hoursInput.setDisable(true);
        hoursInput.clear();
        setHoursButton.setDisable(true);

        salaryInput.setDisable(false);
    }

    private void isValidInput(){
        if(validNameInput) {
            if (validDateInput) {
                if (parttimeButton.isSelected()) {
                    if (validHoursInput && validRateInput) {
                        setHoursButton.setDisable(true);
                        addButton.setDisable(false);
                        removeButton.setDisable(true);
                    }else if(validRateInput && (hoursInput.getText().isEmpty())) {
                        removeButton.setDisable(true);
                        addButton.setDisable(false);
                        setHoursButton.setDisable(true);
                    }else if(validHoursInput && (rateInput.getText().isEmpty())) {
                        removeButton.setDisable(true);
                        addButton.setDisable(true);
                        setHoursButton.setDisable(false);
                    } else if((hoursInput.getText().isEmpty()) && (rateInput.getText().isEmpty())) {
                        removeButton.setDisable(false);
                        addButton.setDisable(true);
                        setHoursButton.setDisable(true);
                    }else {
                        setHoursButton.setDisable(true);
                        addButton.setDisable(true);
                        removeButton.setDisable(true);
                    }
                }else{
                    if(validSalaryInput){
                        setHoursButton.setDisable(true);
                        addButton.setDisable(false);
                        removeButton.setDisable(true);
                    } else {
                        setHoursButton.setDisable(true);
                        addButton.setDisable(true);
                        removeButton.setDisable(false);
                    }
                }
            }else{
                setHoursButton.setDisable(true);
                addButton.setDisable(true);
                removeButton.setDisable(true);
            }
        }else{
            setHoursButton.setDisable(true);
            addButton.setDisable(true);
            removeButton.setDisable(true);
        }
    }

}