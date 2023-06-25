// Copyright (C) 2021 The Qt Company Ltd.
// SPDX-License-Identifier: LicenseRef-Qt-Commercial OR GPL-3.0-only

import QtQuick 6.2
import qt-sample
import QtQuick.VirtualKeyboard 6.2

Window {
    width: mainScreen.width
    height: mainScreen.height

    visible: true
    title: "qt-sample"

    Screen01 {
        id: mainScreen
    }

    InputPanel {
        id: inputPanel
        property bool showKeyboard :  active
        y: showKeyboard ? parent.height - height : parent.height
        Behavior on y {
            NumberAnimation {
                duration: 200
                easing.type: Easing.InOutQuad
            }
        }
        anchors.leftMargin: Constants.width/10
        anchors.rightMargin: Constants.width/10
        anchors.left: parent.left
        anchors.right: parent.right
    }
}

