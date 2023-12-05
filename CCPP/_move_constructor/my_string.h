//
// Created by 杨海波 on 2023/12/5.
//

#ifndef _MOVE_CONSTRUCTOR_MY_STRING_H
#define _MOVE_CONSTRUCTOR_MY_STRING_H

namespace move_constructor {
    class my_string {
    private:
        char *data;

    public:
        // 移动构造函数（负责对象的复制）
        my_string(my_string &&other) noexcept;

        // 析构函数
        ~my_string();
    };

}

#endif //_MOVE_CONSTRUCTOR_MY_STRING_H
