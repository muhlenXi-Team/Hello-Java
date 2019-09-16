package com.muhlenxi.dao;
import java.util.List;
import com.muhlenxi.domain.Student;

public interface IStudentDao {
    List<Student> findAll();

    void saveStudent(Student student);

    void deleteStudent(int id);

    void updateStudent(Student student);

    Student findStudentById(int id);

    List<Student> findStudentsByName(String name);

    int findTotal();
}
