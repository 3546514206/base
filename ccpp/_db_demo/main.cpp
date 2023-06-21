#include <mysql_driver.h>
#include <mysql_connection.h>
#include <cppconn/resultset.h>
#include <cppconn/statement.h>

int main() {
    sql::mysql::MySQL_Driver* driver;
    sql::Connection* con;
    sql::Statement* stmt;
    sql::ResultSet* res;

    driver = sql::mysql::get_mysql_driver_instance();
    con = driver->connect("tcp://127.0.0.1:3306", "root", "root");

    stmt = con->createStatement();
    stmt->execute("USE webloader");
    res = stmt->executeQuery("SELECT * FROM user");

    while (res->next()) {
        int id = res->getInt("id");
        std::string name = res->getString("name");
        int age = res->getInt("age");

        // 打印查询结果
        std::cout << "ID: " << id << ", Name: " << name << ", Age: " << age << std::endl;
    }

    delete res;
    delete stmt;
    delete con;

    return 0;
}
