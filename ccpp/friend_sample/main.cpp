#include<iostream>

using namespace std;

class car;  //提前声明CCar类，以便后面的CDriver类使用
class car_driver {
public:
    void ModifyCar(car *pCar);  //改装汽车
};

class car {
private:
    int price;

    friend int MostExpensiveCar(car cars[], int total);  //声明友元
    friend void car_driver::ModifyCar(car *pCar);  //声明友元，一定要在当前类的外部定义
};

void car_driver::ModifyCar(car *pCar) {
    pCar->price += 1000;  //汽车改装后价值增加
}

//求最贵气车的价格
int MostExpensiveCar(car cars[], int total) {
    int tmpMax = -1;
    for (int i = 0; i < total; ++i)
        if (cars[i].price > tmpMax)
            tmpMax = cars[i].price;
    return tmpMax;
}

int main() {
    return 0;
}
