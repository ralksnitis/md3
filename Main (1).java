import java.io.IOException;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.PrintWriter;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.Collections;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		String choiseStr, choiseStr1;
		String sourceFile;
		//System.out.println("input file name:");
		//sourceFile = sc.nextLine();
		sourceFile = "db.csv";
		loop: while (true) {
			//System.out.println("input name:");
			choiseStr1 = sc.nextLine();
			String[] arr = choiseStr1.split(" ");
      List<String> list = Arrays.asList(arr);
      choiseStr = list.get(0);
       
			switch (choiseStr) {
			case "print":
      print(sourceFile);
        break;
				
			case "add":
        choiseStr = list.get(1);
        String[] choise = choiseStr.split(";");
        list = Arrays.asList(choise);
        try {
        String on = list.get(0);
        String tw = list.get(1);
        String th = list.get(2);
        String fo = list.get(3);
        String fi = list.get(4);
        String si = list.get(5);
        if(list.size()<7){
          String toadd = (on + ";" + tw + ";" + th + ";" + fo + ";" + fi + ";" + si);
				  add(sourceFile, toadd);
        }
        else{
          System.out.println("wrong field count");
          }
        }
        catch(Exception e) {
          System.out.println("wrong field count");
        }
				break;
			case "del":
        String met = list.get(1);
				del(sourceFile, met);
				break;
			case "edit":
       choiseStr = list.get(1);
        String hah = choiseStr.replace(";", ";k");
        int count = choiseStr.length() - choiseStr.replace(";", "").length();
        choise = hah.split(";");
        list = Arrays.asList(choise);
        try {
        String on = list.get(0);
        String tw = list.get(1);
        String th = list.get(2);
        String fo = list.get(3);
        String fi = list.get(4);
        String si = list.get(5);
        if(count == 5){
          String toadd = (on + ";" + tw + ";" + th + ";" + fo + ";" + fi + ";" + si);
				edit(sourceFile, toadd);
        }
        else{
          System.out.println("wrong field count");
        }
        }
        catch(ArrayIndexOutOfBoundsException exception) {
          System.out.println("wrong field count");
        }
				break;
			case "sort":
				sort(sourceFile);
				break;
			case "find":
        met = list.get(1);
        find(sourceFile, met);
				break;
			case "avg":
				avg(sourceFile);
				break;
			case "exit":
				break loop; 
      default:
        System.out.println("wrong command");
        break;
			}
    }
     sc.close(); 
	}
	public static void print(String sourceFile) throws IOException {
		String str;
    FileReader fr = new FileReader(sourceFile);
    Scanner sc = new Scanner(fr);
	  System.out.println("------------------------------------------------------------");
		System.out.printf("%-4s%-21s%-11s%6s%10s%-8s\n", "ID","City","Date","Days","Price"," Vehicle");
		System.out.println("------------------------------------------------------------");
		 while(sc.hasNextLine()) {
       str = sc.nextLine();
       String[] arr = str.split(";");
       List<String> list = Arrays.asList(arr);
       System.out.printf("%-4s%-21s%-11s%6s%10s%-1s%-8s\n", list.get(0), list.get(1),list.get(2),list.get(3),list.get(4)," ",list.get(5));		      
		 }
    System.out.println("------------------------------------------------------------");
    sc.close();
	}
  
	public static void add(String sourceFile, String toadd ) throws IOException {
    String[] arr = toadd.split(";");
    List<String> list = Arrays.asList(arr);
    try{
      int num = Integer.parseInt(list.get(0));
      if(num>99 && num<1000){ 
	    }
      else{
		    System.out.println("wrong id");
      return;
	    }
    }
    catch(Exception e){
      System.out.println("wrong id");
      return;
    }
    boolean is = false;
    String line, data[];
    FileReader fr = new FileReader(sourceFile);
    BufferedReader br = new BufferedReader(fr);
    while((line = br.readLine()) != null){
      data = line.split(";");
      if((data[0].equalsIgnoreCase(list.get(0)))){
        is = true;
      }
    }
    br.close();
    if (is){
      System.out.println("wrong id");
      return;
    }
    String on= list.get(0);

    String words[]=list.get(1).split("\\s");  
    String tw="";  
    for(String s:words){  
      String fir=s.substring(0,1);  
      String afterfirst=s.substring(1);  
      tw+=fir.toUpperCase()+afterfirst;  
    }  

    String th = list.get(2);
    if(th.matches("(0?[1-9]|[12][0-9]|3[01])\\/(0?[1-9]|1[0-2])\\/([0-9]{4})")){
    }
    else{
      System.out.println("wrong date");
      return;
    }
    
    try{
      Integer.parseInt(list.get(3));
    }catch(Exception e ){
      System.out.println("wrong day count");
      return;
    }
    String fo = list.get(3);
    try {  
      Double.parseDouble(list.get(4));  
    } catch(NumberFormatException e){  
      System.out.println("wrong price");
      return;
    }  
    String fi = list.get(4);
    String si = list.get(5).toUpperCase(); 
    if(si.matches("(?i)\\bPLANE\\b")||si.matches 
   ("(?i)\\bTRAIN\\b")||si.matches("(?i)\\bBUS\\b")||si.matches("(?i)\\bBOAT\\b")){
    }
    else{
      System.out.println("wrong vehicle");
      return;
    }

 FileWriter fw = new FileWriter(sourceFile, true);
    toadd = (on + ";" + tw + ";" + th + ";" + fo + ";" + fi + ";" + si);
    fw.write(toadd + "\n");
    System.out.println("added");
    fw.close();
  }
	public static void del(String sourceFile, String met) throws IOException {
    int pos = 0;
    String split = ";";
    File old = new File(sourceFile);
    File next = new File("db1.csv");
    boolean is = false;
    String line, data[];
    try{
      FileWriter fw = new FileWriter("db1.csv", true);
      BufferedWriter bw = new BufferedWriter(fw);
      PrintWriter pw = new PrintWriter(bw);
      FileReader fr = new FileReader(sourceFile);
      BufferedReader br = new BufferedReader(fr);
      while((line = br.readLine()) != null){
        data = line.split(split);
        if(!(data[pos].equalsIgnoreCase(met))){
          pw.println(line);
        }
        else{
          is = true;
        }
      }
      if (is){
        System.out.println("deleted");
      }
      else{
        System.out.println("wrong id");
      }
      pw.flush();
      pw.close();
      fw.close();
      bw.close();
      fr.close();
      br.close();

      old.delete();
      File oh = new File(sourceFile);
      next.renameTo(oh);
    }
    catch(Exception e){
    }
  }
	
	public static void edit(String sourceFile, String toadd) {
    int pos = 0;
    String split = ";";
    String space = "k";
    File old = new File(sourceFile);
    File next = new File("db1.csv");
    boolean is = false;
    String[] arr = toadd.split(";");
    List<String> list = Arrays.asList(arr);
    String on = list.get(0);
    String tw = list.get(1);
    String th = list.get(2);
    String fo = list.get(3);
    String fi = list.get(4);
    String si = list.get(5);

    String line, data[];
    try{
      FileWriter fw = new FileWriter("db1.csv", true);
      BufferedWriter bw = new BufferedWriter(fw);
      PrintWriter pw = new PrintWriter(bw);
      FileReader fr = new FileReader(sourceFile);
      BufferedReader br = new BufferedReader(fr);
      while((line = br.readLine()) != null){
        data = line.split(split);
        if((data[pos].equalsIgnoreCase(on))){
          int num = Integer.parseInt(list.get(0));
          if(num>99 && num<1000){
	        }
          else{
		        System.out.println("wrong id");
            return;
	        }
          if(list.get(1).equals(space)){
            tw = data[1];
          } 
          else{
            String tw1 = tw.substring(1);
            String s1 = tw1.substring(0, 1).toUpperCase();
            tw = s1 + tw1.substring(1);
          }
          
        String th1 = list.get(2).substring(0,1);
      if(th1.matches("(0?[1-9]|[12][0-9]|3[01])\\/(0?[1-9]|1[0-2])\\/([0-9]{4})")){
        th = th1;
      }
      else if(th.equals(space)){
        th = data[2];
      }  
      else{
        System.out.println("wrong date");
        return;
      }
        fo = list.get(3).substring(1);
        boolean der = true;
          try{
            Integer.parseInt(fo);
            der = true;
          }catch(Exception e ){
            der = false;
          }
          if(der){
          }
          else if(list.get(3).equals(space)){
            fo = data[3];
          }
          else{
            System.out.println("wrong day count");
            return;
          }

        fi = list.get(4).substring(1);
        boolean der1 = true;
          try{
            Double.parseDouble(fi);
            der1 = true;
          }catch(Exception e ){
            der1 = false;
          }
          if(der1){
          }
          else if(list.get(4).equals(space)){
            fi = data[4];
          }
          else{
            System.out.println("wrong price");
            return;
          }

      String si1 = list.get(5).toUpperCase().substring(1);    
      if(si1.matches("(?i)\\bPLANE\\b")||si1.matches 
   ("(?i)\\bTRAIN\\b")||si1.matches("(?i)\\bBUS\\b")||si1.matches("(?i)\\bBOAT\\b")){
        si = si1;
      }
      else if(list.get(5).equals(space)){
        si = data[5];
      }
      else{
        System.out.println("wrong vehicle");
        return;
      } 
        toadd = (on + ";" + tw + ";" + th + ";" + fo + ";" + fi + ";" + si);
        is = true;
        pw.println(toadd);
      }
      else{
        pw.println(line);
      }
    }
      
      if (is){
        System.out.println("changed");
      }
      else{
        System.out.println("wrong id");
      }
      pw.flush();
      pw.close();
      fw.close();
      bw.close();
      fr.close();
      br.close();

      old.delete();
      File oh = new File(sourceFile);
      next.renameTo(oh);
    }
    catch(Exception e){
    }   
	}
	public static void sort(String sourceFile) {
    String split = ";";
    String line, date, data[];
    File old = new File(sourceFile);
    File next = new File("db1.csv");
    List<String> list1 = new ArrayList<String>();
    List<String> list2 = new ArrayList<String>();
    List<String> list3 = new ArrayList<String>();
    try{
      FileWriter fw = new FileWriter("db1.csv", true);
      BufferedWriter bw = new BufferedWriter(fw);
      PrintWriter pw = new PrintWriter(bw);
      FileReader fr = new FileReader(sourceFile);
      BufferedReader br = new BufferedReader(fr);
      int j = 0;
      while((line = br.readLine()) != null){
        data = line.split(split);
        date = data[2];
        list3.add(line);
        j++;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        list1.add(sdf2.format(sdf.parse(date)));
        pw.println(line);
       } 
      
      Collections.sort(list1);
      
      for(int i = 0; i < list1.size();i++){
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
      list2.add(sdf.format(sdf2.parse(list1.get(i))));
      if(list1.get(i) == null){
        break;
      }
      }
      fw.close();
      fr.close();
      System.out.println(list2);
      System.out.println(list3);
      fw = new FileWriter("db1.csv", true);
      bw = new BufferedWriter(fw);
      pw = new PrintWriter(bw);
      fr = new FileReader(sourceFile);
      br = new BufferedReader(fr);
      while((line = br.readLine()) != null){
        data = line.split(split);
        date = data[2];
        for (int i=0; i<list2.size();i++){
          if(date.equals(list2.get(i))){
            pw.println(list3.get(i));
          }
        }
      }
      pw.flush();
      pw.close();
      fw.close();
      bw.close();
      fr.close();
      br.close();

      old.delete();
      File oh = new File(sourceFile);
      next.renameTo(oh);
      System.out.println("sorted");
    }
    catch(Exception e){
    }   
	}
	public static void find(String sourceFile, String met) throws IOException {
		String str;
    FileReader fr = new FileReader(sourceFile);
    Scanner sc = new Scanner(fr);
    double price=Double.parseDouble(met);
	  System.out.println("------------------------------------------------------------");
		System.out.printf("%-4s%-21s%-11s%6s%10s%-8s\n", "ID","City","Date","Days","Price"," Vehicle");
		System.out.println("------------------------------------------------------------");
		while(sc.hasNextLine()) {
      str = sc.nextLine();
      String[] arr = str.split(";");
      List<String> list = Arrays.asList(arr);
      double d=Double.parseDouble(list.get(4));
      if(d <= price){
        System.out.printf("%-4s%-21s%-11s%6s%10s%-1s%-8s\n", list.get(0), list.get(1),list.get(2),list.get(3),list.get(4)," ",list.get(5));	
       }	      
		}
    System.out.println("------------------------------------------------------------");
    sc.close();
	}
	public static void avg(String sourceFile) throws IOException {
		String str;
    Double sum=0.00;
    int count=0;
    FileReader fr = new FileReader(sourceFile);
    Scanner sc = new Scanner(fr);
		while(sc.hasNextLine()) {
      str = sc.nextLine();
      String[] arr = str.split(";");
      List<String> list = Arrays.asList(arr);
      double d=Double.parseDouble(list.get(4));
      sum = sum + d;
      count++;
		}
    System.out.printf("average="+"%.2f\n",sum/count);
    sc.close();
	}
}