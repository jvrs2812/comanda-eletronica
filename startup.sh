if ! [ -x "$(command -v docker)" ]; then
  echo 'Docker não está instalado.'

  # Verifica se o sistema operacional é Windows ou Linux
  if [ "$(uname -s)" == "Linux" ]; then
    echo 'Instalando o Docker no Linux...'
    sudo apt-get update
    sudo apt-get install docker-ce -y
  elif [ "$(uname -s)" == "Windows" ]; then
    echo 'Instalando o Docker no Windows...'
    echo 'Acesse o site https://docs.docker.com/docker-for-windows/install/ para instalar o Docker no Windows manualmente.'
  else
    echo 'Sistema operacional desconhecido. Não é possível instalar o Docker.'
  fi
else
  echo 'Docker já está instalado.'
fi

docker-compose up -d

# Monitorar o status do container até que ele esteja pronto
until docker-compose logs | grep "database system is ready to accept connections"
do
  sleep 1
done

echo "Container pronto."

CONTAINER_NAME="comanda-eletronica-postgres-1"

DB_NAME="comanda"


docker exec $CONTAINER_NAME psql -U admin -c "CREATE DATABASE $DB_NAME;" postgres

echo "Banco de dados $DB_NAME criado com sucesso."
sleep 10