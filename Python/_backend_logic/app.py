import webview

def custom_logic(window):
    print(0.48*100)
    window.toggle_fullscreen()
    window.evaluate_js('alert("Nice one brother")')

if __name__ == '__main__':
    window = webview.create_window('Woah dude!', html='<h1>Woah dude!<h1>')
    webview.start(custom_logic, window)
