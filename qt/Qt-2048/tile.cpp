#include "tile.h"

//定义Tile的颜色
struct TileColor{
    char *bgcolor;
    char *ftcolor;
}tileColors[12] = {
    {"rgba(238, 228, 218, 0.35)","#fff"}, //0
    {"#eee4da","#776e65"}, //2
    {"#ede0c8","#776e65"},  //4
    {"#f2b179","#f9f6f2"},  //8
    {"#f59563","#f9f6f2"},  //16
    {"#f67c5f","#f9f6f2"},  //32
    {"#f65e3b","#f9f6f2"},  //64
    {"#edcf72","#f9f6f2"},  //128
    {"#edcc61","#f9f6f2"},  //256
    {"#edc850","#f9f6f2"},  //512
    {"#edc53f","#f9f6f2"},  //1024
    {"#edc22e","#f9f6f2"}  //2048
};

Tile::Tile(QWidget *parent) :
    QPushButton(parent)
{
    this->setStyleSheet(""
                        "border:none; "
                        "border-radius:5px; "
                        "background-color:rgba(238, 228, 218, 0.35);"
                        "color: #eee4da");
    this->setFont(QFont("Arial", 40));
    this->setDisabled(true);
    value = 0;
    setValue(value);
}


int Tile::getValue(){
    return this->value;
}
//改变Tile数值时改变Tile的属性
void Tile::setValue(int value){
    int times = 0;
    QFont font;
    font.setFamily("Arial");
    if(value < 100)
        font.setPointSize(40);
    else if(value < 1000)
        font.setPointSize(30);
    else
        font.setPointSize(25);
    this->value = value;
    if(value == 0){
        this->setText("");
    }else{
        times = (int) log(value*1.0)/log(2.0) + 1;
        times = times < 11 ? times : 11;
        this->setText(QString::number(value));
    }
    this->setFont(font);
    this->setStyleSheet(QString("") +
                        "background-color:" + tileColors[times].bgcolor + "; "
                        "color :" + tileColors[times].ftcolor + ";");
}

//给Tile数值改变添加动画
void Tile::animateTo(int i){
    if(i == this->value)
        return;
    if(c_rect.isNull())
        //因为连续动画会丢失位置信息 所以在第一次动画时存下Tile的位置信息
        c_rect.setRect(this->x(),this->y(),80,80);
    //缩放动画
    QPropertyAnimation *sizeam = new QPropertyAnimation(this,"geometry");
    //值改变动画
    QPropertyAnimation *valueam = new QPropertyAnimation(this, "value");
    sizeam->setDuration(200);
    valueam->setDuration(100);
    sizeam->setKeyValueAt(0.0, c_rect);
    //从原来的大小改编成0
    sizeam->setKeyValueAt(0.5,QRect(c_rect.x() + c_rect.width()/2,
                                    c_rect.y() + c_rect.height()/2,
                                    0,0));
    //然后从0变为原来的大小
    sizeam->setKeyValueAt(1.0, c_rect);
    //值的改变放到最后 在缩放动画执行到一半时候改变值
    valueam->setStartValue(this->value);
    valueam->setKeyValueAt(0.99,this->value);
    valueam->setEndValue(i);
    valueam->start();
    sizeam->start();
}
