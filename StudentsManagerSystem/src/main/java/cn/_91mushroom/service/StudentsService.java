package cn._91mushroom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn._91mushroom.entity.Student;
import cn._91mushroom.mapper.StudentsMapper;

@Service
public class StudentsService {

	@Autowired
	private StudentsMapper studentsMapper;

	public void setStudentsMapper(StudentsMapper studentsMapper) {
		this.studentsMapper = studentsMapper;
	}

	public Integer addStudent(Student student) {
		return studentsMapper.addStudent(student);
	}

	public List<Student> fetchStudents(Integer fisrtPosition, Integer page_size) {
		return studentsMapper.fetchStudents(fisrtPosition, page_size);
	}

	public Integer countStudents() {
		return studentsMapper.countStudents();
	}

	public Integer updateStudent(Student student) {
		return studentsMapper.updateStudent(student);
	}

	public Integer deleteStudents(String[] ids) {
	    return studentsMapper.deleteStudents(ids);
	}

}
