#include <wx/wx.h>
#include "sample.hpp"

class Hello : public wxFrame
{
public:
    Hello(const wxString& title)
            : wxFrame(NULL, wxID_ANY, title, wxDefaultPosition, wxSize(250, 150))
    {
        Centre();
    }
};

class MyApp : public wxApp
{
public:
    bool OnInit()
    {
        Simple *simple = new Simple(wxT("Hello"));
        simple->Show(true);
        return true;
    }
};

wxIMPLEMENT_APP(MyApp);