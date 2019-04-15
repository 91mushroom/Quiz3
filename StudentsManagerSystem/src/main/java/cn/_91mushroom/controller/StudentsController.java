package cn._91mushroom.controller;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn._91mushroom.entity.Result;
import cn._91mushroom.entity.Student;
import cn._91mushroom.service.StudentsService;

@RequestMapping("students")
@Controller
public class StudentsController{
	
	@Autowired
	private StudentsService studentsSercice;
	
	
	public void setStudentsSercice(StudentsService studentsSercice) {
		this.studentsSercice = studentsSercice;
	}



	@ResponseBody
	@RequestMapping(value = "add", method=RequestMethod.POST)
	public Result addStudent(Student student) {
		
		Integer id = studentsSercice.addStudent(student);
		
		Result result = new Result();
		
		if (id == 1) {
			
			result.setCode(0);
			result.setMessage("插入成功");
			
			Map<String, Integer> map = new TreeMap<>();
			map.put("id", student.getId());
			
			Object object = JSON.toJSON(map);
			
			result.setData(object);
		}else {
			
			result.setCode(-1);
			result.setMessage("插入失败");
			
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "fetch", method=RequestMethod.GET)
	public Result fetchStudents(@RequestParam(value = "page") Integer page, @RequestParam(value = "page_size") Integer page_size) {
		
		Result result = new Result();
		
		Integer fisrtPosition = page * page_size;
		
		List<Student> students = studentsSercice.fetchStudents(fisrtPosition, page_size);
		
		result.setCode(0);
		result.setMessage("查询成功");
		
		Map<String, Object> map = new TreeMap<>();
		
		Integer numberOfStudents = studentsSercice.countStudents();
		
		if ((fisrtPosition + page_size) >= numberOfStudents) {
			map.put("hasmore", 0);
		}else {
			map.put("hasmore", 1);
		}
		
		map.put("list", JSON.toJSON(students));
		result.setData(JSON.toJSON(map));
	
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "update/{student_id}", method=RequestMethod.POST)
	public Result updateStudent(@PathVariable("student_id") Integer syudent_id,Student student) {
		
		Result result = new Result();
		
		if (syudent_id == null || syudent_id < 0) {
			result.setCode(-1);
			result.setMessage("id错误");
			return result;
		}
		
		student.setId(syudent_id);
		
		Integer r = studentsSercice.updateStudent(student);
		
		if (r == 1) {
			result.setCode(0);
			result.setMessage("修改成功");
		}else {
			result.setCode(-1);
			result.setMessage("修改失败");
		}
		
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "delete", method=RequestMethod.POST)
	public Result deleteStudents(@RequestParam("ids") String ids) {
		
		Result result = new Result();
		String[] idArray = ids.split(",");
		
		if (idArray.length == 0) {
			result.setCode(0);
			result.setMessage("删除成功");
			return result;
		}
		
		Integer r = studentsSercice.deleteStudents(idArray);
		
		result.setCode(0);
		result.setMessage("删除成功");
		
		Map<String, Object> map = new TreeMap<>();
		map.put("count", r);
		
		result.setData(JSON.toJSON(map));
		return result;
	}
}
