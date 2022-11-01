import java.util.ArrayList;
public class YearReport {

    private int sumIncomeInMonth;
    private int sumExpenseInMonth;

    private ArrayList<Integer> incomes = new ArrayList<>();
    private ArrayList<Integer> expenses = new ArrayList<>();


    public ArrayList<Integer> getIncomes() {
        return incomes;
    }

    public ArrayList<Integer> getExpenses() {
        return expenses;
    }

    private void increaseSumExpenseInMonth(int sumExpenseInMonth) {
        this.sumExpenseInMonth += sumExpenseInMonth;
    }

    public int getSumExpenseInMonth() {
        return sumExpenseInMonth;
    }

    private void increaseSumIncomeInMonth(int sumIncomeInMonth) {
        this.sumIncomeInMonth += sumIncomeInMonth;
    }

    public int getSumIncomeInMonth() {
        return sumIncomeInMonth;
    }

    public void addExpense(Integer amount) {
        expenses.add(amount);
        increaseSumExpenseInMonth(amount);
        System.out.println("Добавлена сумма " + amount +
                " за " + YearReportGlobalInfo.getCurrentMonth() + " месяц. Сумма расхода : " + getSumExpenseInMonth());
        System.out.println(expenses + " - список расходов за месяц");
        System.out.println();
    }

    public void addIncome(Integer amount) {
        incomes.add(amount);
        increaseSumIncomeInMonth(amount);
        System.out.println("Добавлена сумма " + amount + " за " + YearReportGlobalInfo.getCurrentMonth() + " месяц. Сумма дохода: " + getSumIncomeInMonth());
        System.out.println(incomes + " - список доходов за месяц");
        System.out.println();
    }
}
