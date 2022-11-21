cmake_minimum_required(VERSION 3.21)
project(cinatra)

# cinatra 需要 C++ 17 的支持
set(CMAKE_CXX_STANDARD 17)

# boost 配置
find_package(Boost 1.77.0 COMPONENTS system filesystem REQUIRED)
include_directories(${Boost_INCLUDE_DIRS})
include_directories(${BOOST_LIBS_DIR})

# cinatra 依赖
set(cinatra_libs
        ./libs/cinatra.hpp
        ./libs/cinatra/client_factory.hpp
        ./libs/cinatra/connection.hpp
        ./libs/cinatra/cookie.hpp
        ./libs/cinatra/define.h
        ./libs/cinatra/dh1024.pem
        ./libs/cinatra/function_traits.hpp
        ./libs/cinatra/gzip.hpp
        ./libs/cinatra/http_cache.hpp
        ./libs/cinatra/http_client.hpp
        ./libs/cinatra/http_parser.hpp
        ./libs/cinatra/http_router.hpp
        ./libs/cinatra/http_server.hpp
        ./libs/cinatra/io_service_pool.hpp
        ./libs/cinatra/itoa.hpp
        ./libs/cinatra/itoa_jeaiii.hpp
        ./libs/cinatra/mime_types.hpp
        ./libs/cinatra/modern_callback.h
        ./libs/cinatra/multipart_parser.hpp
        ./libs/cinatra/multipart_reader.hpp
        ./libs/cinatra/picohttpparser.h
        ./libs/cinatra/render.h
        ./libs/cinatra/request.hpp
        ./libs/cinatra/response.hpp
        ./libs/cinatra/response_cv.hpp
        ./libs/cinatra/response_parser.hpp
        ./libs/cinatra/router.hpp
        ./libs/cinatra/server.crt
        ./libs/cinatra/server.key
        ./libs/cinatra/session.hpp
        ./libs/cinatra/session_manager.hpp
        ./libs/cinatra/sha1.hpp
        ./libs/cinatra/smtp_client.hpp
        ./libs/cinatra/upload_file.hpp
        ./libs/cinatra/uri.hpp
        ./libs/cinatra/url_encode_decode.hpp
        ./libs/cinatra/use_asio.hpp
        ./libs/cinatra/utils.hpp
        ./libs/cinatra/websocket.hpp
        ./libs/cinatra/ws_define.h
        )


add_executable(cinatra main.cpp ${cinatra_libs})
