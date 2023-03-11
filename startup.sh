if ! [ -x "$(command -v docker)" ]; then
  echo 'Docker não está instalado.'

  # Verifica se o sistema operacional é Windows ou Linux
  if [ "$(uname -s)" == "Linux" ]; then
    echo 'Instalando o Docker no Linux...'
    sudo apt-get update
    sudo apt-get install docker-ce -y
  elif [ "$(uname -o 2> /dev/null)" == "Msys" ]; then
    echo "Baixando o Docker Desktop for Windows..."
    curl -L "https://desktop.docker.com/win/main/amd64/Docker%20Desktop%20Installer.exe?utm_source=docker&utm_medium=webreferral&utm_campaign=dd-smartbutton&utm_location=module" -o "docker-installer.exe"

    echo "Instalando o Docker Desktop for Windows..."
    ./docker-installer.exe

    echo "sera necessario restartar o terminal."
    rm docker-installer.exe
    exit

    echo "Verificando se o Docker está funcionando..."
    docker version > /dev/null 2>&1

    while [ $? -ne 0 ]
    do
    echo "Aguardando a instalação do Docker ser concluída..."
    sleep 10
    docker version > /dev/null 2>&1
  done

    echo "Instalação concluída com sucesso!"
    else
      echo 'Sistema operacional desconhecido. Não é possível instalar o Docker.'
    fi
  
    echo "Excluindo o arquivo de instalador do Docker..."
    rm docker-installer.exe

    echo "Arquivo excluído com sucesso!"

  else
    echo 'Docker já está instalado.'
  fi
CONTAINER_NAME="comanda-eletronica-postgres-1"

DB_NAME="comanda"

if [ "$(uname -s)" == "Linux" ]; then
  if ! systemctl is-active --quiet docker; then
    echo "Docker não está em execução. Fechando..."
    sleep 5
    exit;
  fi
elif [ "$(uname -o 2> /dev/null)" == "Msys" ]; then
  if ! [ "$(docker info &> /dev/null ; echo $?)" -eq 0 ]; then
    echo "Docker não está em execução. Fechando..."
    sleep 5
    exit;
  fi
else
  echo "Sistema operacional desconhecido. Não é possível verificar se o Docker está em execução."
  exit 1
fi

docker-compose up -d

# Monitorar o status do container até que ele esteja pronto
until docker-compose logs | grep "database system is ready to accept connections"
do
  sleep 1
done

echo "Container pronto."

docker exec $CONTAINER_NAME psql -U admin -c "CREATE DATABASE $DB_NAME;" postgres

echo "Banco de dados $DB_NAME criado com sucesso."
sleep 10