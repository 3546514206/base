#include <iostream>
#include <utility>

class Handler {
private:
    std::string name;
protected:

    Handler *successor;
public:

    virtual void setSuccessor(Handler *successor) {
        this->successor = successor;
    }

    std::string getName() {
        return this->name;
    }

    void setName(const std::string &name) {
        this->name = name;
    }

    virtual void handleRequest(const std::string &request) = 0;
};

class ConcreteHandlerA : public Handler {
public:

    void handleRequest(const std::string &request) override {
        if (request == "A") {
            std::cout << "ConcreteHandlerA handles the request." << std::endl;
        }

        if (this->successor != nullptr) {
            successor->handleRequest(request);
        } else {
            std::cout << "No handler can process the request after " << getName() << std::endl;
        }
    }
};

class ConcreteHandlerB : public Handler {
public:

    void handleRequest(const std::string &request) override {
        if (request == "B") {
            std::cout << "ConcreteHandlerB handles the request. " << std::endl;
        }
        if (successor != nullptr) {
            successor->handleRequest(request);
        } else {
            std::cout << "No handler can process the request after " << getName() << std::endl;
        }
    }
};

class ConcreteHandlerC : public Handler {
public:
    void handleRequest(const std::string &request) override {
        if (request == "C") {
            std::cout << "ConcreteHandlerC handles the request." << std::endl;
        }
        if (successor != nullptr) {
            successor->handleRequest(request);
        } else {
            std::cout << "No handler can process the request after " << getName() << std::endl;
        }
    }
};

int main() {
    Handler *handlerA = new ConcreteHandlerA();
    handlerA->setName("ConcreteHandlerA");
    Handler *handlerB = new ConcreteHandlerB();
    handlerB->setName("ConcreteHandlerB");
    Handler *handlerC = new ConcreteHandlerC();
    handlerC->setName("ConcreteHandlerC");

    handlerA->setSuccessor(handlerB);
    handlerB->setSuccessor(handlerC);
    std::cout<<"test"<<std::endl;

    handlerA->handleRequest("A");

    return 0;
}
