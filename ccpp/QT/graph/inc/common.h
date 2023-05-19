#ifndef COMMON_H
#define COMMON_H
#define POS_INFINITY 1073741823 //max pos integer /2
#define ERROR_CODE -1073741824
#define UNSUPPORTED_ERROR -1073741823
#define VERTEX_SIZE 50
#define PI 3.1415926535897932
#define FLOYDMAXDISPLAY 15
#define LABELLRMARGIN 30
#define MIN(a,b) (a>b?b:a)
#define MAX(a,b) (a<b?b:a)
#include "spgraph.h"
#include "spvertex.h"
#include "spvertexparam.h"
#include "nsmgraph.h"
#include "nsmvertex.h"
#include "nsmvertexparam.h"
#include "floydmark.h"
#include "bellmanmark.h"
#include "basematrix.h"
#include "basevector.h"
#include "nsmvertexdata.h"
#include "nsmvertexparamdata.h"
#include "nsmgraphdata.h"
#include "nsmdummyedge.h"
#endif // COMMON_H
double calcDeg(int x1,int y1,int x2,int y2);
QPoint calcTail(int x,int y,double deg,double len);
double calcDis(double x1, double y1, double x2, double y2);
void convertToColName(int data, QString &res);
QString to26AlphabetString(int data);

