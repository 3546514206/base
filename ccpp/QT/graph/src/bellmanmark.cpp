#include "bellmanmark.h"
#include "common.h"
BellmanMark::BellmanMark(int count)
{
    this->count=count;
    d=new double[count+1];
    p=new int[count+1];
    vertex=new QList<int>();
    reset();
}

BellmanMark::~BellmanMark(){
    delete d;
    delete p;
}
double BellmanMark::getD(int pos){
    if(pos>=1&&pos<=count)
        return *(d+pos);
    return ERROR_CODE;
}
int BellmanMark::getP(int pos){
    if(pos>=1&&pos<=count)
        return *(p+pos);
     return ERROR_CODE;
}
void BellmanMark::setD(int pos, double val){
    if(pos>=1&&pos<=count)
        *(d+pos)=val;
}
void BellmanMark::setP(int pos,int val){
    if(pos>=1&&pos<=count)
        *(p+pos)=val;
}

int BellmanMark::getCount() const
{
    return count;
}

void BellmanMark::addVertex(int i){
    vertex->push_back(i);
}
bool BellmanMark::findVertex(int i){
    for(auto it=vertex->begin();it!=vertex->end();it++){
        if(*it==i){
            return true;
        }
    }
    return false;
}

bool BellmanMark::getNegaCircuit() const
{
    return negaCircuit;
}

void BellmanMark::setNegaCircuit(bool value)
{
    negaCircuit = value;
}

void BellmanMark::reset()
{
    vertex->clear();
    negaCircuit=false;
    nega=0;
}

QList<int>* BellmanMark::getVertex() const
{
    return vertex;
}

int BellmanMark::getNega() const
{
    return nega;
}

void BellmanMark::setNega(int value)
{
    nega = value;
}
