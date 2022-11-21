#include <iostream>

using namespace std;

class student {
private:
    char *m_name;
    int m_age;
    float m_score;
public:
    student(char *name, int age, float score);

    void show();
};

//采用初始化列表
student::student(char *name, int age, float score) : m_name(name), m_age(age), m_score(score) {
    cout << "name=" << m_name << endl;
    cout << "age=" << m_age << endl;
    cout << "score=" << m_score << endl;
}

void student::show() {
    cout << m_name << "'age is" << m_age << ",score is" << m_score << endl;
}

int main() {
//  直接声明得到一个对象
    student stu("tifa", 15, 92.5f);
    stu.show();
    cout << endl;
//    new 则返回地址
    student *pstu = new student("yang", 16, 96);
    pstu->show();

    return 0;
}