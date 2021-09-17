package foocorp;

// Class to hold the attributes of an employee
public class Employee {
    private int hoursWorked; // Amount of hours an employee has worked
    private double basePay; // Base hourly wage of the employee
    private double totalPay; // Total pay which is base amount made + overtime
    private String name; 
    private boolean error; // Denotes if the employees base pay is below 8 or if hours worked is above 60

    public Employee(double basePay, int hoursWorked, String name, double totalPay) {
        setBasePay(basePay);
        setHoursWorked(hoursWorked);
        setName(name);
        setTotalPay(totalPay);
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public void setBasePay(double basePay) {
        this.basePay = basePay;
    }

    public void setTotalPay(double totalPay) {
        this.totalPay = totalPay;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public double getBasePay() {
        return basePay;
    }

    public double getTotalPay() {
        return totalPay;
    }

    public String getName() {
        return name;
    }
}
