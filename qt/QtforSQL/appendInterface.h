#ifndef APPENDINTERFACE_H
#define APPENDINTERFACE_H

#include <QWidget>
#include <QListWidget>
#include <QStackedWidget>
#include <QHBoxLayout>
#include <QLabel>

#include "addGrade.h"
#include "addStudent.h"
#include "addCourse.h"
#include "addMark.h"
#include "addFamily.h"

class appendInterface : public QWidget
{
    Q_OBJECT

public:
    appendInterface(QWidget *parent = 0);
    ~appendInterface();
private:
    QListWidget *listWidget;
    QStackedWidget *stackWidget;
    QHBoxLayout *stackWidgetLayout;
};

#endif // APPENDINTERFACE_H
