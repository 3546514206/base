#include "mainwindow.h"

#include <QApplication>
#include <QLineEdit>
#include <QObject>
#include <QPushButton>
#include <QVBoxLayout>

class ButtonHandler : public QObject {
  Q_OBJECT

public:
  ButtonHandler(QObject *parent = nullptr) : QObject(parent) {}

public slots:
  void handleButtonClicked() { qDebug() << "Button Clicked!"; }
};

class LineEditHandler : public QObject {
  Q_OBJECT

public:
  LineEditHandler(QObject *parent = nullptr) : QObject(parent) {}

public slots:
  void handleTxtChanged(const QString &text) {
    qDebug() << "Text changed" << text;
  }
};

int main(int argc, char *argv[]) {
  QApplication a(argc, argv);

  QPushButton button("Click me");
  QLineEdit lineEdit;

  ButtonHandler buttonHandler;
  LineEditHandler lineEditHandler;

  QObject::connect(&button, &QPushButton::clicked, &buttonHandler,
                   &ButtonHandler::handleButtonClicked);
  QObject::connect(&lineEdit, &QLineEdit::textChanged, &lineEditHandler,
                   &LineEditHandler::handleTxtChanged);

  QVBoxLayout layout;
  layout.addWidget(&button);

  layout.addWidget(&lineEdit);

  QWidget window;
  window.setLayout(&layout);
  window.show();

  return a.exec();
}
