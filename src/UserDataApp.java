import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UserDataApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные в формате: Фамилия Имя Отчество датарождения номертелефона пол");
//        Иванов Иван Иванович 01.01.1990 1234567890 M
//        Иванов Петр Иванович 01.01.1990 1234567890 M
//        Сидоров Алексей Владимирович 15.05.1985 9876543210 M
        String input = scanner.nextLine();
        String[] data = input.split(" ");


        if (data.length != 6){
            throw new IllegalArgumentException("Ошибка: неверное количество данных.");
        }


        String lastName = data[0];
        String firstName = data[1];
        String middleName = data[2];
        String dateOfBirth;
        long phoneNumber;
        char gender;


        if (!isAlpha(lastName) || !isAlpha(firstName) || !isAlpha(middleName)) {
            System.out.println("Ошибка: ФИО должно состоять только из букв.");
            return;
        }


        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.mm.yyyy");
            Date parseDate = simpleDateFormat.parse(data[3]);
             dateOfBirth = simpleDateFormat.format(parseDate);
        } catch (ParseException e) {
            System.out.println("Ошибка: неверный формат даты рождения.Ожидается формат dd.mm.yyyy(день, месяц,год).");
            return;
        }


        try {
            phoneNumber = Long.parseLong(data[4]);
        }catch(NumberFormatException r){
            System.out.println("Ошибка: номер телефона должен быть целым числом.");
            return;
        }


        try{
            if(data[5].length() != 1 || !data[5].equalsIgnoreCase("f") && !data[5].equalsIgnoreCase("m")){
                throw new IllegalArgumentException("Ошибка: пол должен быть задан символом 'f' или 'm'.");
            }
            gender = data[5].charAt(0);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return;
        }


        String fileName = lastName + ".txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.write(lastName + " " + firstName + " " + middleName + " " + dateOfBirth + " " + phoneNumber + " " + gender);
            writer.newLine();
            writer.flush();
            System.out.println("Данные успешно записаны в файл " + fileName);
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
            e.printStackTrace();
        }

    }
    private static boolean isAlpha(String str) {
        return str.matches("[а-яА-Я]+");
    }
}