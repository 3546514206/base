#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include "rssxmlparser.h"
#include <QMainWindow>
#include <QProcess>

class QAction;
class QListWidget;
class QMenu;
class QTextEdit;
class QWebView;

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();
private slots:
    void showContent();
    void addFeed(QString);
    void addFeed();
    void showHeaders();

    void processFinished(int exitCode,QProcess::ExitStatus);
    void processError(QProcess::ProcessError error);
    void updateStatusMessage();

private:
   
    void createActions();
    void createToolBars();
    void createStatusBar();
    void createDockWindows();
    void createMenus();

    void readCache();
    void writeCache(QString);

    void addContent(QString);
    QWebView* content;
    QListWidget *feedsList;
    QListWidget* headersList;

    QMenu* feedsMenu;
    QMenu* updateMenu;
    QMenu* viewMenu;
    QMenu* helpMenu;

    QAction* quitAct;
    QAction* addFeedAct;
    QAction* deleteFeedAct;
    QAction* updateOneAct;
    QAction* updateAllAct;
    QAction* showFeedsDockAct;
    QAction* showHeadersDockAct;
    QAction* aboutAct;


    RssXmlParser* rssData;
    QProcess process;
    QString currentFileName;
    QMap<QString,QString> currentFeed;
    QMap<QString,QMap<QString,QString> > feedsHeaders;

};

#endif // MAINWINDOW_H
