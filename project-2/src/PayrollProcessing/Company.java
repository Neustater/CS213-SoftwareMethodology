package PayrollProcessing;

public class Company {
    private Employee[] emplist;
    private int numEmployee;

    private int find(Employee employee) {
        for(int index = 0; index < numEmployee; index++){
            if(employee.returnProfile().equals(emplist[index].returnProfile())){
                return index;
            }
        }
        return -1;
    }

    private void grow() {
        int curr_catalogSize = emplist.length;
        int new_catalogSize = curr_catalogSize + 4;

        Employee[] newCatalog = new Employee[new_catalogSize];  //array with new catalog capacity

        for(int i = 0; i < curr_catalogSize; i++){  //fills new array
            newCatalog[i] = emplist[i];
        }

        emplist = newCatalog;
    }

    public boolean add(Employee employee) { //check the profile before adding
        //Checking profile
        for(int index = 0; index < emplist.length; index++){
            if(employee.returnProfile().equals(emplist[index].returnProfile())){
                return false;
            }
        }

        //Check for full list
        if(numEmployee == emplist.length){
            grow();
        }

        //add the employee
        for(int index = 0; index < emplist.length; index++) {
            if (emplist[index] == null) {
                emplist[index] = employee;
                return true;
            }
        }
        return false;
    }

    public boolean remove(Employee employee) { //maintain the original sequence
        //Find the employee
        int index = 0;
        for(index = 0; index < emplist.length; index++) {
            if (employee.returnProfile().equals(emplist[index].returnProfile())) break;
        }

        //Check if it was found or not
        if(index == emplist.length) return false;

        //Remove employee
        emplist[index] = null;

        //Fix list
        int previous = index;
        int current = index + 1;
        while(current < emplist.length){
            if(emplist[current] != null){
                emplist[previous] = emplist[current];
                emplist[current] = null;
                previous++;
                current++;
            }
        }
        return true;
    }
    public boolean setHours(Employee employee) { //set working hours for a part time
        //Find the employee
        int index = 0;
        for(index = 0; index < emplist.length; index++) {
            if (employee.returnProfile().equals(emplist[index].returnProfile())) break;
        }

        //Check if it was found or not
        if(index == emplist.length) return false;

        //Change the working hours (Change later)
        Employee currEmp = emplist[index];
        if(currEmp instanceof Parttime){
            Parttime currPart = (Parttime) currEmp;
            currPart.setHoursWorked();
        }

        return false;
    }

    public void processPayments() { //process payments for all employees

    }
    public void print() { //print earning statements for all employees
        for(int index = 0; index < emplist.length; index++){ //
            System.out.println();
        }
    }
    public void printByDepartment() { } //print earning statements by department
    public void printByDate() { } //print earning statements by date hired
}
