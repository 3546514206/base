#include <QCoreApplication>

#include <iostream>

int main(int argc, char *argv[]) {
  QCoreApplication a(argc, argv);
  std::cout << "hello qt demo" << std::endl;
  return a.exec();
}
