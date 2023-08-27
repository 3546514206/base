package main

import "fmt"

type Student struct {
	ID       int
	Name     string
	Age      int
	Subjects []string
}

var students map[int]Student = make(map[int]Student)

func AddStudent(student Student) {
	students[student.ID] = student
}

func GetStudentById(id int) (Student, error) {
	student, ok := students[id]
	if ok {
		return student, nil
	}

	return Student{}, fmt.Errorf("Student with ID %d not found", id)
}

func main() {
	student1 := Student{
		ID:       1,
		Name:     "aaa",
		Age:      10,
		Subjects: []string{"Math", "History"},
	}

	student2 := Student{
		ID:       2,
		Name:     "bbb",
		Age:      20,
		Subjects: []string{"Art"},
	}

	AddStudent(student1)
	AddStudent(student2)

	student, _ := GetStudentById(1)
	fmt.Println(student)
}
