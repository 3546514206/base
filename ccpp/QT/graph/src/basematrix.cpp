#include "basematrix.h"
#include "basevector.h"
BaseMatrix::BaseMatrix()
{
    vectors=new QList<BaseVector*>();

}

QList<BaseVector *> *BaseMatrix::getVectors() const
{
    return vectors;
}

void BaseMatrix::addVector(BaseVector *vector)
{
    vectors->push_back(vector);

}

void BaseMatrix::removeVector(int v1,int v2)
{
    for(int i=0;i<vectors->count();i++){
        if((v1==vectors->at(i)->getV1()&&v2==vectors->at(i)->getV2())
                ||(v2==vectors->at(i)->getV1()&&v1==vectors->at(i)->getV2())){
            vectors->removeAt(i);
            break;
        }
    }
}

bool BaseMatrix::isBaseVector(int v1, int v2)
{
    for(int i=0;i<vectors->count();i++){
        if((v1==vectors->at(i)->getV1()&&v2==vectors->at(i)->getV2())
                ||(v2==vectors->at(i)->getV1()&&v1==vectors->at(i)->getV2())){
            return true;
        }
    }
    return false;
}

BaseVector *BaseMatrix::getBaseVector(int v1, int v2)
{
    for(int i=0;i<vectors->count();i++){
        if((v1==vectors->at(i)->getV1()&&v2==vectors->at(i)->getV2())
                ||(v2==vectors->at(i)->getV1()&&v1==vectors->at(i)->getV2())){
            return vectors->at(i);
        }
    }
    return NULL;
}

void BaseMatrix::clearVectors()
{
    vectors->clear();

}

int BaseMatrix::getCount() const
{
    return vectors->count();
}

QList<int> BaseMatrix::getCircuit(int v1, int v2)
{
    QList<int> list;
    list.push_back(v1);
    for(int i=0;i<vectors->count();i++){
        vectors->at(i)->setSelected(false);
    }
    int p=0;
    while(p!=v2){
        int q=list.at(list.count()-1);
        p=0;
        for(int i=0;i<vectors->count();i++){
            if(!vectors->at(i)->getSelected()){
                if(q==vectors->at(i)->getV1()){
                    p=vectors->at(i)->getV2();
                }else if(q==vectors->at(i)->getV2())
                    p=vectors->at(i)->getV1();
                if(p!=0){
                    vectors->at(i)->setSelected(true);
                    list.append(p);
                    break;
                }
            }
        }
        if(p==0){
            list.removeAt(list.count()-1);
        }

    }
    return list;
}

int BaseMatrix::getDegree(int v)
{
    int ret=0;
    for(int i=0;i<vectors->count();i++){
        if(v==vectors->at(i)->getV1()||v==vectors->at(i)->getV2())
            ret++;
    }
    return ret;
}
bool BaseMatrix::isDegreeOne(int v){
    return getDegree(v)==1;
}
