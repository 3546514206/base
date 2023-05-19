#include "common.h"
#include <iostream>
using namespace std;
SPGraph::SPGraph()
{
    vertexs=new QList<SPVertex*>();
    vertexs->push_back(new SPVertex());
    bellmanMark=NULL;
    floydMark=NULL;
    count=0;
}


SPGraph::~SPGraph()
{
    vertexs->clear();
    delete vertexs;
}

void SPGraph::addVertex(SPVertex *v)
{
    vertexs->push_back(v);
    count++;
}

void SPGraph::removeVertexAt(int pos){
    if(pos>0){
        for(int i=0;i<vertexs->size();i++){
            if(i!=pos){
                QList<SPVertexParam*> *vp=vertexs->at(i)->getParams();
                auto b=vp->begin(),e=vp->end();
                while(b!=e){
                    if((*b)->getP()==pos)
                        vp->erase(b++);
                    else
                        b++;
                }
            }
        }
        for(int i=0;i<vertexs->size();i++){
            QList<SPVertexParam*> *vp=vertexs->at(i)->getParams();
            auto b=vp->begin(),e=vp->end();
            while(b!=e){
                if((*b)->getP()>pos)
                    (*b)->setP((*b)->getP()-1);
                b++;
            }
        }
        vertexs->removeAt(pos);
        count--;
    }
}

int SPGraph::getCount() const
{
    return count;
}


int SPGraph::bellman()
{
    calcResult.clear();
    calcResult<<"当前方法:Bellman";
    if(bellmanMark!=NULL)
        delete bellmanMark;
    bellmanMark=new BellmanMark(count);
    if(count<2){
        calcResult<<"至少要有两个节点！！！";
        return 2;
    }
    int k=1;
    bool changed=false;
    BellmanMark* m=bellmanMark;
    m->setD(1,0);
    m->setP(1,0);
    for(int i=2;i<=count;i++){
        m->setD(i,POS_INFINITY);
        m->setP(i,0);
    }
    while(k<=count)
    {
        calcResult<<"第"+QString::number(k)+"次计算开始";
        for(int j=2;j<=count;j++){
            SPVertex* v=vertexs->at(j);
            QList<SPVertexParam*> *vp=v->getParams();
            for(auto q=vp->begin();q!=vp->end();++q){
                int p=(*q)->getP();
                double e=(*q)->getE();
                if(m->getD(p)+e<m->getD(j)){
                    m->setD(j,m->getD(p)+e);
                    m->setP(j,p);
                    changed=true;
                }
            }
        }
        for(int j=1;j<=m->getCount();j++){
            int p=m->getP(j);
            double d=m->getD(j);
            QString s= "   ·"+QString::number(j)+":d="+(d==POS_INFINITY?"∞":QString::number(d))+" p="+QString::number(p);
            calcResult<<s;
        }
        calcResult<<"第"+QString::number(k)+"次计算结束";
        if(!changed){
            calcResult<<"完成计算";
            return 0;
        }else{
            ++k;
            changed=false;
        }
    }

    if(k>count){
        calcResult<<"<span style='color:red;font-family:\"微软雅黑\"'><i>检测到负权值回路，计算终止</i></span>";
        return 1;
    }
    return ERROR_CODE;
}

int SPGraph::floyd(){
    calcResult.clear();
    calcResult<<"当前方法:Floyd";
    calcResult<<"<span style='color:red;font-family:\"微软雅黑\"'><i>注意：使用Floyd方法需确保没有负权值回路<br>否则计算的结果有可能是错的</i></span>";
    if(floydMark!=NULL)
        delete floydMark;
    floydMark=new FloydMark(count);
    if(count<2){
        calcResult<<"至少要有两个节点！！！";
        return ERROR_CODE;
    }
    FloydMark* m=floydMark;
    for(int i=1;i<=count;i++){
        for(int j=1;j<=count;j++){
            if(i==j)m->setD(i,j,0);
            else m->setD(i,j,POS_INFINITY);
            m->setP(i,j,i);
            SPVertex* v=vertexs->at(j);
            QList<SPVertexParam*> *vp=v->getParams();
            for(auto k=vp->begin();k!=vp->end();++k){
                if((*k)->getP()==i){
                    m->setD(i,j,(*k)->getE());
                }
            }
        }
    }

    for(int k=1;k<=count;k++){
        for(int i=1;i<=count;i++)
            for(int j=1;j<=count;j++){
                double p=m->getD(i,k);
                double q=m->getD(k,j);
                if(p<POS_INFINITY&&q<POS_INFINITY&&p+q<m->getD(i,j)){
                    m->setD(i,j,p+q);
                    m->setP(i,j,m->getP(k,j));

                }
            }

        if(count<=FLOYDMAXDISPLAY){
            calcResult<<"第"+QString::number(k)+"次计算";
            calcResult<<"P:";
            QString temp="┌";
            for(int i=0;i<count;i++){
                temp+="----------┬";
            }
            temp+="----------┐";
            calcResult<<temp;
            temp="│          │";
            for(int i=1;i<=count-1;i++){
                temp+=QString("%1").arg(i,10)+"│";
            }
            temp+=QString("%1").arg(count,10)+"│";
            calcResult<<temp;
            for(int i=1;i<=count;i++){


                if(i==1){
                    temp="├----------┼";
                    for(int j=1;j<=count-1;j++){
                        temp+="----------┴";
                    }
                    temp+="----------┤";
                }else{
                    temp="├----------┤";
                    for(int j=1;j<=count-1;j++){
                        temp+="            ";
                    }
                    temp+="          │";

                }
                calcResult<<temp;
                temp="│"+QString("%1").arg(i,10)+"│";
                for(int j=1;j<=count-1;j++){
                    temp+=QString("%1").arg(m->getP(i,j),10);
                    temp+="  ";
                }
                temp+=QString("%1").arg(m->getP(i,count),10);
                temp+="│";

                calcResult<<temp;

            }
            temp="└----------┴";
            for(int j=1;j<=count-1;j++){
                temp+="------------";
            }
            temp+="----------┘";
            calcResult<<temp;
            calcResult<<"D:";
            temp="┌";
            for(int i=0;i<count;i++){
                temp+="----------┬";
            }
            temp+="----------┐";
            calcResult<<temp;
            temp="│          │";
            for(int i=1;i<=count-1;i++){
                temp+=QString("%1").arg(i,10)+"│";
            }
            temp+=QString("%1").arg(count,10)+"│";
            calcResult<<temp;
            for(int i=1;i<=count;i++){
                if(i==1){
                    temp="├----------┼";
                    for(int j=1;j<=count-1;j++){
                        temp+="----------┴";
                    }
                    temp+="----------┤";
                }else{
                    temp="├----------┤";
                    for(int j=1;j<=count-1;j++){
                        temp+="            ";
                    }
                    temp+="          │";

                }
                calcResult<<temp;
                temp="│"+QString("%1").arg(i,10)+"│";
                for(int j=1;j<=count-1;j++){
                    double d=m->getD(i,j);
                    if(d<POS_INFINITY)
                        temp+=QString("%1").arg(d,10);
                    else
                        temp+="        ∞";
                    temp+="  ";
                }
                double d=m->getD(i,count);
                if(d<POS_INFINITY)
                    temp+=QString("%1").arg(d,10);
                else
                    temp+="        ∞";
                temp+="│";

                calcResult<<temp;

            }
            temp="└----------┴";
            for(int j=1;j<=count-1;j++){
                temp+="------------";
            }
            temp+="----------┘";
            calcResult<<temp;
        }
    }
    if(count>FLOYDMAXDISPLAY){
        calcResult<<"数据量太大,显示被禁止了";
    }
    calcResult<<"完成计算";
    return 0;
}

BellmanMark *SPGraph::getBellmanMark() const
{
    return bellmanMark;
}

FloydMark *SPGraph::getFloydMark() const
{
    return floydMark;
}




void SPGraph::clearVertexs(){
    vertexs->clear();
    vertexs->push_back(new SPVertex());
    count=0;
}

SPVertex* SPGraph::getVertexAt(int pos) const{
    return vertexs->at(pos);
}
int SPGraph::getLastX(){

    return vertexs->at(count)->getCenterX();
}
int SPGraph::getLastY(){

    return vertexs->at(count)->getCenterY();
}

QStringList SPGraph::getCalcResult() const
{
    return calcResult;
}
