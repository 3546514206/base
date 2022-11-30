#include <wx/wx.h>

namespace Examples {
    class Frame : public wxFrame {
    public:
        Frame() : wxFrame(nullptr, wxID_ANY, "Hello world (emoticons)") {
            staticText1->SetFont({72, wxFONTFAMILY_DEFAULT, wxFONTSTYLE_NORMAL, wxFONTWEIGHT_NORMAL});
            staticText1->SetSize(wxClientDC(staticText1).GetTextExtent(staticText1->GetLabel()));
            SetClientSize(staticText1->GetSize());
            SetMaxSize(GetSize());
            SetMinSize(GetSize());
        }

    private:
        wxPanel* panel = new wxPanel(this);
        wxStaticText* staticText1 = new wxStaticText(panel, wxID_ANY, wxT("\U0001F44B, \U0001F30E\U00002757"));
    };

    class Application : public wxApp {
        bool OnInit() override {
            (new Frame())->Show();
            return true;
        }
    };
}

wxIMPLEMENT_APP(Examples::Application);