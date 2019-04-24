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
/**
 * 负责处理对students表的操作请求
 * @author H.H
 *
 */
@RequestMapping("students")
@Controller
public class StudentsController{
	
	@Autowired
	private StudentsService studentsService;
	

	/**
	 * 向表中增加学生
	 * @param student
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "add", method=RequestMethod.POST)
	public Result addStudent(Student student) {
		
		Integer id = studentsService.addStudent(student);
		
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
	
	/**
	 * 根据输入的页码和每页的数量查询学生信息
	 * @param page
	 * @param page_size
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "fetch", method=RequestMethod.GET)
	public Result fetchStudents(@RequestParam(value = "page") Integer page, @RequestParam(value = "page_size") Integer page_size) {
		
		Result result = new Result();
		
		Integer fisrtPosition = page * page_size;
		
		List<Student> students = studentsService.fetchStudents(fisrtPosition, page_size);
		
		result.setCode(0);
		result.setMessage("查询成功");
		
		Map<String, Object> map = new TreeMap<>();
		
		Integer numberOfStudents = studentsService.countStudents();
		
		if ((fisrtPosition + page_size) >= numberOfStudents) {
			map.put("hasmore", 0);
		}else {
			map.put("hasmore", 1);
		}
		
		map.put("list", JSON.toJSON(students));
		result.setData(JSON.toJSON(map));
	
		return result;
	}
	
	/**
	 * 根据学生id和传入的值，更新相应的学生信息
	 * @param student_id
	 * @param student
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "update/{student_id}", method=RequestMethod.POST)
	public Result updateStudent(@PathVariable("student_id") Integer student_id,Student student) {
		
		Result result = new Result();
		
		student.setId(student_id);
		
		Integer r = studentsService.updateStudent(student);
		
		if (r == 1) {
			result.setCode(0);
			result.setMessage("修改成功");
		}else {
			result.setCode(-1);
			result.setMessage("修改失败，该id不存在");
		}
		
		return result;
	}
    
	/**
	 * 根据传入的id值，批量删除学生信息
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "delete", method=RequestMethod.POST)
	public Result deleteStudents(@RequestParam("ids") String ids) {
		
		Result result = new Result();
		String[] idArray = ids.split(",");
		
		Integer r = studentsService.deleteStudents(idArray);
		
		if(r > 0) {
			result.setCode(0);
			result.setMessage("删除成功");
		}else {
			result.setCode(-1);
			result.setMessage("删除失败，id不存在");
		}
		
		return result;
	}
}
