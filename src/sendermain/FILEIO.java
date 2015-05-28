package sendermain;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FILEIO {

    public List<String> errors = new ArrayList<>();
    public List<String> lines = new ArrayList<>();
    private LocalDateTime currentdatetime = LocalDateTime.now();

    public List Emails(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("D:\\Общая\\Отправка оборотов\\Emails.ini"));
            String line;
            lines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                if (line.matches("^[a-zA-Zа-яА-ЯёЁ0-9-_]*@[a-zA-Zа-яА-ЯёЁ0-9-_.,]*$")){
                    lines.add(line);
                    System.out.println("Этот адрес прошел проверку! :" + line +". Он включен в рассылку "+currentdatetime);
                    System.out.println(lines);
                } else {
                    errors.add(line);
                    SomethingGoesWrong(errors);
                    System.out.println("Один из адресов НЕ прошел проверку! :" + line +". Он НЕ будет включен в рассылку "+currentdatetime);
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Ошибка:"+e);
        }
        SomethingGoesRight(lines);
        errors.clear();
        return lines;
    }

    public void SomethingGoesWrong (List errors){
        try {
            FileWriter writer = new FileWriter("D:\\Общая\\Отправка оборотов\\Errors.dat",true);
            for (Object vah : errors) {
                String hav = vah.toString();
                writer.append("Этот адрес НЕ прошел проверку! :" + hav + ". Он НЕ включен в рассылку " + currentdatetime+"\n");
                System.out.println("Этот адрес НЕ прошел проверку! :" + hav + ". Он НЕ включен в рассылку " + currentdatetime +"\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл Errors.dat: "+e+" \n"+currentdatetime);
        }
    }

    public void SomethingGoesRight (List lines){
        try {
            FileWriter writer = new FileWriter("D:\\Общая\\Отправка оборотов\\Log.dat",true);
            for (Object vah : lines) {
                String hav = vah.toString();
                writer.append("Этот адрес прошел проверку! : " + hav + ". И по нему была выслана почта в " + currentdatetime+"\n");
                System.out.println("Этот адрес прошел проверку! :" + hav + ". И по нему была выслана почта в" + currentdatetime +"\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл Log.dat: "+e+" \n"+currentdatetime);
        }
    }
}
