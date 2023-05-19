#ifndef BASEVECTOR_H
#define BASEVECTOR_H


class BaseVector
{
public:
    BaseVector();
    BaseVector(int v1,int v2);
    bool getSelected() const;
    void setSelected(bool value);
    void set(int v1,int v2);
    int getV1() const;

    int getV2() const;

private:
    int v1;
    int v2;
    bool bSelected;

};

#endif // BASEVECTOR_H
