package com.example.studentapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.studentapp.entity.Student;
import com.example.studentapp.repository.StudentRepository;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public String listStudents(Model model) {
        List<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "list-students";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }

    @PostMapping("/add")
    public String addStudent(@ModelAttribute Student student) {
        studentRepository.save(student);
        return "redirect:/students";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        model.addAttribute("student", student);
        return "update-student";
    }

    @PostMapping("/update")
    public String updateStudent(@ModelAttribute Student student) {
        studentRepository.save(student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        studentRepository.deleteById(id);
        return "redirect:/students";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
