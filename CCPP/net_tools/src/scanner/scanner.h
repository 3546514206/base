#ifndef SCANNER_H
#define SCANNER_H

#include <vector>
#include <string>

class Scanner {
public:
    // Constructor
    Scanner();

    // Destructor
    ~Scanner();

    // Method to scan the network
    std::vector<std::string> scanNetwork(const std::string &subnet, int timeout);

private:
    // Platform-specific initialization
    void initialize();

    // Platform-specific cleanup
    void cleanup();

    // Utility to ping a single IP
    bool ping(const std::string &ip, int timeout);
};


void scan_subnet();

#endif // SCANNER_H
