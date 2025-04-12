#!/bin/bash

# Ruta al directorio del proyecto Maven
PROJECT_DIR="/home/jumanor/Documentos/region2025/componente-proxy"

# Ruta al directorio de WildFly
WILDFLY_HOME="/home/jumanor/Documentos/wildfly-13.0.0.Final"

# Nombre del archivo WAR generado por Maven
WAR_FILE="target/componente-proxy.war"

# Cambiar al directorio del proyecto
cd $PROJECT_DIR

# Ejecutar mvn package para construir el proyecto
echo "Empaquetando la aplicación con Maven..."
mvn clean package

# Verificar si el archivo WAR se generó correctamente
if [ ! -f $WAR_FILE ]; then
  echo "Error: El archivo WAR no se generó correctamente."
  exit 1
fi

# Desplegar la aplicación en WildFly
echo "Desplegando la aplicación en WildFly..."
echo "deploy $PROJECT_DIR/$WAR_FILE"
$WILDFLY_HOME/bin/jboss-cli.sh --connect --command="deploy $PROJECT_DIR/$WAR_FILE --force"

# Verificar el estado del despliegue
#DEPLOYMENT_STATUS=$($WILDFLY_HOME/bin/jboss-cli.sh --connect --command="deployment-info --name=tu-aplicacion.war" | grep "status")
#echo "Estado del despliegue: $DEPLOYMENT_STATUS"

# Comprobar si el despliegue fue exitoso
#if [[ $DEPLOYMENT_STATUS == *"OK"* ]]; then
#  echo "Despliegue exitoso."
#else
#  echo "Error en el despliegue."
#  exit 1
#fi
