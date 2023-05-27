from http.server import HTTPServer, BaseHTTPRequestHandler
import json

data = {'result': 'HTTP SERVER OK'}
host = ('localhost', 19999)


class My_Server(BaseHTTPRequestHandler):
    def do_GET(self):
        self.send_response(200)

        # 发给请求客户端的响应数据
        self.send_header('Content-type', 'application/json')
        self.end_headers()
        self.wfile.write(json.dumps(data).encode())

    def do_POST(self):
        self.send_response(200)

        datas = self.rfile.read(int(self.headers['content-length']))
        print('headers', self.headers)
        print("-->> post:", self.path, self.client_address)
        print(datas)

        # 发给请求客户端的响应数据
        self.send_header('Content-type', 'application/json')
        self.end_headers()
        self.wfile.write(json.dumps(data).encode())


if __name__ == '__main__':
    server = HTTPServer(host, My_Server)
    print("server启动@ : %s:%s" % host)

    server.serve_forever()