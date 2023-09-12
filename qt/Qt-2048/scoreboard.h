#ifndef SCOREBOARD_H
#define SCOREBOARD_H

#include <QWidget>
#include <QLabel>

class ScoreBoard : public QWidget
{
    Q_OBJECT
public:
    explicit ScoreBoard(QWidget *parent = 0);
    void setScore(int c_score);
    void initScore();
signals:

public slots:
private:
    QLabel *score, *best;
    int i_score, i_best;
};

#endif // SCOREBOARD_H
