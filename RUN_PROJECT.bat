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

echo [3/3] Compiling Server...
javac -cp bin -d bin src/FoodOrderingServer.java
if %errorlevel% neq 0 (
    echo ERROR: Server compilation failed!
    pause
    exit /b %errorlevel%
)

echo.
echo ============================================
echo   COMPILATION SUCCESSFUL!
echo ============================================
echo.
echo Starting OFO Server...
echo.
echo Access the website at: http://localhost:8080
echo Port: 8080
echo.
echo Features:
echo - Browse Restaurants
echo - Place Orders
echo - Track Orders
echo - Leave Reviews
echo - Multiple Payment Options
echo.
echo (Keep this window open while using the website)
echo.

cd bin
java -cp . FoodOrderingServer

if %errorlevel% neq 0 (
    echo.
    echo ERROR: Server failed to start!
    echo.
)

pause
cd ..
