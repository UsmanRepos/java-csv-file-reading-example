/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvfilereader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usman_Aslam
 */
class Student
{
    
    String[] record;
    String name;
    String registration;
    String date;
    String status;
    String arrivaltime;
    String lastseen;
    String noOfJoined;
    String details;
    
    Student()
    {
        record = new String[10];
        name = "";
        date = "";
        status = "";
        
    }
    public void setData(Scanner filereader)
    {
        name = record[0];
        status = record[1];
        
        if(status.equals(" \"\"")) 
        {
                System.out.println("Abscent");  
        } 
        else
        {    
            arrivaltime = record[4];
            lastseen = record[5];
            noOfJoined = record[7]; 
        }
        
        
    }
    
    public void getData()
    {
        System.out.printf("%30s %10s %17s %15s %10s %n",name,status,arrivaltime,lastseen,noOfJoined);
    }
}

public class CsvFileReader {
    ArrayList<Student> stu;
    File myfile;
    Scanner filereader;
    static Scanner cin;
    int count;
    CsvFileReader()
    {
        try 
        {
            cin = new Scanner(System.in);
            stu = new ArrayList<Student>();
            myfile = new File("CSVFILE.csv");
            filereader = new Scanner(myfile);
            count = 0;
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CsvFileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void displayData()
    {
        System.out.printf("    %25s         "
                + "%s     %s       %s    %s  %n","Name","Status","ArrivalTime","LastSeen","NoOfJoined");
        for(int i = 0; i<count; i++)
        {
            stu.get(i).getData();
            System.out.println();
        }
    }
    public void readFile()
    {
        excludeHeaders();
        while(filereader.hasNextLine())
        {
            String line = filereader.nextLine();
            if(line.isEmpty())
                break;
            stu.add(new Student());
            stu.get(count).record = line.split(",");
            stu.get(count).setData(filereader);
            count++;
        }
    }
    public void checkRecord(char op)
    {
        System.out.println("Enter Name of Student: ");
        String name = cin.nextLine();
        boolean flag = false;
        
        for(int i = 0; i<count; i++)
        {
            if(stu.get(i).name.equals("\"" + name + "\""))
            {
                switch(op)
                {
                    case'p':
                        if(stu.get(i).status.equals(" \"\""))
                        {
                            System.out.println("Student is Abscent..");   
                        }
                        else
                            System.out.println("Student is present..");
                        break;
                    case'a':
                        if(stu.get(i).status.equals(" \"\""))
                        {
                            System.out.println("Student is Abscent..");   
                        }
                        else
                            System.out.println("Arrival Time : " + stu.get(i).arrivaltime);
                        break;
                    case 'l':
                        if(stu.get(i).status.equals(" \"\""))
                        {
                            System.out.println("Student is Abscent..");   
                        }
                        else
                            System.out.println("Last Seen: " + stu.get(i).lastseen);
                        break;
                    case'n':
                        if(stu.get(i).status.equals(" \"\""))
                        {
                            System.out.println("Student is Abscent..");   
                        }
                        else
                            System.out.println("No  of Times Joined: " + stu.get(i).noOfJoined);
                        break;      
                
                }
                break;
            }
        }
        
        
    }
    public static void main(String[] args) {
        CsvFileReader obj = new CsvFileReader();
        
        while(true)
        {
            System.out.println("----------(MAIN MENU)---------\n");
            System.out.println("1) READ FROM FILE \n");
            System.out.println("2) DISPLAY ALL RECORD \n");
            System.out.println("3) CHECK STUDENT DATA \n");
            System.out.println("4) EXIT \n\n");
            
            System.out.println("Enter your Choice: ");
            char ch = cin.next().charAt(0);
            
            switch(ch)
            {
                case'1':
                    obj.readFile();
                    System.out.println("Successfully Read From File..");
                    break;
                case'2':
                    obj.displayData();
                    break;
                case'3':
                   while(true)
                   {
                        System.out.println("----------(SUB MENU)---------\n");
                        System.out.println("1) CHECK PRESENT \n");
                        System.out.println("2) CHECK ARRIVAL TIME \n");
                        System.out.println("3) CHECK LAST SEEN \n");
                        System.out.println("4) NO OF TIME JOINED \n");
                        System.out.println("5) Exit \n\n");

                        System.out.println("Enter your Choice: ");
                        ch = cin.next().charAt(0);
                        cin.nextLine();
                        
                        switch(ch)
                        {
                            case'1':
                                obj.checkRecord('p');
                                break;
                            case'2':
                                obj.checkRecord('a');
                                break;
                            case'3':
                                obj.checkRecord('l');
                                break;
                            case'4':
                                obj.checkRecord('n');
                                break;
                            default:
                                break;  
                            
                        }
                        if(ch == '5')
                            break;
                   }
                   break;
                case'4':
                    System.exit(-1);
                    break;
                default:
                    System.out.println("Invalid Input...");
            }
            
        }
        
        
   
    }
    public void excludeHeaders()
    {
        System.out.println(filereader.nextLine());
        System.out.println(filereader.nextLine());
        System.out.println(filereader.nextLine());
        System.out.println(filereader.nextLine());
        
    }
    
}
