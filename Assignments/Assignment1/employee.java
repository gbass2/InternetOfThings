package employee;

// Class to hold the attributes of an employee
class Employee {
    private int hoursWorked;
    private double basePay;
    private double totalPay;
    private String name;

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
