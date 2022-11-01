import java.util.HashMap;
import java.util.Scanner;

public class YearReportGlobalInfo {

    private static Integer currentMonth = 0;

    private Integer yearReportsCount = 0; //счетчик считанных месяцев

    public HashMap<Integer, YearReport> yearReports = new HashMap<>();

    public static int getCurrentMonth() {
        return currentMonth;
    }

    public Integer getTotalProfit() {
        Integer totalProfit = getTotalIncome() - getTotalExpense();
        return totalProfit;
    }

    public Integer getTotalIncome() {
        Integer totalIncome = 0;
        for (Integer month : yearReports.keySet()) {
            totalIncome += yearReports.get(month).getSumIncomeInMonth();
        }
        return totalIncome;
    }

    public Integer getTotalExpense() {
        Integer totalExpense = 0;
        for (Integer month : yearReports.keySet()) {
            totalExpense += yearReports.get(month).getSumExpenseInMonth();
        }
        return totalExpense;
    }

    public Integer getYearReportsCount() {
        return yearReportsCount;
    }

    private Double getAverageIncome() {
        Double averageIncome = Double.valueOf(getTotalIncome()) / yearReportsCount;
        return averageIncome;
    }

    private Double getAverageExpense() {
        Double averageExpense = Double.valueOf(getTotalExpense()) / yearReportsCount;
        return averageExpense;
    }

    private void setCurrentMonth(Integer currentMonth) {
        YearReportGlobalInfo.currentMonth = currentMonth;
    }

    public void addYearReport(Integer month, Integer amount, boolean isExpense) {
        YearReport newYearReport;
        setCurrentMonth(month);

        if (yearReports.containsKey(month)) {
            newYearReport = yearReports.get(month);
            if (newYearReport.getIncomes().size() >= 1 && (!isExpense) || (newYearReport.getExpenses().size() >= 1 && isExpense)) {
                byte userCommand = chooseAction(amount);
                if (userCommand == 1) { //Добавить еще одну сумму за месяц
                    if (isExpense) {
                        newYearReport.addExpense(amount);
                    } else {
                        newYearReport.addIncome(amount);
                    }
                    yearReports.put(month, newYearReport);
                } //иначе пропускаем значение
                else {
                    System.out.println("Сумма " + amount + " пропущена");
                }
            } else {
                if (isExpense) {
                    newYearReport.addExpense(amount);
                } else {
                    newYearReport.addIncome(amount);
                }
                yearReports.put(month, newYearReport);
            }
        } else {
            yearReportsCount += 1; //счетчик считанных месяцев
            newYearReport = new YearReport();
            if (isExpense) {
                newYearReport.addExpense(amount);
            } else {
                newYearReport.addIncome(amount);
            }
            yearReports.put(month, newYearReport);
        }
    }

    //Byte сделал чтобы памяти меньше жрал
    private byte chooseAction(Integer amount) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("В месяце " + getCurrentMonth() + " годового отчета обнаружена новая сумма: " + amount + "\nВыберите действие: ");
        System.out.println("1 - добавить в месяц");
        System.out.println("2 - пропустить");

        byte command = 0;
        boolean isCorrectInput = true;

        while (isCorrectInput) {
            String inputInfo = scanner.nextLine();
            if (inputInfo.equalsIgnoreCase("1")) {
                command = Byte.parseByte(inputInfo);
                isCorrectInput = false;
            } else if (inputInfo.equalsIgnoreCase("2")) {
                command = Byte.parseByte(inputInfo);
                isCorrectInput = false;
            } else {
                System.out.println("Введено некорректное значение. Попробуйте еще раз");
            }
        }
        return command;
    }

    public void printYearReport() {
        System.out.println("В отчете содержались " + getYearReportsCount() + " месяцев");
        System.out.println("Общий годовой доход " + getTotalIncome());
        System.out.println("Общий годовой расход " + getTotalExpense());
        System.out.println("Прибыль: " + getTotalProfit());
        System.out.println("Средний месячный доход: " + getAverageIncome());
        System.out.println("Средний месячный расход: " + getAverageExpense());
        System.out.println();
    }
}
