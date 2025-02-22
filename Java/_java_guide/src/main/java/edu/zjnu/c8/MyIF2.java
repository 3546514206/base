package edu.zjnu.c8;

public interface MyIF2 {
    // This is a static interface method.
    static int getUniversalID() {
        return 0;
    }

    // this is a "normal" interface method declaration.
    // It does NOT define a default implementation.
    int getUserID();

    // This is a default method. Notice that it provides
    // a default implementation.
    default int getAdminID() {
        return 1;
    }
}
