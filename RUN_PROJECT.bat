@echo off
echo ============================================
echo   Online Food Ordering System (OFO)
echo   Java OOP Project
echo ============================================
echo.

REM Create bin directory if it doesn't exist
if not exist bin mkdir bin

echo [1/3] Compiling Models...
javac -d bin src/models/*.java
if %errorlevel% neq 0 (
    echo ERROR: Models compilation failed!
    pause
    exit /b %errorlevel%
)

echo [2/3] Compiling Handlers and Database...
javac -cp bin -d bin src/handlers/*.java src/database/*.java
if %errorlevel% neq 0 (
    echo ERROR: Handlers/Database compilation failed!
    pause
    exit /b %errorlevel%
)

echo [3/3] Compiling GUI and Server...
javac -cp bin -d bin src/FoodOrderingServer.java src/ui/FoodOrderingGUI.java
if %errorlevel% neq 0 (
    echo ERROR: Server/GUI compilation failed!
    pause
    exit /b %errorlevel%
)

echo.
echo ============================================
echo   COMPILATION SUCCESSFUL!
echo ============================================
echo.
echo Starting OFO Desktop App...
echo.
echo The Java Swing GUI will now open.
echo.

cd bin
java -cp . ui.FoodOrderingGUI

if %errorlevel% neq 0 (
    echo.
    echo ERROR: App failed to start!
    echo.
)

pause
cd ..
