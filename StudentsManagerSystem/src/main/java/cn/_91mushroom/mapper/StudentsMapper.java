package cn._91mushroom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn._91mushroom.entity.Student;

@Repository
public interface StudentsMapper {
	
	Integer addStudent(Student student);

	List<Student> fetchStudents(@Param("fisrtPosition") Integer fisrtPosition, @Param("page_size") Integer page_size);
	
	Integer countStudents();

	Integer updateStudent(Student student);

	Integer deleteStudents(String[] ids);
}
