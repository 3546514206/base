#include "rssxmlparser.h"
#include <QXmlStreamReader>
#include <QMap>

RssXmlParser::RssXmlParser(QIODevice *device)
{
    setDevice(device);
    while(!atEnd())
    {
        readNext();
        if(isStartElement())
        {
            if(name()=="rss")
            {
                read_rss();
            }

        }
    }
}

void RssXmlParser::read_rss()
{
    while(!atEnd())
    {
        readNext();
        if(isStartElement())
        {
            if(name()=="channel")
            {
                read_channel();
            }
        }
        if(isEndElement())
        {
            if(name()=="rss")
            {
                break;
            }
        }

    }
}

void RssXmlParser::read_channel()
{
    QString t;
    while(!atEnd())
    {
        readNext();
        if(isStartElement())
        {
            //optimised,call once!
            t=name().toString();
            if(t=="title")
                   networkName=readElementText();
            if(t=="item")
            {
                read_item();
            }

        }
        if(isEndElement())
        {
            if(name()=="channel")
                break;
        }

    }

}

void RssXmlParser::read_item()
{
    QString title;
    QString link;
    while(!atEnd())
    {
        readNext();
        if(isStartElement())
        {
            if(name()=="title")
            {
                title=readElementText();
                articles.insert(title,NULL);
            }
            if(name()=="link")
            {
                link=readElementText();
                articles[title]=link;
            }

        }
        if(isEndElement())
        {
            if(name()=="item")
                break;
        }
    }
}
