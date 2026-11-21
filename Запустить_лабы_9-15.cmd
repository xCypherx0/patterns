@echo off
chcp 65001 >nul
cd /d "%~dp0"
if not exist bin mkdir bin
for /L %%n in (9,1,15) do (
  javac --release 17 -encoding UTF-8 -d bin lab%%n\src\lab%%n\*.java
  if errorlevel 1 goto error
)
javac --release 17 -encoding UTF-8 -d bin demo\src\demo\DemoRunner.java
if errorlevel 1 goto error
java -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -cp bin demo.DemoRunner
exit /b
:error
pause
exit /b 1
