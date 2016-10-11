@echo off
call mvn clean package
timeout /T 1  > nul

echo.
echo ----------
echo Kopiere jawb-tools-util-0.13.jar nach mytourapp\mytourapp-mobile-android\app\libs

copy /Y target\jawb-tools-util-0.13.jar ..\mytourapp-mobile-android\app\libs  

echo Fertig
echo ----------
pause