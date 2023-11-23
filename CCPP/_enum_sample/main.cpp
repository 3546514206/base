#include <iostream>

// 强类型枚举引入了类型安全和作用域。在C++11及以后的版本中，引入了强类型枚举（enum class）：
enum class file_type {
    text,
    image,
    binary,
    unknown
};

class enum_sample_file {
private:
    std::string file_name;
    file_type type;
public:
    enum_sample_file(const std::string &name);

    std::string get_name() const;

    file_type get_file_type() const;

    void print_file_info() const;

    void set_file_type(file_type new_type);
};

enum_sample_file::enum_sample_file(const std::string &name) : file_name(name), type(file_type::unknown) {}

std::string enum_sample_file::get_name() const {
    return this->file_name;
}

file_type enum_sample_file::get_file_type() const {
    return this->type;
}

void enum_sample_file::set_file_type(file_type new_type) {
    this->type = new_type;
}

void enum_sample_file::print_file_info() const {
    std::cout << "File: " << this->file_name << std::endl;
    std::cout << "Type: ";
    switch (this->type) {
        case file_type::text:
            std::cout << "Text";
            break;
        case file_type::binary:
            std::cout << "Binary";
            break;
        case file_type::image:
            std::cout << "Image";
            break;
        case file_type::unknown:
            std::cout << "Unknown";
            break;
    }
    std::cout << std::endl;
}


int main() {

    enum_sample_file file("test.txt");
    file.print_file_info();

    file.set_file_type(file_type::image);
    file.print_file_info();

    return 0;
}
