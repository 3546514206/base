#include "common.h"
#include <math.h>
double calcDeg(int x1,int y1,int x2,int y2){
    double ret=0;
    if(abs(x1-x2)<=1&&abs(y1-y2)>1) ret= y2>y1?-90:90;
    else if(abs(y1-y2)<=1&&abs(x1-x2)>1) ret= x2>x1?0:180;
    else{
        ret=atan((double)(y1-y2)/(x2-x1))*180/PI;
        if(x1>x2){
           ret+=180;
        }
    }
    if(ret>180)ret-=360;
    return ret;
}
QPoint calcTail(int x,int y,double deg,double len){
    return QPoint(x+len*cos(deg*PI/180),y-len*sin(deg*PI/180));
}
double calcDis(double x1,double y1,double x2,double y2){
    return sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
}
void convertToColName(int data, QString& res)
{
    Q_ASSERT(data>0 && data<65535);
    int tempData = data / 26;
    if(tempData > 0)
    {
        int mode = data % 26;
        convertToColName(mode,res);
        convertToColName(tempData,res);
    }
    else
    {
        res=(to26AlphabetString(data)+res);
    }
}
QString to26AlphabetString(int data)
{
    int conb_int = data + 0x40;
    char myChar = static_cast<char>(conb_int);  // 将整数转换为ASCII字符
    QChar myQChar = QChar::fromLatin1(myChar);  // 将ASCII字符转换为QChar
    return QString(myQChar);
}
