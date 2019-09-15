package com.muhlenxi.dao;
import java.util.List;
import com.muhlenxi.domain.Student;

public interface IStudentDao {
    List<Student> findAll();
}
