@echo off
call mvn clean install
timeout /T 1  > nul

echo.
echo ----------
echo Kopiere jawb-tools-util-0.14.jar nach mytourapp\mytourapp-mobile-android\app\libs

copy /Y target\jawb-tools-util-0.14.jar ..\mytourapp-mobile-android\app\libs  

echo Fertig
echo ----------
pause