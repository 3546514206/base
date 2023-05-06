#include <iostream>
#include <cstring>

using namespace std;

struct Books
{
    char title[50];
    char author[50];
    char subject[100];
    int book_id;
};

int main()
{

    Books book1;
    Books book2;

    strcpy(book1.title, "learing C++ Programing");
    strcpy(book1.author, "杨海波");
    strcpy( book1.subject, "C++ Programming");
    book1.book_id = 6495407;

    // Book2 详述
    strcpy( book2.title, "Telecom Billing");
    strcpy( book2.author, "Yakit Singha");
    strcpy( book2.subject, "Telecom");
    book2.book_id = 6495700;
 
    // 输出 Book1 信息
    cout << "Book 1 title : " << book1.title <<endl;
    cout << "Book 1 author : " << book1.author <<endl;
    cout << "Book 1 subject : " << book1.subject <<endl;
    cout << "Book 1 id : " << book1.book_id <<endl;

    // 输出 Book2 信息
    cout << "Book 2 title : " << book2.title <<endl;
    cout << "Book 2 author : " << book2.author <<endl;
    cout << "Book 2 subject : " << book2.subject <<endl;
    cout << "Book 2 id : " << book2.book_id <<endl;

    return 0;
}
