#!/bin/bash

set -e  # Encerra o script se algum comando falhar


echo "🚀 Buildando o projeto Quarkus..."
cd ../customer-management
quarkus build

echo "📦 Subindo containers com Docker Compose..."
cd ..
docker-compose up --build
