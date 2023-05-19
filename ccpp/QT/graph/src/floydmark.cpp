#include "floydmark.h"
#include "common.h"
FloydMark::FloydMark(int count)
{
    this->count=count;
    int c=count+1;
    c*=c;
    p=new int[c];
    d=new double[c];
    vertex=new QList<int>();
    floydStart=0;
    reset();
}
void FloydMark::setP(int x,int y,int val){
    if(x>=1&&x<=count&&y>=1&&y<=count){
        *(p+x*(count+1)+y)=val;
    }
}
void FloydMark::setD(int x, int y, double val){
    if(x>=1&&x<=count&&y>=1&&y<=count){
        *(d+x*(count+1)+y)=val;
    }
}
int FloydMark::getP(int x,int y){
    if(x>=1&&x<=count&&y>=1&&y<=count){
        return *(p+x*(count+1)+y);
    }
    return ERROR_CODE;
}
double FloydMark::getD(int x,int y){
    if(x>=1&&x<=count&&y>=1&&y<=count){
        return *(d+x*(count+1)+y);
    }
    return ERROR_CODE;
}

int FloydMark::getCount() const
{
    return count;
}

void FloydMark::reset()
{
    vertex->clear();
    negaCircuit=false;
    nega=0;


}

int FloydMark::getFloydStart() const
{
    return floydStart;
}

void FloydMark::setFloydStart(int value)
{
    floydStart = value;
}

QList<int> *FloydMark::getVertex() const
{
    return vertex;
}

void FloydMark::addVertex(int i){
    vertex->push_back(i);
}
bool FloydMark::findVertex(int i){
    for(auto it=vertex->begin();it!=vertex->end();it++){
        if(*it==i){
            return true;
        }
    }
    return false;
}

int FloydMark::getNega() const
{
    return nega;
}

void FloydMark::setNega(int value)
{
    nega = value;
}

bool FloydMark::getNegaCircuit() const
{
    return negaCircuit;
}

void FloydMark::setNegaCircuit(bool value)
{
    negaCircuit = value;
}
