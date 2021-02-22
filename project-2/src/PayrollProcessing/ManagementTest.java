package PayrollProcessing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 Junit class to test the Management class methods
 @author Muhammad Faizan Saiyed, Michael Neustater
 */

class ManagementTest {

    @Test
    void calculatePayment() {
        Date dateHired = new Date("7/31/2001");
        Employee bob = new Management("Bob", "CS", dateHired, 10000, 1);
        Employee bob2 = new Management("Bob2", "CS", dateHired, 50000, 2);
        Employee bob3 = new Management("Bob3", "CS", dateHired, 25000, 3);
        Employee bob4 = new Management("Bob4", "CS", dateHired, 35000, 4);
        Employee bob5 = new Management("Bob5", "CS", dateHired, 45000, 0);

        bob.calculatePayment();
        bob2.calculatePayment();
        bob3.calculatePayment();
        bob4.calculatePayment();
        bob5.calculatePayment();

        assertTrue(bob.toString().equals("Bob::CS::7/31/2001::Payment $576.92::FULL TIME::Annual Salary $10,000.00::Manager Compensation $192.31"));
        assertTrue(bob2.toString().equals("Bob2::CS::7/31/2001::Payment $2,288.46::FULL TIME::Annual Salary $50,000.00::Director Compensation $365.38"));
        assertTrue(bob3.toString().equals("Bob3::CS::7/31/2001::Payment $1,423.08::FULL TIME::Annual Salary $25,000.00::Department Head Compensation $461.54"));
        assertTrue(bob4.toString().equals("Bob4::CS::7/31/2001::Payment $1,346.15::FULL TIME::Annual Salary $35,000.00"));
        assertTrue(bob5.toString().equals("Bob5::CS::7/31/2001::Payment $1,730.77::FULL TIME::Annual Salary $45,000.00"));

    }

    @Test
    void testToString() {
        Date dateHired = new Date("7/31/2001");

        Employee bob = new Management("Bob", "CS", dateHired, 10000, 1);
        Employee bob2 = new Management("Bob2", "CS", dateHired, 50000, 2);
        Employee bob3 = new Management("Bob3", "CS", dateHired, 25000, 3);
        Employee bob4 = new Management("Bob4", "CS", dateHired, 35000, 4);
        Employee bob5 = new Management("Bob5", "CS", dateHired, 45000, 0);

        assertTrue(bob.toString().equals("Bob::CS::7/31/2001::Payment $0.00::FULL TIME::Annual Salary $10,000.00::Manager Compensation $192.31"));
        assertTrue(bob2.toString().equals("Bob2::CS::7/31/2001::Payment $0.00::FULL TIME::Annual Salary $50,000.00::Director Compensation $365.38"));
        assertTrue(bob3.toString().equals("Bob3::CS::7/31/2001::Payment $0.00::FULL TIME::Annual Salary $25,000.00::Department Head Compensation $461.54"));
        assertTrue(bob4.toString().equals("Bob4::CS::7/31/2001::Payment $0.00::FULL TIME::Annual Salary $35,000.00"));
        assertTrue(bob5.toString().equals("Bob5::CS::7/31/2001::Payment $0.00::FULL TIME::Annual Salary $45,000.00"));

    }

    @Test
    void testEquals() {
        Date dateHired = new Date("7/31/2001");
        Date dateHiredDiff = new Date("9/30/2000");
        Employee bob = new Management("Bob", "CS", dateHired, 10000, 1);
        Employee bobRoleDiff = new Management("Bob", "CS", dateHired, 10000, 2);
        Employee bobMoneyDiff = new Management("Bob", "CS", dateHired, 25000, 1);
        Employee bobSame = new Management("Bob", "CS", dateHired, 10000, 1);
        Employee bobNameDiff = new Management("BobTwo", "CS", dateHired, 10000, 1);
        Employee bobDepDiff = new Management("Bob", "IT", dateHired, 10000, 1);
        Employee bobDateDiff = new Management("Bob", "CS", dateHiredDiff, 10000, 1);
        Employee bobPartTime = new Parttime("Bob", "CS", dateHired, 10000);

        assertTrue(bob.equals(bobSame));
        assertTrue(bob.equals(bobMoneyDiff));
        assertFalse(bob.equals(bobRoleDiff));
        assertFalse(bob.equals(bobNameDiff));
        assertFalse(bob.equals(bobDepDiff));
        assertFalse(bob.equals(bobDateDiff));
        assertFalse(bob.equals(bobPartTime));
    }
}