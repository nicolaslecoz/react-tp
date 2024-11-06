# Utilise l'image officielle Node.js
FROM node:18-alpine

# Définit le répertoire de travail dans le conteneur
WORKDIR /app

# Installe create-react-app globalement
RUN npm install -g create-react-app@5.0.1

# Copie le script d'entrée dans le conteneur
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

# Expose le port 3000 pour React
EXPOSE 3000

# Utilise le script d'entrée comme commande par défaut
CMD ["/entrypoint.sh"]
