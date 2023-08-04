#include <iostream>

class Temperature {
public:
    // explicit 表示要求创建 Temperature 对象的时候必须显示调用该构造函数
    // 不允许发生隐式转换
    explicit Temperature(double value) : temperature(value) {}

    /**
     * 常量成员函数不会修改 Temperature 的内部数据。
     * @return
     */
    double getTemperature() const { return temperature; }

private:
    double temperature;
};

class WeatherStation {
public:
    WeatherStation(const Temperature &temp) : temperature(temp) {}

    const Temperature &getTemperature() const { return temperature; }

private:
    Temperature temperature;
};

void printTemperature(const Temperature &temp) {
    std::cout << "Temperature: " << temp.getTemperature() << " degrees Celsius" << std::endl;
}

int main() {

    Temperature temp(25.5);
    // Temperature temp01 = 26.1;

    // 使用 explicit 构造函数时，必须显式转换类型
    // 正确，显式调用构造函数
    WeatherStation station(temp);
    WeatherStation station01 = temp;

    // 错误，不能隐式转换
    // printTemperature(temp);
    // 正确，显式转换
    printTemperature(static_cast<Temperature>(30.0));

    return 0;
}
