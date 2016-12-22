package pk.edu.nust.seecs.gradebook;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import pk.edu.nust.seecs.gradebook.dao.CloDao;
import pk.edu.nust.seecs.gradebook.dao.ContentDao;
import pk.edu.nust.seecs.gradebook.dao.CourseDao;
import pk.edu.nust.seecs.gradebook.dao.GradeDao;
import pk.edu.nust.seecs.gradebook.dao.StudentDao;
import pk.edu.nust.seecs.gradebook.dao.TeacherDao;
import pk.edu.nust.seecs.gradebook.entity.Clo;
import pk.edu.nust.seecs.gradebook.entity.Content;
import pk.edu.nust.seecs.gradebook.entity.Course;
import pk.edu.nust.seecs.gradebook.entity.Grade;
import pk.edu.nust.seecs.gradebook.entity.Student;
import pk.edu.nust.seecs.gradebook.entity.Teacher;

/**
 * My main App.
 * <p>
 * This executes everything.
 */
public class App {

    static Scanner in = new Scanner(System.in);

    public static Clo setclo() {

        CloDao clodao = new CloDao();
        Clo clo = new Clo();
        System.out.println("Enter name ");
        String name = in.nextLine();
        clo.setName(name);
        System.out.println("Enter desc ");
        String desc = in.nextLine();
        clo.setDescription(desc);
        System.out.println("Enter plo ");
        String plo = in.nextLine();
        clo.setPlo(plo);
        return clo;
    }

    public static Content setcontent() throws ParseException {

        ContentDao clodao = new ContentDao();
        Content content = new Content();
        System.out.println("Enter title ");
        String title = in.nextLine();
        content.setTitle(title);
        System.out.println("Enter desc ");
        String desc = in.nextLine();
        content.setDescription(desc);
        System.out.println("Enter End date in format like '2008-04-16 00:05:05'");
        String etime = in.nextLine();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // replace with your start date string
        Date d1 = df.parse(etime);
        content.setEndtime(d1);
        System.out.println("Enter start date in format like '2008-04-16 00:05:05'");
        String stime = in.nextLine();
        Date d2 = df.parse(stime);
        content.setStarttime(d2);

        System.out.println("Enter no of student want to enter");
        int n = in.nextInt();
        Set<Student> students = new HashSet<>();
        for (int i = 0; i < n; i++) {
            Student ss = setstudent();
            students.add(ss);
        }
        content.setStudents(students);
        System.out.println("Enter no of clo's want to enter");
        int nn = in.nextInt();
        List<Clo> cloo = new ArrayList<>();
        for (int i = 0; i < nn; i++) {
            Clo cc = setclo();
            cloo.add(cc);
        }
        content.setClo(cloo);
        
        return content;
    }

    public static Course setcourse() throws ParseException {

        CourseDao clocourse = new CourseDao();
        Course course = new Course();
        System.out.println("Enter title ");
        String title = in.nextLine();
        course.setClasstitle(title);
       
        System.out.println("Enter no of contents's want to enter");
        int nn = in.nextInt();
        Set<Content> contents = new HashSet<>(0);
        for (int i = 0; i < nn; i++) {
            Content cc = setcontent();
            contents.add(cc);
        }
       
        course.setContents(contents);
        System.out.println("Enter credit hours ");
        int ch = in.nextInt();
        course.setCreditHours(ch);

        System.out.println("Enter End date in format like '2008-04-16 00:05:05'");
        String etime = in.nextLine();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // replace with your start date string
        Date d1 = df.parse(etime);

        course.setEndsOn(d1);

        System.out.println("Enter start date in format like '2008-04-16 00:05:05'");
        String stime = in.nextLine();
        Date d2 = df.parse(stime);

        course.setStartsOn(d2);
        Teacher teacher = setteacher();
        course.setTeacher(teacher);
        return course;
    }

    public static Grade setgrade() throws ParseException {

        GradeDao clodao = new GradeDao();
        Grade grade = new Grade();
        Content ct = setcontent();
        grade.setContentItem(ct);
        System.out.println("Enter name ");
        String name = in.nextLine();
        grade.setName(name);
        System.out.println("Enter score ");
        int score = in.nextInt();
        grade.setScore(score);

        return grade;
    }

    public static Student setstudent() throws ParseException {

        StudentDao studentdao = new StudentDao();
        Student student = new Student();
        
        
        System.out.println("Enter no of contents's want to enter");
        int nn = in.nextInt();
        Set<Course> Courses = new HashSet<>();
        for (int i = 0; i < nn; i++) {
            Course cs = setcourse();
            Courses.add(cs);
        }
        student.setCourses(Courses);
        System.out.println("Enter name ");
        String name = in.nextLine();
        student.setName(name);

        return student;
    }

    public static Teacher setteacher() throws ParseException {

        TeacherDao tacherdao = new TeacherDao();
        Teacher teacher = new Teacher();

        System.out.println("Enter no of contents's want to enter");
        int nn = in.nextInt();
        Set<Course> Courses = new HashSet<>();
        for (int i = 0; i < nn; i++) {
            Course cs = setcourse();
            Courses.add(cs);
        }
        teacher.setCourses(Courses);
        System.out.println("Enter name ");
        String name = in.nextLine();
        teacher.setName(name);

        return teacher;
    }

    public static void main(String[] args) throws ParseException {
        CloDao clodao = new CloDao();
        ContentDao contentdao = new ContentDao();
        CourseDao coursedao = new CourseDao();
        GradeDao gradedao = new GradeDao();
        StudentDao studentdao = new StudentDao();
        TeacherDao teacherdao = new TeacherDao();

        // Add new clo
        // Delete an existing clo
        //dao.deleteClo(1);
        System.out.println("Enter 1 to add clo");
        System.out.println("Enter 11 to update clo");
        System.out.println("Enter 2 to update clo");
        System.out.println("Enter 22 to update clo");
        System.out.println("Enter 3 to update clo");
        //System.out.println("Enter 33 to update clo");
        System.out.println("Enter 4 to update clo");
        //System.out.println("Enter 44 to update clo");
        System.out.println("Enter 5 to update clo");
        //System.out.println("Enter 55 to update clo");
        System.out.println("Enter 6 to update clo");
        //System.out.println("Enter 66 to update clo");

        //Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        if (a == 1) {
            Clo clo = setclo();
            clodao.addClo(clo);

        }
        if (a == 11) {
            System.out.println("Enter id of clo to be updated");
            int b = in.nextInt();
            Clo clo = clodao.getCloById(b);
            System.out.println("Enter name ");
             String name = in.nextLine();
            clo.setName(name);
            System.out.println("Enter desc ");
        String desc = in.nextLine();
            clo.setDescription(desc);
            System.out.println("Enter plo ");
        String plo = in.nextLine();
            clo.setPlo(plo);
            clodao.updateClo(clo);
        }
        if (a == 2) {

            Content content = setcontent();

            contentdao.addContent(content);

        }
        if (a == 22) {

            System.out.println("Enter id of content to be updated");
            int b = in.nextInt();
            Content content = contentdao.getContentById(b);
            System.out.println("Enter name ");
            String name = in.nextLine();
            content.setTitle(name);
            System.out.println("Enter desc ");
            String desc = in.nextLine();
            content.setDescription(desc);
            
            System.out.println("Enter End date in format like '2008-04-16 00:05:05'");
            String etime = in.nextLine();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // replace with your start date string
            Date d1 = df.parse(etime);
            content.setEndtime(d1);
            System.out.println("Enter start date in format like '2008-04-16 00:05:05'");
            String stime = in.nextLine();
            Date d2 = df.parse(stime);
            content.setStarttime(d2);

System.out.println("Enter no of student want to enter");
        int n = in.nextInt();
        Set<Student> students = new HashSet<>();
        for (int i = 0; i < n; i++) {
            Student ss = setstudent();
            students.add(ss);
        }
        content.setStudents(students);
        System.out.println("Enter no of clo's want to enter");
        int nn = in.nextInt();
        List<Clo> clooo = new ArrayList<>();
        for (int i = 0; i < nn; i++) {
            Clo cc = setclo();
            clooo.add(cc);
        }
        content.setClo(clooo);
            contentdao.updateContent(content);
        }
        if (a == 3) {
            Course course = setcourse();

            coursedao.addCourse(course);
        }

        if (a == 4) {
            Grade grade = setgrade();

            gradedao.addGrade(grade);
        }

        if (a == 5) {
            Student student = setstudent();

            studentdao.addStudent(student);
        }

        if (a == 6) {
            Teacher teacher = setteacher();

            teacherdao.addTeacher(teacher);
        }

        // Get all clos
//        for (Clo iter : clodao.getAllClos()) {
//            System.out.println(iter);
//        }
//
//        // Get clo by id
//        System.out.println(clodao.getCloById(1));
    }

}
