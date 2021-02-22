package PayrollProcessing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 Junit class to test the Payroll class methods
 @author Muhammad Faizan Saiyed, Michael Neustater
 */

class CompanyTest {

    @Test
    void add() {
        Company company = new Company();
        Employee bobFullTime = new Fulltime("BobFT", "CS", new Date("7/31/2001"), 10000);
        Employee bobManagement = new Management("BobM", "IT", new Date("10/20/1989"), 15000, 1);
        Employee bobPartTime = new Parttime("BobPT", "ECE", new Date("8/20/2010"), 10);
        Employee bobPartTime2 = new Parttime("BobPT2", "IT", new Date("9/30/2000"), 20);
        Employee bobPartTime3 = new Parttime("BobPT3", "CS", new Date("10/20/2008"), 15);

        assertTrue(company.add(bobFullTime));
        assertTrue(company.add(bobManagement));
        assertTrue(company.add(bobPartTime));
        assertTrue(company.add(bobPartTime2));
        assertTrue(company.add(bobPartTime3));

        assertFalse(company.add(bobFullTime));
        assertFalse(company.add(bobManagement));
        assertFalse(company.add(bobPartTime));
        assertFalse(company.add(bobPartTime2));
        assertFalse(company.add(bobPartTime3));

    }

    @Test
    void remove() {
        Company company = new Company();
        Employee bobFullTime = new Fulltime("BobFT", "CS", new Date("7/31/2001"), 10000);
        Employee bobManagement = new Management("BobM", "IT", new Date("10/20/1989"), 15000, 1);
        Employee bobPartTime = new Parttime("BobPT", "ECE", new Date("8/20/2010"), 10);
        Employee bobPartTime2 = new Parttime("BobPT2", "IT", new Date("9/30/2000"), 20);
        Employee bobPartTime3 = new Parttime("BobPT3", "CS", new Date("10/20/2008"), 15);

        assertFalse(company.remove(bobFullTime));
        assertFalse(company.remove(bobManagement));
        assertFalse(company.remove(bobPartTime));
        assertFalse(company.remove(bobPartTime2));
        assertFalse(company.remove(bobPartTime3));

        company.add(bobFullTime);
        company.add(bobManagement);
        company.add(bobPartTime);
        company.add(bobPartTime2);
        company.add(bobPartTime3);

        assertTrue(company.remove(bobFullTime));
        assertTrue(company.remove(bobManagement));
        assertTrue(company.remove(bobPartTime));
        assertTrue(company.remove(bobPartTime2));
        assertTrue(company.remove(bobPartTime3));

        assertFalse(company.remove(bobFullTime));
        assertFalse(company.remove(bobManagement));
        assertFalse(company.remove(bobPartTime));
        assertFalse(company.remove(bobPartTime2));
        assertFalse(company.remove(bobPartTime3));
    }

    @Test
    void setHours() {
        Company company = new Company();
        Employee bobFullTime = new Fulltime("BobFT", "CS", new Date("7/31/2001"), 10000);
        Employee bobManagement = new Management("BobM", "IT", new Date("10/20/1989"), 15000, 1);
        Employee bobPartTime = new Parttime("BobPT", "ECE", new Date("8/20/2010"), 10);
        Employee bobPartTime2 = new Parttime("BobPT2", "IT", new Date("9/30/2000"), 20);
        Employee bobPartTime3 = new Parttime("BobPT3", "CS", new Date("10/20/2008"), 15);

        company.add(bobFullTime);
        company.add(bobManagement);
        company.add(bobPartTime);
        company.add(bobPartTime2);
        company.add(bobPartTime3);

        assertTrue(company.setHours(bobPartTime));
        assertTrue(company.setHours(bobPartTime2));
        assertTrue(company.setHours(bobPartTime3));
        assertFalse(company.setHours(bobFullTime));
        assertFalse(company.setHours(bobManagement));

    }

    @Test
    void processPayments() {
        Company company = new Company();
        Employee bobFullTime = new Fulltime("BobFT", "CS", new Date("7/31/2001"), 10000);
        Employee bobManagement = new Management("BobM", "IT", new Date("10/20/1989"), 15000, 1);

        company.add(bobFullTime);
        company.add(bobManagement);

        company.processPayments();

        assertTrue(bobFullTime.toString().equals("BobFT::CS::7/31/2001::Payment $384.62::FULL TIME::Annual Salary $10,000.00"));
        assertTrue(bobManagement.toString().equals("BobM::IT::10/20/1989::Payment $769.23::FULL TIME::Annual Salary $15,000.00::Manager Compensation $192.31"));

    }

    @Test
    void findEmployee(){
        Company company = new Company();
        Employee bobFullTime = new Fulltime("BobFT", "CS", new Date("7/31/2001"), 10000);
        Employee bobManagement = new Management("BobM", "IT", new Date("10/20/1989"), 15000, 1);
        Employee bobPartTime = new Parttime("BobPT", "ECE", new Date("8/20/2010"), 10);
        Employee bobPartTime2 = new Parttime("BobPT2", "IT", new Date("9/30/2000"), 20);
        Employee bobPartTime3 = new Parttime("BobPT3", "CS", new Date("10/20/2008"), 15);

        company.add(bobFullTime);
        company.add(bobManagement);
        company.add(bobPartTime);
        company.add(bobPartTime2);
        company.add(bobPartTime3);

        assertTrue(company.findEmployee("BobFT", "CS", new Date("7/31/2001")).equals(bobFullTime));
        assertTrue(company.findEmployee("BobM", "IT", new Date("10/20/1989")).equals(bobManagement));
        assertTrue(company.findEmployee("BobPT", "ECE", new Date("8/20/2010")).equals(bobPartTime));
        assertTrue(company.findEmployee("BobPT2", "IT", new Date("9/30/2000")).equals(bobPartTime2));
        assertTrue(company.findEmployee("BobPT3", "CS", new Date("10/20/2008")).equals(bobPartTime3));


    }



    @Test
    void isEmpty() {
        Company company = new Company();
        Employee bobFullTime = new Fulltime("BobFT", "CS", new Date("7/31/2001"), 10000);
        Employee bobManagement = new Management("BobM", "IT", new Date("10/20/1989"), 15000, 1);
        Employee bobPartTime = new Parttime("BobPT", "ECE", new Date("8/20/2010"), 10);
        Employee bobPartTime2 = new Parttime("BobPT2", "IT", new Date("9/30/2000"), 20);
        Employee bobPartTime3 = new Parttime("BobPT3", "CS", new Date("10/20/2008"), 15);

        assertTrue(company.isEmpty());

        company.add(bobFullTime);
        company.add(bobManagement);
        company.add(bobPartTime);
        company.add(bobPartTime2);
        company.add(bobPartTime3);

        assertFalse(company.isEmpty());
    }
}