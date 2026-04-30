#!/bin/bash

# Online Food Ordering System - Run Script for Linux/Mac

echo "=========================================="
echo "  Online Food Ordering System (OFO)"
echo "  Java OOP Project"
echo "=========================================="
echo ""

# Create bin directory if it doesn't exist
if [ ! -d "bin" ]; then
    mkdir bin
    echo "Created bin directory"
fi

echo "[1/3] Compiling Models..."
javac -d bin src/models/*.java
if [ $? -ne 0 ]; then
    echo "ERROR: Models compilation failed!"
    exit 1
fi

echo "[2/3] Compiling Handlers and Database..."
javac -cp bin -d bin src/handlers/*.java src/database/*.java
if [ $? -ne 0 ]; then
    echo "ERROR: Handlers/Database compilation failed!"
    exit 1
fi

echo "[3/3] Compiling Server..."
javac -cp bin -d bin src/FoodOrderingServer.java
if [ $? -ne 0 ]; then
    echo "ERROR: Server compilation failed!"
    exit 1
fi

echo ""
echo "=========================================="
echo "  COMPILATION SUCCESSFUL!"
echo "=========================================="
echo ""
echo "Starting OFO Server..."
echo ""
echo "Access the website at: http://localhost:8080"
echo "Port: 8080"
echo ""
echo "Features:"
echo "- Browse Restaurants"
echo "- Place Orders"
echo "- Track Orders"
echo "- Leave Reviews"
echo "- Multiple Payment Options"
echo ""
echo "(Keep this terminal window open while using the website)"
echo ""

cd bin
java -cp . FoodOrderingServer

if [ $? -ne 0 ]; then
    echo ""
    echo "ERROR: Server failed to start!"
    echo ""
fi

cd ..
