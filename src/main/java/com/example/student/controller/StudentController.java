package com.example.student.controller;

import org.springframework.stereotype.Controller;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.student.model.Student;
import com.example.student.service.StudentService;


@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("listStudents", studentService.getAllStudents());
		return "index";
	} 
	
	@GetMapping("/showNewStudentForm")
	public String showNewStudentForm(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "new_student";
	}
	
	@PostMapping("/saveStudent")
	public String saveStudent(@ModelAttribute("student")Student student) {
		studentService.saveStudent(student);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		Student student = studentService.getStudentById(id);
		model.addAttribute("student", student);
		return "update_student";
	}
	
	@GetMapping("/deleteStudent/{id}")
	public String deleteStudent(@PathVariable (value = "id") long id) {
		this.studentService.deleteStudentById(id);
		return "redirect:/";
	}
	
}
