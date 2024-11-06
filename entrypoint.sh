#!/bin/sh

PROJECT_NAME="react-tp-mti2024"

# Vérifie si le dossier de l'application React existe
if [ ! -d "/source/$PROJECT_NAME" ]; then
  echo "Aucun projet React trouvé. Création d'une nouvelle application React..."
  npx create-react-app $PROJECT_NAME

  # Installe la version spécifique de React
  cd $PROJECT_NAME
  npm install
else
  echo "Projet React détecté. Vérification des dépendances..."
  cd $PROJECT_NAME
  
  # Vérifie si le dossier node_modules existe, sinon exécute npm install
  if [ ! -d "node_modules" ]; then
    echo "Dossier node_modules introuvable. Installation des dépendances..."
    npm install
  else
    echo "Les dépendances sont déjà installées."
  fi
fi

# Démarre le serveur de développement
npm start
