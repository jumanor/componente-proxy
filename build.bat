@echo off

REM Ruta al directorio del proyecto Maven
set PROJECT_DIR=C:\Users\ACER\Documents\ProyectoRegion2025\componente-proxy

REM Ruta al directorio de WildFly
set WILDFLY_HOME=C:\Users\ACER\Documents\wildfly-13.0.0.Final

REM Nombre del archivo WAR generado por Maven
set WAR_FILE=target\componente-proxy.war

REM Cambiar al directorio del proyecto
cd /d %PROJECT_DIR%

REM Ejecutar mvn package para construir el proyecto
echo Empaquetando la aplicación con Maven...
call mvn clean package

REM Verificar si el archivo WAR se generó correctamente
if not exist %WAR_FILE% (
    echo Error: El archivo WAR no se generó correctamente.
    exit /b 1
)

REM Desplegar la aplicación en WildFly
echo Desplegando la aplicación en WildFly...
call %WILDFLY_HOME%\bin\jboss-cli.bat --connect --command="deploy %PROJECT_DIR%\%WAR_FILE% --force"
REM start /b %WILDFLY_HOME%\bin\jboss-cli.bat --connect --command="deploy %PROJECT_DIR%\%WAR_FILE%"

REM Verificar el estado del despliegue
REM for /f "delims=" %%i in ('%WILDFLY_HOME%\bin\jboss-cli.bat --connect --command="deployment-info --name=componente-proxy.war" ^| findstr "status"') do set "DEPLOYMENT_STATUS=%%i"
REM echo Estado del despliegue: %DEPLOYMENT_STATUS%

REM Comprobar si el despliegue fue exitoso
REM echo %DEPLOYMENT_STATUS% | find /i "OK" >nul
REM if %errorlevel% == 0 (
REM    echo Despliegue exitoso.
REM ) else (
REM    echo Error en el despliegue.
REM    exit /b 1
REM )

REM Evitar el mensaje "Presione una tecla para continuar . . ."
exit /b 0
