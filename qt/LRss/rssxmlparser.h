#ifndef XMLPARSER_H
#define XMLPARSER_H

#include <QMap>
#include <QXmlStreamReader>

class RssXmlParser:public QXmlStreamReader
{
public:
    RssXmlParser(QIODevice* device);
    QMap<QString,QString>&   getMap(){return articles;}
    QString getNetworkName() const {return networkName;}

private:

    void read_rss();
    void read_channel();
    void read_item();

    QMap<QString,QString> articles;
    QString networkName;
};

#endif // XMLPARSER_H
