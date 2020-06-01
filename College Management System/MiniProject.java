package miniproject;

import java.util.*;
import java.sql.*;

class FacultySQL
{
    public void displayAll(Statement st) throws Exception
    {
        String select = "select * from faculty";
        String fname, dept, desig;
        int id, age;
        ResultSet rs = st.executeQuery(select);
        while(rs.next())
        {
            id = rs.getInt("id");
            fname = rs.getString("fname");
            dept = rs.getString("dept");
            age = rs.getInt("age");
            desig = rs.getString("desig");

            System.out.println("\nID: " + id);
            System.out.println("Name: " + fname);
            System.out.println("Dept: " + dept);
            System.out.println("Age: " + age);
            System.out.println("Designation: " + desig);
        }
    }
    
    public void insertRecord(PreparedStatement pst) throws Exception
    {
        String fname, dept, desig;
        int id, age;
        Scanner in = new Scanner(System.in);
        
        System.out.println("Enter faculty details:");
        System.out.print("\nID: ");
        id = in.nextInt();
        System.out.print("Name: ");
        fname = in.next();
        System.out.print("Dept: ");
        dept = in.next();
        System.out.print("Age: ");
        age = in.nextInt();
        System.out.print("Designation: ");
        desig = in.next();
        
        pst.setInt(1,id);
        pst.setString(2,fname);
        pst.setString(4,dept);
        pst.setInt(3,age);
        pst.setString(5,desig);
        
        
        int count = pst.executeUpdate();
    }
    
    public void deleteRecord(PreparedStatement pst, Statement st) throws Exception
    {
        Scanner in = new Scanner(System.in);
        
        int id;
        System.out.print("Enter ID of faculty who's record is to be deleted: ");
        id = in.nextInt();
        
        if(isPresent(st,id) == 1)
        {
            pst.setInt(1,id);
        }    
        else
        {
            System.out.println("Faculty not found.");
        }
        
        int count = pst.executeUpdate();
    }
    
    public int isPresent(Statement st, int id) throws Exception
    {
        String search = "select id from faculty where id=";
        ResultSet rs = st.executeQuery(search+id);
        if(rs.next())
        {
            return 1;
        }
        else
        {
            return 0;
        }
        
    }
    
    public void updateRecord(PreparedStatement pst, Statement st) throws Exception
    {
        String fname, dept, desig;
        int id, age, Sid;
        Scanner in = new Scanner(System.in);
        
        System.out.print("Enter faculty ID who's details are to be updated: ");
        Sid = in.nextInt();
        
        if(isPresent(st,Sid) == 1)
        {
            System.out.print("ID: ");
            id = in.nextInt();
            System.out.print("Name: ");
            fname = in.next();
            System.out.print("Dept: ");
            dept = in.next();
            System.out.print("Age: ");
            age = in.nextInt();
            System.out.print("Designation: ");
            desig = in.next();
            
            pst.setInt(1,id);
            pst.setString(2,fname);
            pst.setInt(3,age);
            pst.setString(4,dept);
            pst.setString(5,desig);
            pst.setInt(6,Sid);
            
            int count = pst.executeUpdate();
        }
        else
        {
            System.out.println("Faculty not found.");
        }
    }
}

class StudentSQL
{
    public void insertRecord(PreparedStatement pst, Add add) throws Exception
    {
        String sname, dept;
        int rollno, age;
        float attendance, marks;
        Scanner in = new Scanner(System.in);
        //Add add = new Add();
        String temp;
        
        /*
        System.out.println("Enter student details:");
        System.out.print("\nRoll No: ");
        rollno = in.nextInt();
        System.out.print("Name: ");
        sname = in.next();
        System.out.print("Dept: ");
        dept = in.next();
        System.out.print("Age: ");
        age = in.nextInt();
        System.out.print("Attendance: ");
        attendance = in.nextFloat();
        System.out.print("Ave Marks: ");
        marks = in.nextFloat();
        */
        
        temp = new String(add.rollnofield.getText());
        rollno = Integer.parseInt(temp);
        //System.out.println(rollno);
        
        sname = add.studentnamefield.getText();
        
        dept = add.deptfield.getText();
        
        temp = new String(add.agefield.getText());
        age = Integer.parseInt(temp);
        
        temp = new String(add.attendancefield.getText());
        attendance = Float.parseFloat(temp);
        
        temp = new String(add.gpafield.getText());
        marks = Float.parseFloat(temp);
        
        pst.setInt(1,rollno);
        pst.setString(2,sname);
        pst.setString(4,dept);
        pst.setInt(3,age);
        pst.setFloat(5,attendance);
        pst.setFloat(6,marks);
        
        
        int count = pst.executeUpdate();
    }
    
    public void deleteRecord(PreparedStatement pst, Statement st) throws Exception
    {
        Scanner in = new Scanner(System.in);
        
        int rollno;
        System.out.print("Enter roll number of student who's record is to be deleted: ");
        rollno = in.nextInt();
        
        if(isPresent(st,rollno) == 1)
        {
            pst.setInt(1,rollno);
        }    
        else
        {
            System.out.println("Student not found.");
        }
        
        int count = pst.executeUpdate();
    }
    
    public int isPresent(Statement st, int rollno) throws Exception
    {
        String search = "select rollno from student where rollno=";
        ResultSet rs = st.executeQuery(search+rollno);
        if(rs.next())
        {
            return 1;
        }
        else
        {
            return 0;
        }
        
    }
    
    public void updateRecord(PreparedStatement pst, Statement st) throws Exception
    {
        String sname, dept;
        int rollno, age, Srollno;
        float attendance, marks;
        Scanner in = new Scanner(System.in);
        
        System.out.print("Enter student rollno who's details are to be updated: ");
        Srollno = in.nextInt();
        
        if(isPresent(st,Srollno) == 1)
        {
            System.out.print("Rollno: ");
            rollno = in.nextInt();
            System.out.print("Name: ");
            sname = in.next();
            System.out.print("Dept: ");
            dept = in.next();
            System.out.print("Age: ");
            age = in.nextInt();
            System.out.print("Attendance: ");
            attendance = in.nextFloat();
            System.out.print("Ave Marks: ");
            marks = in.nextFloat();
            
            pst.setInt(1,rollno);
            pst.setString(2,sname);
            pst.setInt(3,age);
            pst.setString(4,dept);
            pst.setFloat(5,attendance);
            pst.setFloat(6,marks);
            pst.setInt(7,Srollno);
            
            int count = pst.executeUpdate();
        }
        else
        {
            System.out.println("Student not found.");
        }
    }
    
    public void displayAll(Statement st) throws Exception
    {
        String select = "select * from student";
        String sname, dept;
        int rollno, age;
        float attendance, marks;
        ResultSet rs = st.executeQuery(select);
        while(rs.next())
        {
            rollno = rs.getInt("rollno");
            sname = rs.getString("sname");
            dept = rs.getString("dept");
            age = rs.getInt("age");
            attendance = rs.getFloat("attendance");
            marks = rs.getFloat("marks");

            System.out.println("\nRoll No: " + rollno);
            System.out.println("Name: " + sname);
            System.out.println("Dept: " + dept);
            System.out.println("Age: " + age);
            System.out.println("Attendance: " + attendance);
            System.out.println("Ave Marks: " + marks);
        }
    }
}

public class MiniProject
{
    
    public static void main(String[] args) throws Exception
    {
        Scanner in = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/mini";
        String uname = "root";
        String pass = "pass";
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(url,uname,pass);
        
        
        //Student data
        StudentSQL std = new StudentSQL();
        String sselect = "select * from student";
        String sinsert = "insert into student values (?,?,?,?,?,?)";
        String sdelete = "delete from student where rollno=?";
        String supdate = "update student set rollno=?,sname=?,age=?,dept=?,attendance=?,marks=? where rollno=?";
        String sname, dept;
        int rollno, age;
        float attendance, marks;
        Statement st = con.createStatement();
        PreparedStatement pstIn = con.prepareStatement(sinsert);
        PreparedStatement pstDel = con.prepareStatement(sdelete);
        PreparedStatement pstUpd = con.prepareStatement(supdate);
        
        //Faculty data
        FacultySQL fac = new FacultySQL();
        String fname, desig;
        int id;
        String fselect = "select * from faculty";
        String finsert = "insert into faculty values (?,?,?,?,?)";
        String fdelete = "delete from faculty where id=?";
        String fupdate = "update faculty set id=?,fname=?,age=?,dept=?,desig=? where id=?";
        PreparedStatement fpstIn = con.prepareStatement(finsert);
        PreparedStatement fpstDel = con.prepareStatement(fdelete);
        PreparedStatement fpstUpd = con.prepareStatement(fupdate);
        
        // start adding code here
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
        
        // stop adding code here
        
        st.close();
        con.close();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LoginLabel;
    private javax.swing.JButton loginbutton;
    private javax.swing.JPasswordField passwordfield;
    private javax.swing.JLabel passwordlabel;
    private javax.swing.JTextField usernamefield;
    private javax.swing.JLabel usernamelabel;
    // End of variables declaration//GEN-END:variables
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton addbutton;
    public javax.swing.JTextField agefield;
    public javax.swing.JTextField attendancefield;
    public javax.swing.JButton backbutton;
    public javax.swing.JTextField deptfield;
    public javax.swing.JTextField gpafield;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JTextField rollnofield;
    public javax.swing.JTextField studentnamefield;
    // End of variables declaration//GEN-END:variables
    
}


